/**
 * @filename:PurchasApplyDetailController 2019-06-12 11:57:58
 * @project ydsh-saas-service-stock  V1.0
 * Copyright(c) 2020 <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a> Co. Ltd.
 * All right reserved.
 */
package com.ydsh.stock.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ydsh.generator.common.JsonResult;
import com.ydsh.generator.common.PageParam;
import com.ydsh.stock.common.constans.CommonConstans;
import com.ydsh.stock.common.enums.DBDictionaryEnumManager;
import com.ydsh.stock.common.utils.MapBeanUtil;
import com.ydsh.stock.web.controller.base.AbstractController;
import com.ydsh.stock.web.entity.PurchasApplyDetail;
import com.ydsh.stock.web.service.PurchasApplyDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>自定义方法写在这里</p>
 * <p>
 * <p>说明： 采购申请子表（明细）API接口层</P>
 *
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 */
@Api(description = "采购申请子表（明细）", value = "采购申请子表（明细）")
@RestController
@RequestMapping("/purchasApplyDetail")
@Slf4j
public class PurchasApplyDetailController extends AbstractController<PurchasApplyDetailService, PurchasApplyDetail> {

    /**
     * 采购需求汇总修改采购状态
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/update_purchas_apply_review_status", method = RequestMethod.POST)
    @ApiOperation(value = "采购需求汇总修改采购状态", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<PurchasApplyDetail> updatePurchasApplyReviewStatus(@RequestBody PurchasApplyDetail entity) {
        log.info("【采购需求汇总修改采购状态】{},请求参数：{}", "接口请求", entity);
        if (entity == null
                || StringUtils.isEmpty(entity.getReviewStatus())
                || entity.getIdList() == null
                || entity.getIdList().size() <= 0
                || entity.getCreateId() == null
                || StringUtils.isEmpty(entity.getCreateName())) {
            log.error("【采购需求汇总修改采购状态】{}，请求参数：{}", "参数空异常", entity);
            return result.error("请完善必填项！");
        }
        //查询出数据
        List<PurchasApplyDetail> purchasApplyDetails = baseService.list(
                new QueryWrapper<PurchasApplyDetail>().lambda()
                        .in(PurchasApplyDetail::getId, entity.getIdList())
        );
        if (purchasApplyDetails == null || purchasApplyDetails.size() != entity.getIdList().size()) {
            log.error("【采购需求汇总修改采购状态】{}，请求参数：{}", "批量id里出现不存在的采购申请子表数据", entity);
            return result.error("请选择正确的采购需求进行操作！");
        }
        if (DBDictionaryEnumManager.purchase_status_2.getkey().equals(entity.getReviewStatus())) {
            // 无需采购
            // 状态为 已处理 或者 是无需采购 是不符合条件的
            long num = purchasApplyDetails.stream().filter(purchasApplyDetail ->
                    DBDictionaryEnumManager.purchase_status_0.getkey().equals(purchasApplyDetail.getReviewStatus())
                            || DBDictionaryEnumManager.purchase_status_2.getkey().equals(purchasApplyDetail.getReviewStatus())
            ).count();
            if (num > 0) {
                log.error("【采购需求汇总修改采购状态】{}，请求参数：{}", "存在已处理或者无需采购，不可以转成无需采购的状态", entity);
                return result.error("存在不可修改的需求，请选择正确的需求进行操作！");
            }
        } else if (DBDictionaryEnumManager.purchase_status_1.getkey().equals(entity.getReviewStatus())) {
            // 暂不采购
            // 状态不是待处理的，都不符合条件
            long numOne = purchasApplyDetails.stream().filter(purchasApplyDetail ->
                    !DBDictionaryEnumManager.purchase_status_3.getkey().equals(purchasApplyDetail.getReviewStatus())
            ).count();
            if (numOne > 0) {
                log.error("【采购需求汇总修改采购状态】{}，请求参数：{}", "存在非待处理需求，不可以转成暂不采购的状态", entity);
                return result.error("存在不可修改的需求，请选择正确的需求进行操作！");
            }
        } else {
            log.error("【采购需求汇总修改采购状态】{}，请求参数：{}", "错误修改状态！", entity);
            return result.error("错误操作，请勿非法错误操作！");
        }
        purchasApplyDetails.stream().forEach(
                purchasApplyDetail ->
                {
                    purchasApplyDetail.setReviewStatus(entity.getReviewStatus());
                    purchasApplyDetail.setReviewId(entity.getCreateId());
                    purchasApplyDetail.setCreateName(entity.getCreateName());
                    purchasApplyDetail.setReviewTime(new Date());
                }
        );
        baseService.saveBatch(purchasApplyDetails);
        return result.success("操作成功！");
    }

    /**
     * 采购需求汇总查询是否符合生成条件，并且返回完整信息
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/select_purchas_apply_detail", method = RequestMethod.POST)
    @ApiOperation(value = "采购需求汇总查询是否符合生成条件，并且返回完整信息",
            notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<List<PurchasApplyDetail>> selectPurchasApplyDetail(@RequestBody PurchasApplyDetail entity) {
        JsonResult<List<PurchasApplyDetail>> resultList = new JsonResult<List<PurchasApplyDetail>>();
        log.info("【采购需求汇总查询】{},请求参数：{}", "接口请求", entity);
        if (entity == null || entity.getIdList() == null || entity.getIdList().size() == 0) {
            log.error("【采购需求汇总查询】{}，请求参数：{}", "参数空异常", entity);
            return resultList.error("请完善必填项！");
        }
        List<PurchasApplyDetail> applyDetails = baseService.list(new QueryWrapper<PurchasApplyDetail>().lambda().in(
                PurchasApplyDetail::getId, entity.getIdList()
        ));
        long num = applyDetails.stream().filter(applyDetail ->
                DBDictionaryEnumManager.purchase_status_2.getkey().equals(applyDetail.getReviewStatus())
                        || DBDictionaryEnumManager.purchase_status_3.getkey().equals(applyDetail.getReviewStatus())
        ).count();
        if (num != 0) {
            log.error("【采购需求汇总查询】{}，请求参数：{}", "存在不支持生成采购单的状态", entity);
            return resultList.error("存在不支持生成采购单的状态！请选择正确的需求！");
        }
        return resultList.success("请求成功", applyDetails);
    }

    /**
     * 采购需求汇总分页查询
     *
     * @param pageParam
     * @return
     */
    @RequestMapping(value = "/page_purchas_apply", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询采购需求汇总", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<IPage<PurchasApplyDetail>> pagePurchasApply(@RequestBody PageParam<PurchasApplyDetail> pageParam) {
        log.info("【分页查询采购需求汇总】{},请求参数：{}", "接口请求", pageParam);
        JsonResult<IPage<PurchasApplyDetail>> resultPage = new JsonResult<IPage<PurchasApplyDetail>>();
        if (pageParam.getPageSize() > CommonConstans.NUM_500) {
            log.error("【分页查询采购需求汇总】{},请求参数：{}", "分页大小超出限制", pageParam);
            return resultPage.error("分页大小超出限制，请正确操作！");
        }
        if (pageParam.getPageNum() == CommonConstans.NUM_0) {
            pageParam.setPageNum(CommonConstans.NUM_1);
        }
        Page<PurchasApplyDetail> page = new Page<PurchasApplyDetail>(pageParam.getPageNum(), pageParam.getPageSize());
        QueryWrapper<PurchasApplyDetail> queryWrapper = new QueryWrapper<PurchasApplyDetail>();
        if (pageParam.getParam() != null) {
            Map map = MapBeanUtil.objectCamel2MapUnderline(pageParam.getParam());
            queryWrapper.allEq(map);
        }
        queryWrapper.lambda().groupBy(PurchasApplyDetail::getCreateTime);
        //提交开始时间
        if (pageParam.getParam().getCreateEndTime() != null) {
            queryWrapper.lambda().ge(PurchasApplyDetail::getCreateTime, pageParam.getParam().getCreateEndTime());
        }
        //提交结束时间
        if (pageParam.getParam().getCreateStartTime() != null) {
            queryWrapper.lambda().le(PurchasApplyDetail::getCreateTime, pageParam.getParam().getCreateStartTime());
        }
        //审核开始时间
        if (pageParam.getParam().getReviewStartTime() != null) {
            queryWrapper.lambda().ge(PurchasApplyDetail::getReviewTime, pageParam.getParam().getReviewStartTime());
        }
        //审核结束时间
        if (pageParam.getParam().getReviewEndTime() != null) {
            queryWrapper.lambda().le(PurchasApplyDetail::getReviewTime, pageParam.getParam().getReviewEndTime());
        }
        IPage<PurchasApplyDetail> pageData = baseService.page(page, queryWrapper);
        return resultPage.success(pageData);

    }

}
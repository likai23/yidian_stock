/**
 * @filename:PurchasApplyController 2019-06-12 11:57:58
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
import com.ydsh.stock.web.entity.PurchasApply;
import com.ydsh.stock.web.entity.PurchasApplyDetail;
import com.ydsh.stock.web.service.PurchasApplyDetailService;
import com.ydsh.stock.web.service.PurchasApplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * <p>自定义方法写在这里</p>
 * <p>
 * <p>说明： 采购申请主表API接口层</P>
 *
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 */
@Api(description = "采购申请主表", value = "采购申请主表")
@RestController
@RequestMapping("/purchasApply")
@Slf4j
public class PurchasApplyController extends AbstractController<PurchasApplyService, PurchasApply> {
    /**
     * 采购申请子表数据层
     */
    @Autowired
    private PurchasApplyDetailService purchasApplyDetailServiceImpl;

    /**
     * 采购申请新增
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/insert_purchas_apply", method = RequestMethod.POST)
    @ApiOperation(value = "新增采购申请", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<PurchasApply> insertPurchasApply(@RequestBody PurchasApply entity) {
        log.info("【新增采购申请】{},请求参数：{}", "接口请求", entity);
        if (entity == null || entity.getDetail() == null || entity.getDetail().size() <= 0
                || entity.getCreateId() == null || StringUtils.isEmpty(entity.getCreateName())) {
            log.error("【新增采购申请】{}，请求参数：{}", "参数空异常", entity);
            return result.error("请完善必填项！");
        }

        List<PurchasApplyDetail> detail = entity.getDetail();
        int bpaProductNum = 0;
        for (PurchasApplyDetail purchasApplyDetail : detail) {
            if (purchasApplyDetail.getGcsId() == null
                    || StringUtils.isEmpty(purchasApplyDetail.getGcsNo())
                    || StringUtils.isEmpty(purchasApplyDetail.getGcsName())
                    || StringUtils.isEmpty(purchasApplyDetail.getGcsType())
                    || StringUtils.isEmpty(purchasApplyDetail.getGcsNature())
                    || StringUtils.isEmpty(purchasApplyDetail.getGcsSku())
                    || purchasApplyDetail.getProductNum() == null
                    || purchasApplyDetail.getProductNum() == 0) {
                log.error("【新增采购申请】{}，请求参数：{}", "参数空异常", entity);
                return result.error("请完善必填项！");
            }
            bpaProductNum += purchasApplyDetail.getProductNum();
        }
        entity.setProductNum(bpaProductNum);
        // 添加采购主单
        PurchasApply purchasApply = baseService.saveReId(entity);
        if (purchasApply == null) {
            log.error("【新增采购申请】{}，请求参数：{}", "添加采购订单主表失败，数据库操作异常", entity);
            return result.error("添加采购订单主表失败,请联系管理员！");
        }
        for (PurchasApplyDetail purchasApplyDetail : detail) {
            purchasApplyDetail.setPurchasApplyId(purchasApply.getId());
            purchasApplyDetail.setCreateId(entity.getCreateId());
            purchasApplyDetail.setCreateName(entity.getCreateName());
            purchasApplyDetail.setReviewStatus(DBDictionaryEnumManager.review_0.getkey());
        }
        purchasApplyDetailServiceImpl.saveBatch(detail);
        return result.success("操作成功！");

    }

    /**
     * 采购申请修改
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/update_purchas_apply", method = RequestMethod.POST)
    @ApiOperation(value = "采购申请修改", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<PurchasApply> updatePurchasApply(@RequestBody PurchasApply entity) {
        log.info("【采购申请修改】{},请求参数：{}", "接口请求", entity);
        if (entity == null || entity.getDetail() == null
                || entity.getDetail().size() <= 0 || entity.getId() == null) {
            log.error("【采购申请修改】{}，请求参数：{}", "参数空异常", entity);
            return result.error("请完善必填项！");
        }
        // 判断状态是否能修改
        int bpaProductNum = 0;
        for (PurchasApplyDetail purchasApplyDetail : entity.getDetail()) {
            if (purchasApplyDetail.getGcsId() == null
                    || StringUtils.isEmpty(purchasApplyDetail.getGcsNo())
                    || StringUtils.isEmpty(purchasApplyDetail.getGcsName())
                    || StringUtils.isEmpty(purchasApplyDetail.getGcsType())
                    || StringUtils.isEmpty(purchasApplyDetail.getGcsNature())
                    || StringUtils.isEmpty(purchasApplyDetail.getGcsSku())
                    || purchasApplyDetail.getProductNum() == null
                    || purchasApplyDetail.getProductNum() == 0) {
                log.error("【采购申请修改】{}，请求参数：{}", "参数空异常", entity);
                return result.error("请完善必填项！");
            }
            purchasApplyDetail.setReviewStatus(DBDictionaryEnumManager.review_0.getkey());
            purchasApplyDetail.setPurchasApplyId(entity.getId());
            bpaProductNum += purchasApplyDetail.getProductNum();
        }
        //查询旧数据
        List<PurchasApplyDetail> purchasApplyDetailList = purchasApplyDetailServiceImpl.list(
                new QueryWrapper<PurchasApplyDetail>().lambda()
                        .eq(PurchasApplyDetail::getPurchasApplyId, entity.getId()));
        // 统计非未处理的数量
        long num = purchasApplyDetailList.stream().filter(purchasApplyDetail -> !DBDictionaryEnumManager.review_0.getkey().equals(purchasApplyDetail.getReviewStatus())).count();
        if (num > 0) {
            log.error("【采购申请修改】{}，请求参数：{}", "该采购申请订单无法修改,存在不可修改的状态！", entity);
            return result.error("该采购申请订单无法修改！");
        }
        entity.setProductNum(bpaProductNum);
        // 修改主表信息
        boolean flag = baseService.updateById(entity);
        if (!flag) {
            log.error("【采购申请修改】{}，请求参数：{}", "采购申请主表数据库执行异常失败！", entity);
            return result.error("该采购申请订单修改失败，请联系管理员！");
        }
        //删除旧的数据
        Boolean removeflag = purchasApplyDetailServiceImpl.remove(
                new QueryWrapper<PurchasApplyDetail>().lambda().eq(PurchasApplyDetail::getPurchasApplyId, entity.getId())
        );
        if (!removeflag) {
            log.error("【采购申请修改】{}，请求参数：{}", "采购申请子表数据库执行删除异常失败！", entity);
            return result.error("该采购申请订单修改失败，请联系管理员！");
        }
        //新增数据
        purchasApplyDetailServiceImpl.saveBatch(entity.getDetail());
        return result.success("操作成功！");
    }

    /**
     * 采购申请修改查看/查看详情 根据id
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/select_purchas_apply", method = RequestMethod.POST)
    @ApiOperation(value = "采购申请修改查看/查看详情", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<PurchasApply> selectPurchasApply(@RequestBody PurchasApply entity) {
        log.info("【采购申请修改查看/查看详情】{},请求参数：{}", "接口请求", entity);
        if (entity.getId() == null || entity.getType() == null) {
            log.error("【采购申请修改查看/查看详情】{}，请求参数：{}", "参数空异常", entity);
            return result.error("请完善必填项！");
        }
        //查询主表数据
        PurchasApply purchasApply = baseService.getById(entity.getId());
        if (purchasApply == null) {
            log.error("【采购申请修改查看/查看详情】{}，请求参数：{}", "该采购申请不存在！", entity);
            return result.error("该采购申请不存在，请选择正确的采购申请单进行操作！");
        }
        //查询子表数据
        List<PurchasApplyDetail> purchasApplyDetails = purchasApplyDetailServiceImpl.list(
                new QueryWrapper<PurchasApplyDetail>().lambda()
                        .eq(PurchasApplyDetail::getPurchasApplyId, entity.getId())
        );
        if (purchasApplyDetails == null || purchasApplyDetails.size() <= 0) {
            log.error("【采购申请修改查看/查看详情】{}，请求参数：{}", "该采购申请异常存在！", entity);
            return result.error("该采购申请异常存在，请联系管理员！");
        }
        // 判断类型 // 如果是修改的话，需要判断是否是可修改的数据
        if (CommonConstans.NUM_1 == entity.getType()) {
            // 统计非未处理的数量
            long num = purchasApplyDetails.stream().filter(applyDetail -> !DBDictionaryEnumManager.review_0.getkey().equals(applyDetail.getReviewStatus())).count();
            if (num > 0) {
                log.error("【采购申请修改查看/查看详情】{}，请求参数：{}", "该采购申请订单无法修改,存在不可修改的状态！", entity);
                return result.error("该采购申请订单无法修改！");
            }
        }
        purchasApply.setDetail(purchasApplyDetails);
        return result.success("请求成功", purchasApply);
    }

    /**
     * 删除采购申请
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/delete_purchas_apply", method = RequestMethod.POST)
    @ApiOperation(value = "采购申请删除操作", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<PurchasApply> deletePurchasApply(@RequestBody PurchasApply entity) {
        log.info("【采购申请删除操作】{},请求参数：{}", "接口请求", entity);
        if (entity.getId() == null) {
            log.error("【采购申请删除操作】{}，请求参数：{}", "参数空异常", entity);
            return result.error("请完善必填项！");
        }
        //查询子表数据
        List<PurchasApplyDetail> purchasApplyDetails = purchasApplyDetailServiceImpl.list(
                new QueryWrapper<PurchasApplyDetail>().lambda()
                        .eq(PurchasApplyDetail::getPurchasApplyId, entity.getId())
        );
        if (purchasApplyDetails == null || purchasApplyDetails.size() <= 0) {
            log.error("【采购申请删除操作】{}，请求参数：{}", "该采购申请异常存在！", entity);
            return result.error("该采购申请订单无法删除，该采购申请异常存在，请联系管理员！");
        }
        // 统计非未处理的数量
        long num = purchasApplyDetails.stream().filter(applyDetail -> !DBDictionaryEnumManager.review_0.getkey().equals(applyDetail.getReviewStatus())).count();
        if (num > 0) {
            log.error("【采购申请删除操作】{}，请求参数：{}", "该采购申请订单无法删除,存在不可删除的状态！", entity);
            return result.error("该采购申请订单无法删除！");
        }
        // 执行删除操作
        boolean delectFlag = baseService.removeById(entity.getId());
        if (!delectFlag) {
            log.error("【采购申请删除操作】{}，请求参数：{}", "采购申请主表删除失败，数据库操作异常！", entity);
            return result.error("删除失败，请联系管理员！");
        }
        //删除旧的数据
        purchasApplyDetailServiceImpl.remove(
                new QueryWrapper<PurchasApplyDetail>().lambda().eq(PurchasApplyDetail::getPurchasApplyId, entity.getId())
        );
        return result.error("操作成功！");

    }

    /**
     * 分页查询采购申请
     *
     * @param pageParam
     * @return
     */
    @RequestMapping(value = "/page_purchas_apply", method = RequestMethod.POST)
    @ApiOperation(value = "分页查询采购申请", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<IPage<PurchasApply>> pagePurchasApply(@RequestBody PageParam<PurchasApply> pageParam) {
        log.info("【分页查询采购申请】{},请求参数：{}", "接口请求", pageParam);
        JsonResult<IPage<PurchasApply>> resultPage = new JsonResult<IPage<PurchasApply>>();
        if (pageParam.getPageSize() > CommonConstans.NUM_500) {
            log.error("【分页查询采购申请】{},请求参数：{}", "分页大小超出限制", pageParam);
            return resultPage.error("分页大小超出限制，请正确操作！");
        }
        if (pageParam.getPageNum() == CommonConstans.NUM_0) {
            pageParam.setPageNum(CommonConstans.NUM_1);
        }
        Page<PurchasApply> page = new Page<PurchasApply>(pageParam.getPageNum(), pageParam.getPageSize());
        QueryWrapper<PurchasApply> queryWrapper = new QueryWrapper<PurchasApply>();
        if (pageParam.getParam() != null) {
            Map map = MapBeanUtil.objectCamel2MapUnderline(pageParam.getParam());
            queryWrapper.allEq(map);
        }
        queryWrapper.lambda().groupBy(PurchasApply::getCreateTime);
        //提交开始时间
        if (pageParam.getParam().getCreateEndTime() != null) {
            queryWrapper.lambda().ge(PurchasApply::getCreateTime, pageParam.getParam().getCreateEndTime());
        }
        //提交结束时间
        if (pageParam.getParam().getCreateStartTime() != null) {
            queryWrapper.lambda().le(PurchasApply::getCreateTime, pageParam.getParam().getCreateStartTime());
        }
        IPage<PurchasApply> pageData = baseService.page(page, queryWrapper);
        return resultPage.success(pageData);
    }


}
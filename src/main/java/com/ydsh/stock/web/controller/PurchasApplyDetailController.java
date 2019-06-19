/**
 * @filename:PurchasApplyDetailController 2019-06-12 11:57:58
 * @project ydsh-saas-service-stock  V1.0
 * Copyright(c) 2020 <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a> Co. Ltd.
 * All right reserved.
 */
package com.ydsh.stock.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ydsh.generator.common.JsonResult;
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
     * 采购申请新增
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/insert_purchas_apply", method = RequestMethod.POST)
    @ApiOperation(value = "新增采购申请", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<PurchasApplyDetail> updateReviewStatus(@RequestBody PurchasApplyDetail entity) {
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
        switch (entity.getReviewStatus()) {
            // 无需采购
            case "1":
                // 状态为 已处理 或者 是无需采购 是不符合条件的
                long num = purchasApplyDetails.stream().filter(purchasApplyDetail ->
                        "".equals(purchasApplyDetail.getReviewStatus()) || "".equals(purchasApplyDetail.getReviewStatus())
                ).count();
                if (num > 0) {
                    log.error("【采购需求汇总修改采购状态】{}，请求参数：{}", "存在已处理或者无需采购，不可以转成无需采购的状态", entity);
                    return result.error("存在不可修改的需求，请选择正确的需求进行操作！");
                }
                break;
            // 暂不采购
            case "2":
                // 状态不是待处理的，都不符合条件
                long numOne = purchasApplyDetails.stream().filter(purchasApplyDetail ->
                        !"".equals(purchasApplyDetail.getReviewStatus())
                ).count();
                if (numOne > 0) {
                    log.error("【采购需求汇总修改采购状态】{}，请求参数：{}", "存在非待处理需求，不可以转成暂不采购的状态", entity);
                    return result.error("存在不可修改的需求，请选择正确的需求进行操作！");
                }
                break;
            default:
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

}
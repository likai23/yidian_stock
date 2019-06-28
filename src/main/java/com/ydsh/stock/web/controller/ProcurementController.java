/**
 * @filename:ProcurementController 2019-06-12 11:57:57
 * @project ydsh-saas-service-stock  V1.0
 * Copyright(c) 2020 <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a> Co. Ltd.
 * All right reserved.
 */
package com.ydsh.stock.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ydsh.generator.common.JsonResult;
import com.ydsh.stock.common.constans.CommonConstans;
import com.ydsh.stock.common.db.DBKeyGenerator;
import com.ydsh.stock.common.enums.DBBusinessKeyTypeEnums;
import com.ydsh.stock.common.enums.DBDictionaryEnumManager;
import com.ydsh.stock.web.controller.base.AbstractController;
import com.ydsh.stock.web.entity.Procurement;
import com.ydsh.stock.web.entity.ProcurementDetail;
import com.ydsh.stock.web.service.ProcurementDetailService;
import com.ydsh.stock.web.service.ProcurementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>自定义方法写在这里</p>
 * <p>
 * <p>说明： 采购订单主表API接口层</P>
 *
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 */
@Api(description = "采购订单主表", value = "采购订单主表")
@RestController
@RequestMapping("/procurement")
@Slf4j
public class ProcurementController extends AbstractController<ProcurementService, Procurement> {
    /**
     * 子表数据操作层
     */
    @Autowired
    private ProcurementDetailService procurementDetailServiceImpl;

    /**
     * 采购单新增
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/insert_purchas_apply", method = RequestMethod.POST)
    @ApiOperation(value = "采购单新增", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<Procurement> insertPurchasApply(@RequestBody List<Procurement> entity) {
        log.info("【新建采购订单】{},请求参数：{}", "接口请求", entity);
        if (entity == null || entity.size() == 0) {
            log.error("【新建采购订单】{}，请求参数：{}", "参数空异常", entity);
            return result.error("请完善必填项！");
        }
        for (Procurement procurement : entity) {
            if (procurement.getDetail() == null
                    || procurement.getDetail().size() == 0
                    || procurement.getSupplierId() == null
                    || StringUtils.isEmpty(procurement.getSupplierNo())
                    || StringUtils.isEmpty(procurement.getSupplierName())
                    || procurement.getCreateId() == null
                    || StringUtils.isEmpty(procurement.getCreateName())
                    || StringUtils.isEmpty(procurement.getProcurementType())) {
                log.error("【新建采购订单】{}，请求参数：{}", "参数空异常", entity);
                return result.error("请完善必填项！");
            }
            int ProductNum = 0;
            for (ProcurementDetail procurementDetail : procurement.getDetail()) {
                if (procurementDetail.getGcsId() == null
                        || StringUtils.isEmpty(procurementDetail.getGcsNo())
                        || StringUtils.isEmpty(procurementDetail.getGcsSku())
                        || StringUtils.isEmpty(procurementDetail.getGcsName())
                        || procurementDetail.getGcsDenomination() == null
                        || StringUtils.isEmpty(procurementDetail.getGcsType())
                        || StringUtils.isEmpty(procurementDetail.getGcsNature())
                        || procurementDetail.getTaxRate() == null
                        || procurementDetail.getSupplyPrice() == null
                        || StringUtils.isEmpty(procurementDetail.getInvoiceType())
                        || StringUtils.isEmpty(procurementDetail.getSettlementType())
                        || StringUtils.isEmpty(procurementDetail.getOrNotExtension())
                        || procurementDetail.getProductNum() == null) {
                    return result.error("请完善必填项！");
                }
                // 累计数量
                ProductNum += procurementDetail.getProductNum();
            }
            procurement.setTotalNum(ProductNum);
            procurement.setReviewStatus(DBDictionaryEnumManager.review_0.getkey());
        }
        for (Procurement procurement : entity) {
            procurement.setProcurementNo(DBKeyGenerator.generatorDBKey(DBBusinessKeyTypeEnums.P, null));
            Procurement reId = baseService.saveReId(procurement);
            procurement.setId(reId.getId());
        }
        List<ProcurementDetail> procurementDetails = new ArrayList<ProcurementDetail>();
        entity.stream().forEach(procurement -> {
            procurement.getDetail().stream().forEach(procurementDetail -> {
                procurementDetail.setProcurementId(procurement.getId());
                procurementDetails.add(procurementDetail);
            });
        });
        procurementDetailServiceImpl.saveBatch(procurementDetails);
        return result.success("操作成功！");
    }

    /**
     * 采购单修改
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/update_purchas_apply", method = RequestMethod.POST)
    @ApiOperation(value = "采购单修改", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<Procurement> updatePurchasApply(@RequestBody Procurement entity) {
        log.info("【采购单修改】{},请求参数：{}", "接口请求", entity);
        if (entity == null
                || entity.getId() == null
                || entity.getDetail() == null
                || entity.getDetail().size() == 0
                || entity.getSupplierId() == null
                || StringUtils.isEmpty(entity.getSupplierNo())
                || StringUtils.isEmpty(entity.getSupplierName())
                || entity.getCreateId() == null
                || StringUtils.isEmpty(entity.getCreateName())
                || StringUtils.isEmpty(entity.getProcurementType())) {
            log.error("【采购单修改】{}，请求参数：{}", "主表参数空异常", entity);
            return result.error("请完善必填项！");
        }
        int ProductNum = 0;
        for (ProcurementDetail procurementDetail : entity.getDetail()) {
            if (procurementDetail.getGcsId() == null
                    || StringUtils.isEmpty(procurementDetail.getGcsNo())
                    || StringUtils.isEmpty(procurementDetail.getGcsSku())
                    || StringUtils.isEmpty(procurementDetail.getGcsName())
                    || procurementDetail.getGcsDenomination() == null
                    || StringUtils.isEmpty(procurementDetail.getGcsType())
                    || StringUtils.isEmpty(procurementDetail.getGcsNature())
                    || procurementDetail.getTaxRate() == null
                    || procurementDetail.getSupplyPrice() == null
                    || StringUtils.isEmpty(procurementDetail.getInvoiceType())
                    || StringUtils.isEmpty(procurementDetail.getSettlementType())
                    || StringUtils.isEmpty(procurementDetail.getOrNotExtension())
                    || procurementDetail.getProductNum() == null) {
                procurementDetail.setId(null);
                log.error("【采购单修改】{}，请求参数：{}", "子表参数空异常", entity);
                return result.error("请完善必填项！");
            }
            // 累计数量
            ProductNum += procurementDetail.getProductNum();
        }
        entity.setTotalNum(ProductNum);
        entity.setReviewStatus(DBDictionaryEnumManager.review_0.getkey());
        // 查询订单状态
        Procurement procurement = baseService.getById(entity.getId());
        if (procurement == null) {
            log.error("【采购单修改】{}，请求参数：{}", "采购订单不存在", entity);
            return result.error("此订单不存在，请选择正确的采购订单进行操作！");
        }
        if (!DBDictionaryEnumManager.review_0.getkey().equals(procurement.getReviewStatus())
                && !DBDictionaryEnumManager.review_2.getkey().equals(procurement.getReviewStatus())) {
            log.error("【采购单修改】{}，请求参数：{}", "采购订单审核审核状态不符合修改的条件", entity);
            return result.error("此订单不可修改，请选择正确的采购订单进行操作！");
        }
        // 更新
        baseService.updateById(entity);
        // 删除
        procurementDetailServiceImpl.remove(new QueryWrapper<ProcurementDetail>().lambda().eq(
                ProcurementDetail::getProcurementId, entity.getId()
        ));
        // 增加
        procurementDetailServiceImpl.saveBatch(entity.getDetail());
        return result.success("操作成功！");
    }

    /**
     * 采购单查询
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/select_purchas_apply", method = RequestMethod.POST)
    @ApiOperation(value = "采购单查询", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<Procurement> selectPurchasApply(@RequestBody Procurement entity) {
        log.info("【采购单查询】{},请求参数：{}", "接口请求", entity);
        if (entity == null || entity.getId() == null || entity.getType() == null) {
            log.error("【采购单查询】{},请求参数：{}", "参数空异常！", entity);
            return result.error("请完善必填项！");
        }
        // 查询信息
        Procurement procurement = baseService.getById(entity.getId());
        if (procurement == null) {
            log.error("【采购单查询】{},请求参数：{}", "采购单不存在！", entity);
            return result.error("采购订单不存在，请正确操作！");
        }
        // 根据类型 判断状态
        if (CommonConstans.NUM_1 == entity.getType()) {
            // 如果不是 待审核、审核不通过  不能查看
            if (DBDictionaryEnumManager.review_1.getkey().equals(procurement.getReviewStatus())) {
                log.error("【采购单查询】{},请求参数：{}", "采购单的状态不符合修改条件！", entity);
                return result.error("采购单的状态不符合修改条件！");
            }
        }
        List<ProcurementDetail> procurementDetail = procurementDetailServiceImpl.list(new QueryWrapper<ProcurementDetail>().lambda()
                .eq(ProcurementDetail::getProcurementId, entity.getId())
        );
        if (procurementDetail == null || procurementDetail.size() == 0) {
            log.error("【采购单查询】{},请求参数：{}", "此采购订单的子订单异常，空！", entity);
            return result.error("采购订单异常，请联系管理员！");
        }
        procurement.setDetail(procurementDetail);
        return result.success("请求成功", procurement);
    }

    /**
     * 采购单删除
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/delete_purchas_apply", method = RequestMethod.POST)
    @ApiOperation(value = "采购单删除（支持批量）", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<Procurement> deletePurchasApply(@RequestBody Procurement entity) {
        log.info("【采购单删除（支持批量）】{},请求参数：{}", "接口请求", entity);
        if (entity == null || entity.getIdList() == null || entity.getIdList().size() == 0) {
            log.error("【采购单删除（支持批量）】{},请求参数：{}", "参数空异常！", entity);
            return result.error("请完善必填项！");
        }
        // 查询出信息
        List<Procurement> procurements = baseService.list(new QueryWrapper<Procurement>().lambda().in(
                Procurement::getId, entity.getIdList()
        ));
        for (Procurement procurement : procurements) {
            if (DBDictionaryEnumManager.review_1.getkey().equals(procurement.getReviewStatus())) {
                log.error("【采购单删除（支持批量）】{},请求参数：{}", "存在已审核通过的！", entity);
                return result.error("此采购单审核通过，不可删除！");
            }
        }
        // 删除
        baseService.remove(new QueryWrapper<Procurement>().lambda().in(
                Procurement::getId, entity.getIdList()
        ));
        //删除子表
        procurementDetailServiceImpl.remove(new QueryWrapper<ProcurementDetail>().lambda()
                .in(ProcurementDetail::getProcurementId, entity.getIdList())
        );
        return result.success("操作成功！");
    }

    /**
     * 采购单审核
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/update_purchas_apply_review_status", method = RequestMethod.POST)
    @ApiOperation(value = "采购单审核（支持批量审核）", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<Procurement> updatePurchasApplyReviewStatus(@RequestBody Procurement entity) {
        log.info("【采购单审核（支持批量审核）】{},请求参数：{}", "接口请求", entity);
        if (entity == null || entity.getIdList() == null || entity.getIdList().size() == 0
                || StringUtils.isEmpty(entity.getReviewStatus())
                || StringUtils.isEmpty(entity.getReviewName())
                || entity.getReviewId() == null) {
            log.error("【采购单审核（支持批量审核）】{},请求参数：{}", "参数空异常！", entity);
            return result.error("请完善必填项！");
        }
        if (!DBDictionaryEnumManager.review_1.getkey().equals(entity.getReviewStatus())
                && !DBDictionaryEnumManager.review_2.getkey().equals(entity.getReviewStatus())) {
            log.error("【采购单审核（支持批量审核）】{},请求参数：{}", "审核参数异常，传参错误！", entity);
            return result.error("审核数据异常，请正确操作！");
        }
        // 查询状态
        List<Procurement> procurements = baseService.list(new QueryWrapper<Procurement>().lambda()
                .in(Procurement::getId, entity.getIdList())
        );
        if (procurements == null || procurements.size() != entity.getIdList().size()) {
            log.error("【采购单审核（支持批量审核）】{},请求参数：{}", "采购单不存在，请检查！", entity);
            return result.error("采购单不存在，请选择正确的采购单进行操作！");
        }

        for (Procurement procurement : procurements) {
            if (!DBDictionaryEnumManager.review_0.getkey().equals(procurement.getReviewStatus())) {
                return result.error("采购单存在不可审核的订单，请选择正确的采购单进行操作！");
            }
        }
        // 批量修改状态
        baseService.update(entity, new QueryWrapper<Procurement>().lambda()
                .in(Procurement::getId, entity.getIdList())
        );
        return result.success("操作成功！");
    }
}
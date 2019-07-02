/**
 * @filename:ReviewInStorageController 2019-06-12 11:57:58
 * @project ydsh-saas-service-stock  V1.0
 * Copyright(c) 2020 <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a> Co. Ltd.
 * All right reserved.
 */
package com.ydsh.stock.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ydsh.generator.common.JsonResult;
import com.ydsh.stock.common.enums.DBDictionaryEnumManager;
import com.ydsh.stock.web.controller.base.AbstractController;
import com.ydsh.stock.web.entity.ReviewInStorage;
import com.ydsh.stock.web.entity.ReviewInStorageDetails;
import com.ydsh.stock.web.service.ReviewInStorageDetailsService;
import com.ydsh.stock.web.service.ReviewInStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>自定义方法写在这里</p>
 * <p>
 * <p>说明： 入库单申请主表API接口层</P>
 *
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 */
@Api(description = "入库单申请主表", value = "入库单申请主表")
@RestController
@RequestMapping("/reviewInStorage")
@Slf4j
public class ReviewInStorageController extends AbstractController<ReviewInStorageService, ReviewInStorage> {
    /**
     * 子表数据层操作
     */
    @Autowired
    private ReviewInStorageDetailsService reviewInStorageDetailsServiceImpl;

    /**
     * 新增入库单
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/insert_review_in_storage", method = RequestMethod.POST)
    @ApiOperation(value = "新增入库单", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<ReviewInStorage> insertReviewInStorage(@RequestBody ReviewInStorage entity) {
        log.info("【新增入库单】{},请求参数：{}", "接口请求", entity);
        if (entity == null
                || entity.getPurchasingApplyId() == null
                || StringUtils.isEmpty(entity.getPurchasingApplyNo())
                || entity.getSupplierId() == null
                || StringUtils.isEmpty(entity.getSupplierName())
                || StringUtils.isEmpty(entity.getSupplierNo())
                || StringUtils.isEmpty(entity.getReviewInStorageType())
                || entity.getCreateId() == null
                || StringUtils.isEmpty(entity.getCreateName())
                || entity.getDetail() == null
                || entity.getDetail().size() == 0) {
            log.error("【新增入库单】{}，请求参数：{}", "参数空异常", entity);
            return result.error("请完善必填项！");
        }
        for (ReviewInStorageDetails reviewInStorageDetails : entity.getDetail()) {
            if (reviewInStorageDetails.getGcsId() == null
                    || StringUtils.isEmpty(reviewInStorageDetails.getGcsNo())
                    || StringUtils.isEmpty(reviewInStorageDetails.getGcsSku())
                    || StringUtils.isEmpty(reviewInStorageDetails.getGcsName())
                    || StringUtils.isEmpty(reviewInStorageDetails.getGcsType())
                    || reviewInStorageDetails.getGcsDenomination() == null
                    || reviewInStorageDetails.getSupplyPrice() == null
                    || reviewInStorageDetails.getTaxRate() == null
                    || StringUtils.isEmpty(reviewInStorageDetails.getInvoiceType())
                    || StringUtils.isEmpty(reviewInStorageDetails.getSettlementType())
                    || StringUtils.isEmpty(reviewInStorageDetails.getOrNotExtension())
                    || reviewInStorageDetails.getProcurementNum() == null
                    || reviewInStorageDetails.getExpectedInStorageNum() == null
                    || reviewInStorageDetails.getActualInStorageNum() == null
                    || reviewInStorageDetails.getEffectiveEndTime() == null
                    || reviewInStorageDetails.getEffectiveStartTime() == null
                    || StringUtils.isEmpty(reviewInStorageDetails.getFileName())
                    || StringUtils.isEmpty(reviewInStorageDetails.getFileUrl())) {
                log.error("【新增入库单】{}，请求参数：{}", "参数空异常", entity);
                return result.error("请完善必填项！");
            }
            if (reviewInStorageDetails.getEffectiveStartTime().after(reviewInStorageDetails.getEffectiveEndTime())) {
                log.error("【新增入库单】{}，请求参数：{}", "时间参数错误！", entity);
                return result.error("时间参数错误！");
            }
        }
        entity.setReviewStatus(DBDictionaryEnumManager.review_3.getkey());
        boolean flag = baseService.save(entity);
        if (!flag) {
            log.error("【新增入库单】{}，请求参数：{}", "主表新增数据失败！", entity);
            return result.error("时间参数错误！");
        }
        entity.getDetail().stream().forEach(
                reviewInStorageDetails -> {
                    reviewInStorageDetails.setReviewInStorageId(entity.getId());
                }
        );
        reviewInStorageDetailsServiceImpl.saveBatch(entity.getDetail());
        return result.success("操作成功！");
    }

    /**
     * 修改入库单
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/update_review_in_storage", method = RequestMethod.POST)
    @ApiOperation(value = "修改入库单", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<ReviewInStorage> updateReviewInStorage(@RequestBody ReviewInStorage entity) {
        log.info("【修改入库单】{},请求参数：{}", "接口请求", entity);
        if (entity == null
                || entity.getId() == null
                || entity.getPurchasingApplyId() == null
                || StringUtils.isEmpty(entity.getPurchasingApplyNo())
                || entity.getSupplierId() == null
                || StringUtils.isEmpty(entity.getSupplierName())
                || StringUtils.isEmpty(entity.getSupplierNo())
                || StringUtils.isEmpty(entity.getReviewInStorageType())
                || entity.getCreateId() == null
                || StringUtils.isEmpty(entity.getCreateName())
                || entity.getDetail() == null
                || entity.getDetail().size() == 0) {
            log.error("【修改入库单】{}，请求参数：{}", "参数空异常", entity);
            return result.error("请完善必填项！");
        }
        ReviewInStorage reviewInStorage = baseService.getById(entity.getId());
        if (!DBDictionaryEnumManager.review_0.getkey().equals(reviewInStorage.getReviewStatus())
                || !DBDictionaryEnumManager.review_2.getkey().equals(reviewInStorage.getReviewStatus())) {
            log.error("【修改入库单】{}，请求参数：{}", "此入库单不符合修改条件，无法修改！", entity);
            return result.error("此入库单不符合修改条件，无法修改，请选择正确的入库单进行操作！");
        }
        for (ReviewInStorageDetails reviewInStorageDetails : entity.getDetail()) {
            if (reviewInStorageDetails.getGcsId() == null
                    || StringUtils.isEmpty(reviewInStorageDetails.getGcsNo())
                    || StringUtils.isEmpty(reviewInStorageDetails.getGcsSku())
                    || StringUtils.isEmpty(reviewInStorageDetails.getGcsName())
                    || StringUtils.isEmpty(reviewInStorageDetails.getGcsType())
                    || reviewInStorageDetails.getGcsDenomination() == null
                    || reviewInStorageDetails.getSupplyPrice() == null
                    || reviewInStorageDetails.getTaxRate() == null
                    || StringUtils.isEmpty(reviewInStorageDetails.getInvoiceType())
                    || StringUtils.isEmpty(reviewInStorageDetails.getSettlementType())
                    || StringUtils.isEmpty(reviewInStorageDetails.getOrNotExtension())
                    || reviewInStorageDetails.getProcurementNum() == null
                    || reviewInStorageDetails.getExpectedInStorageNum() == null
                    || reviewInStorageDetails.getActualInStorageNum() == null
                    || reviewInStorageDetails.getEffectiveEndTime() == null
                    || reviewInStorageDetails.getEffectiveStartTime() == null
                    || StringUtils.isEmpty(reviewInStorageDetails.getFileName())
                    || StringUtils.isEmpty(reviewInStorageDetails.getFileUrl())) {
                log.error("【修改入库单】{}，请求参数：{}", "参数空异常", entity);
                return result.error("请完善必填项！");
            }
            if (reviewInStorageDetails.getEffectiveStartTime().after(reviewInStorageDetails.getEffectiveEndTime())) {
                log.error("【修改入库单】{}，请求参数：{}", "时间参数错误！", entity);
                return result.error("时间参数错误！");
            }
            reviewInStorageDetails.setId(null);
            reviewInStorageDetails.setReviewInStorageId(entity.getId());
        }
        baseService.updateById(entity);
        //删除
        reviewInStorageDetailsServiceImpl.remove(
                new QueryWrapper<ReviewInStorageDetails>().lambda().eq(
                        ReviewInStorageDetails::getReviewInStorageId, entity.getId()
                )
        );
        // 新增
        reviewInStorageDetailsServiceImpl.saveBatch(entity.getDetail());
        return result.success("操作成功！");
    }

    /**
     * 查看入库单
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/select_review_in_storage", method = RequestMethod.POST)
    @ApiOperation(value = "查看入库单", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<ReviewInStorage> selectReviewInStorage(@RequestBody ReviewInStorage entity) {
        log.info("【查看入库单】{},请求参数：{}", "接口请求", entity);
        if (entity == null || entity.getId() == null) {
            log.error("【查看入库单】{}，请求参数：{}", "参数空异常", entity);
            return result.error("请完善必填项！");
        }
        ReviewInStorage reviewInStorage = baseService.getById(entity.getId());
        if (reviewInStorage == null) {
            log.error("【查看入库单】{}，请求参数：{}", "入库单不存在！", entity);
            return result.error("入库单不存在，请选择正确的入库单进行操作！");
        }
        List<ReviewInStorageDetails> details = reviewInStorageDetailsServiceImpl.list(
                new QueryWrapper<ReviewInStorageDetails>().lambda().eq(
                        ReviewInStorageDetails::getReviewInStorageId, entity.getId()
                )
        );
        reviewInStorage.setDetail(details);
        return result.success("请求成功！", reviewInStorage);
    }

    /**
     * 修改入库单状态（可批量操作）
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/update_review_in_storage_review_status", method = RequestMethod.POST)
    @ApiOperation(value = "修改入库单状态（可批量操作）", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<ReviewInStorage> updateReviewInStorageReviewStatus(@RequestBody ReviewInStorage entity) {
        if (entity == null
                || entity.getIdList() == null
                || entity.getIdList().size() == 0
                || entity.getReviewId() == null
                || StringUtils.isEmpty(entity.getReviewStatus())
                || StringUtils.isEmpty(entity.getReviewName())) {
            log.error("【修改入库单状态】{}，请求参数：{}", "参数空异常", entity);
            return result.error("请完善必填项！");
        }
        if (!DBDictionaryEnumManager.review_2.getkey().equals(entity.getReviewStatus())
                && !DBDictionaryEnumManager.review_1.getkey().equals(entity.getReviewStatus())
                && !DBDictionaryEnumManager.review_0.getkey().equals(entity.getReviewStatus())) {
            log.error("【修改入库单状态】{}，请求参数：{}", "审核参数不正确！", entity);
            return result.error("审核参数不正确！");
        }
        //查询所有的入库单
        List<ReviewInStorage> reviewInStorages = baseService.list(
                new QueryWrapper<ReviewInStorage>().lambda().in(
                        ReviewInStorage::getId, entity.getIdList()
                )
        );
        if (reviewInStorages == null || reviewInStorages.size() != entity.getIdList().size()) {
            log.error("【修改入库单状态】{}，请求参数：{}", "入库单异常，不存在！", entity);
            return result.error("入库单异常，请选择正确的入库单进行操作！");
        }
        for (ReviewInStorage reviewInStorage : reviewInStorages) {
            if (DBDictionaryEnumManager.review_0.getkey().equals(entity.getReviewStatus())) {
                if (!DBDictionaryEnumManager.review_3.getkey().equals(reviewInStorage.getReviewStatus())) {
                    log.error("【修改入库单状态】{}，请求参数：{}", "入库单不提交入库！", entity);
                    return result.error("入库单不可审核，请选择正确的入库单进行操作！");
                }
            } else {
                if (!DBDictionaryEnumManager.review_0.getkey().equals(reviewInStorage.getReviewStatus())) {
                    log.error("【修改入库单状态】{}，请求参数：{}", "入库单不可审核！", entity);
                    return result.error("入库单不可审核，请选择正确的入库单进行操作！");
                }
                reviewInStorage.setReviewStatus(entity.getReviewStatus());
                reviewInStorage.setReviewId(entity.getReviewId());
                reviewInStorage.setReviewName(entity.getReviewName());
                reviewInStorage.setReviewTime(new Date());
            }
        }
        // 进行修改
        baseService.updateBatchById(reviewInStorages);
        return result.success("操作成功！");
    }

    /**
     * 删除入库单（可批量操作）
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/delect_review_in_storage", method = RequestMethod.POST)
    @ApiOperation(value = "删除入库单（可批量操作）", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<ReviewInStorage> delectReviewInStorage(@RequestBody ReviewInStorage entity) {
        if (entity == null || entity.getIdList() == null || entity.getIdList().size() == 0) {
            log.error("【删除入库单】{}，请求参数：{}", "参数空异常", entity);
            return result.error("请完善必填项！");
        }
        // 判断这些是否能删除
        List<ReviewInStorage> reviewInStorages = baseService.list(
                new QueryWrapper<ReviewInStorage>().lambda().in(
                        ReviewInStorage::getId, entity.getIdList()
                )
        );
        for (ReviewInStorage reviewInStorage : reviewInStorages) {
            if (!DBDictionaryEnumManager.review_0.getkey().equals(reviewInStorage.getReviewStatus())
                    && !DBDictionaryEnumManager.review_3.getkey().equals(reviewInStorage.getReviewStatus())) {
                log.error("【删除入库单】{}，请求参数：{}", "入库单不符合删除条件！", entity);
                return result.error("入库单不符合删除条件，请选择正确的入库订单进行操作！");
            }
        }
        baseService.remove(
                new QueryWrapper<ReviewInStorage>().lambda().in(
                        ReviewInStorage::getId, entity.getIdList()
                )
        );
        reviewInStorageDetailsServiceImpl.remove(
                new QueryWrapper<ReviewInStorageDetails>().lambda().in(
                        ReviewInStorageDetails::getReviewInStorageId, entity.getIdList()
                )
        );
        return result.success("操作成功！");

    }

}
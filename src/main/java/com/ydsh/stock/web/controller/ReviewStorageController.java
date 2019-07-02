/**
 * @filename:ReviewStorageController 2019-06-12 11:57:58
 * @project ydsh-saas-service-stock  V1.0
 * Copyright(c) 2020 <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a> Co. Ltd.
 * All right reserved.
 */
package com.ydsh.stock.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ydsh.generator.common.Const;
import com.ydsh.generator.common.JsonResult;
import com.ydsh.stock.common.db.DBKeyGenerator;
import com.ydsh.stock.common.enums.DBBusinessKeyTypeEnums;
import com.ydsh.stock.common.enums.DBDictionaryEnumManager;
import com.ydsh.stock.web.controller.base.AbstractController;
import com.ydsh.stock.web.entity.ReviewStorage;
import com.ydsh.stock.web.entity.ReviewStorageDetails;
import com.ydsh.stock.web.service.ReviewStorageDetailsService;
import com.ydsh.stock.web.service.ReviewStorageService;
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
 * <p>说明： 作废、延期、冻结、加密导出审核申请主表API接口层</P>
 *
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 */
@Api(description = "作废、延期、冻结、加密导出审核申请主表", value = "作废、延期、冻结、加密导出审核申请主表")
@RestController
@RequestMapping("/reviewStorage")
@Slf4j
public class ReviewStorageController extends AbstractController<ReviewStorageService, ReviewStorage> {
    /**
     * 子表数据库操作
     */
    @Autowired
    private ReviewStorageDetailsService reviewStorageDetailsServiceImpl;

    /**
     * 审核申请单新增
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/insert_review_storage", method = RequestMethod.POST)
    @ApiOperation(value = "审核申请单新增", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<ReviewStorage> insertReviewStorage(@RequestBody ReviewStorage entity) {
        log.info("【审核申请单新增】{},请求参数：{}", "接口请求", entity);
        if (entity == null) {
            log.error("【审核申请单新增】{},请求参数：{}", "参数空异常！", entity);
            return result.error("请完善必填项！");
        }
        if (StringUtils.isEmpty(entity.getReviewType())
                || StringUtils.isEmpty(entity.getCouponStatus())
                || StringUtils.isEmpty(entity.getCreateName())
                || entity.getCreateId() == null) {
            log.error("【审核申请单新增】{},请求参数：{}", "参数空异常！", entity);
            return result.error("请完善必填项！");
        }
        // 判断操作类型
        result = reviewStorageEmpty(entity);
        if (Const.CODE_FAILED.equals(result.getCode())) {
            return result;
        }
        // 判断操作类型
        switch (DBDictionaryEnumManager.getBykey(entity.getExpiredType())) {
            case coupon_code_review_1:
                // 作废申请 赋值
                entity.setReviewStorageNo(DBKeyGenerator.generatorDBKey(DBBusinessKeyTypeEnums.IA, null));
                baseService.save(entity);
                break;
            case coupon_code_review_2:
                // 延期申请
                entity.setReviewStorageNo(DBKeyGenerator.generatorDBKey(DBBusinessKeyTypeEnums.DA, null));
                baseService.save(entity);
                break;
            case coupon_code_review_3:
                // 冻结申请
                entity.setReviewStorageNo(DBKeyGenerator.generatorDBKey(DBBusinessKeyTypeEnums.FA, null));
                baseService.save(entity);
                for (ReviewStorageDetails storageDetails : entity.getDetail()) {
                    storageDetails.setReviewStorageId(entity.getId());
                }
                reviewStorageDetailsServiceImpl.saveBatch(entity.getDetail());
                break;
            case coupon_code_review_4:
                // 加密导出申请
                entity.setReviewStorageNo(DBKeyGenerator.generatorDBKey(DBBusinessKeyTypeEnums.EA, null));
                baseService.save(entity);
                for (ReviewStorageDetails storageDetails : entity.getDetail()) {
                    storageDetails.setReviewStorageId(entity.getId());
                }
                reviewStorageDetailsServiceImpl.saveBatch(entity.getDetail());
                break;
            default:
                break;
        }
        return result.success("操作成功！");
    }

    /**
     * 审核申请单
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/select_review_storage", method = RequestMethod.POST)
    @ApiOperation(value = "审核申请单（支持批量操作）", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<ReviewStorage> selectReviewStorage(@RequestBody ReviewStorage entity) {
        log.info("【审核申请单】{},请求参数：{}", "接口请求", entity);
        if (entity == null
                || entity.getIdList() == null
                || entity.getIdList().size() == 0
                || StringUtils.isEmpty(entity.getReviewStatus())
                || StringUtils.isEmpty(entity.getReviewName())
                || entity.getReviewId() == null) {
            log.error("【审核申请单新增】{},请求参数：{}", "参数空异常！", entity);
            return result.error("请完善必填项！");
        }
        if (!DBDictionaryEnumManager.review_1.getkey().equals(entity.getReviewStatus())
                && !DBDictionaryEnumManager.review_2.getkey().equals(entity.getReviewStatus())) {
            log.error("【审核申请单新增】{},请求参数：{}", "审核参数不正常！", entity);
            return result.error("审核状态不正确！");
        }
        // 判断原审核状态
        List<ReviewStorage> reviewStorages = baseService.list(
                new QueryWrapper<ReviewStorage>().lambda().in(
                        ReviewStorage::getId, entity.getIdList()
                )
        );
        if (reviewStorages.size() != entity.getIdList().size()) {
            log.error("【审核申请单新增】{},请求参数：{}", "审核单数量不一致，不存在！", entity);
            return result.error("审核单不存在，请选择正确的审核但进行操作！");
        }
        for (ReviewStorage reviewStorage : reviewStorages) {
            if (!DBDictionaryEnumManager.review_0.getkey().equals(reviewStorage.getReviewStatus())) {
                log.error("【审核申请单新增】{},请求参数：{}", "审核单已经审核过了！", entity);
                return result.error("审核单不可审核，请选择正确的审核单进行操作！");
            }
            reviewStorage.setReviewStatus(entity.getReviewStatus());
            reviewStorage.setReviewId(entity.getReviewId());
            reviewStorage.setReviewName(entity.getReviewName());
            reviewStorage.setReviewTime(new Date());
        }
        baseService.updateBatchById(reviewStorages);
        return result.success("操作成功！");
    }

    /**
     * 判空
     *
     * @param entity
     * @return
     */
    private static JsonResult<ReviewStorage> reviewStorageEmpty(ReviewStorage entity) {
        JsonResult<ReviewStorage> result = new JsonResult<ReviewStorage>();
        switch (DBDictionaryEnumManager.getBykey(entity.getReviewType())) {
            case coupon_code_review_1:
                // 作废申请
                if (entity.getStorageDetailsId() == null || entity.getGcsId() == null
                        || StringUtils.isEmpty(entity.getGcsName())
                        || StringUtils.isEmpty(entity.getGcsForm())) {
                    log.error("【审核申请单新增】{},请求参数：{}", "参数空异常！", entity);
                    return result.error("请完善必填项！");
                }
                break;
            case coupon_code_review_2:
                // 延期申请
                if (entity.getDetail() == null || entity.getDetail().size() == 0 || entity.getGcsId() == null
                        || StringUtils.isEmpty(entity.getGcsName())
                        || StringUtils.isEmpty(entity.getGcsForm())
                        || StringUtils.isEmpty(entity.getExpiredType())
                        || StringUtils.isEmpty(entity.getOrNotExport())
                        || StringUtils.isEmpty(entity.getExportName())
                        || StringUtils.isEmpty(entity.getExportPhone())) {
                    log.error("【审核申请单新增】{},请求参数：{}", "参数空异常！", entity);
                    return result.error("请完善必填项！");
                }
                if (DBDictionaryEnumManager.delay_type_2.getkey().equals(entity.getExpiredType())) {
                    //判断延期类型：原虚拟结束时间延期
                    if (entity.getVirtualEndTime() == null
                            || entity.getVirtualEndTime() == null
                            || entity.getVirtualEndTime().after(entity.getEffectiveEndTime())) {
                        log.error("【审核申请单新增】{},请求参数：{}", "参数时间错误，延期时间不能小于原本时间！", entity);
                        return result.error("请完善必填项！");
                    }
                } else {
                    // 原有效结束时间延期
                    if (entity.getEffectiveEndTime() == null
                            || entity.getFinalEndTime() == null
                            || entity.getFinalEndTime().after(entity.getEffectiveEndTime())) {
                        log.error("【审核申请单新增】{},请求参数：{}", "参数时间错误，延期时间不能小于原本时间！", entity);
                        return result.error("请完善必填项！");
                    }
                }
                // 判断子表数据
                for (ReviewStorageDetails reviewStorageDetails : entity.getDetail()) {
                    if (reviewStorageDetails.getStorageId() == null
                            || StringUtils.isEmpty(reviewStorageDetails.getCouponStatus())) {
                        log.error("【审核申请单新增】{},请求参数：{}", "参数空异常！", entity);
                        return result.error("请完善必填项！");
                    }
                }
                break;
            case coupon_code_review_3:
                // 冻结申请
                if (entity.getStorageDetailsId() == null
                        || entity.getGcsId() == null
                        || StringUtils.isEmpty(entity.getGcsName())
                        || StringUtils.isEmpty(entity.getGcsForm())) {
                    log.error("【审核申请单新增】{},请求参数：{}", "参数空异常！", entity);
                    return result.error("请完善必填项！");
                }
                if (!DBDictionaryEnumManager.coupon_code_1.getkey().equals(entity.getCouponStatus())) {
                    // 必须是已出库表的未验证数据才能操作
                    log.error("【审核申请单新增】{},请求参数：{}", "数据来源错误，非已出库表的数据不可以进行冻结操作！", entity);
                    return result.error("数据错误，请选择正确的数据进行操作！");
                }
                break;
            case coupon_code_review_4:
                // 加密导出申请
                if (StringUtils.isEmpty(entity.getOrNotExport())
                        || DBDictionaryEnumManager.yes.getkey().equals(entity.getOrNotExport())
                        || StringUtils.isEmpty(entity.getExportPhone())
                        || StringUtils.isEmpty(entity.getExportName())) {
                    log.error("【审核申请单新增】{},请求参数：{}", "参数空异常！", entity);
                    return result.error("请完善必填项！");
                }
                break;
            default:
                return result.error("类型错误！");
        }
        return result.success("成功！");
    }
}
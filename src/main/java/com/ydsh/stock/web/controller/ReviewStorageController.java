/**
 * @filename:ReviewStorageController 2019-06-12 11:57:58
 * @project ydsh-saas-service-stock  V1.0
 * Copyright(c) 2020 <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a> Co. Ltd.
 * All right reserved.
 */
package com.ydsh.stock.web.controller;

import com.ydsh.generator.common.JsonResult;
import com.ydsh.stock.common.enums.DBDictionaryEnumManager;
import com.ydsh.stock.web.controller.base.AbstractController;
import com.ydsh.stock.web.entity.ReviewStorage;
import com.ydsh.stock.web.entity.ReviewStorageDetails;
import com.ydsh.stock.web.service.ReviewStorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
     * 审核申请单新增（支持批量）
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/insert_review_storage", method = RequestMethod.POST)
    @ApiOperation(value = "审核申请单新增（支持批量）", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<ReviewStorage> insertReviewStorage(@RequestBody List<ReviewStorage> entity) {
        log.info("【审核申请单新增】{},请求参数：{}", "接口请求", entity);
        if (entity == null || entity.size() == 0) {
            log.error("【审核申请单新增】{},请求参数：{}", "参数空异常！", entity);
            return result.error("请完善必填项！");
        }
        for (ReviewStorage reviewStorage : entity) {
            if (StringUtils.isEmpty(reviewStorage.getReviewType()) || StringUtils.isEmpty(reviewStorage.getCouponStatus())) {
                log.error("【审核申请单新增】{},请求参数：{}", "参数空异常！", entity);
                return result.error("请完善必填项！");
            }
            // 判断操作类型
            switch (reviewStorage.getReviewType()) {
                case "1":
                    // 作废申请
                    if (reviewStorage.getStorageDetailsId() == null
                            || reviewStorage.getGcsId() == null
                            || StringUtils.isEmpty(reviewStorage.getGcsName())
                            || StringUtils.isEmpty(reviewStorage.getGcsForm())) {
                        log.error("【审核申请单新增】{},请求参数：{}", "参数空异常！", entity);
                        return result.error("请完善必填项！");
                    }
                    break;
                case "2":
                    // 延期申请
                    if (reviewStorage.getDetail() == null
                            || reviewStorage.getDetail().size() == 0
                            || reviewStorage.getGcsId() == null
                            || StringUtils.isEmpty(reviewStorage.getGcsName())
                            || StringUtils.isEmpty(reviewStorage.getGcsForm())
                            || StringUtils.isEmpty(reviewStorage.getExpiredType())
                            || StringUtils.isEmpty(reviewStorage.getOrNotExport())
                            || StringUtils.isEmpty(reviewStorage.getExportName())
                            || StringUtils.isEmpty(reviewStorage.getExportPhone())) {
                        log.error("【审核申请单新增】{},请求参数：{}", "参数空异常！", entity);
                        return result.error("请完善必填项！");
                    }
                    if (DBDictionaryEnumManager.delay_type_2.getkey().equals(reviewStorage.getExpiredType())) {
                        //判断延期类型：原虚拟结束时间延期
                        if (reviewStorage.getVirtualEndTime() == null
                                || reviewStorage.getVirtualEndTime() == null
                                || reviewStorage.getVirtualEndTime().after(reviewStorage.getEffectiveEndTime())) {
                            log.error("【审核申请单新增】{},请求参数：{}", "参数时间错误，延期时间不能小于原本时间！", entity);
                            return result.error("请完善必填项！");
                        }
                    } else {
                        // 原有效结束时间延期
                        if (reviewStorage.getEffectiveEndTime() == null
                                || reviewStorage.getFinalEndTime() == null
                                || reviewStorage.getFinalEndTime().after(reviewStorage.getEffectiveEndTime())) {
                            log.error("【审核申请单新增】{},请求参数：{}", "参数时间错误，延期时间不能小于原本时间！", entity);
                            return result.error("请完善必填项！");
                        }
                    }
                    // 判断子表数据
                    for (ReviewStorageDetails reviewStorageDetails : reviewStorage.getDetail()) {
                        if (reviewStorageDetails.getStorageId() == null
                                || StringUtils.isEmpty(reviewStorageDetails.getCouponStatus())) {
                            log.error("【审核申请单新增】{},请求参数：{}", "参数空异常！", entity);
                            return result.error("请完善必填项！");
                        }
                    }
                    break;
                case "3":
                    // 冻结申请
                    if (reviewStorage.getStorageDetailsId() == null
                            || reviewStorage.getGcsId() == null
                            || StringUtils.isEmpty(reviewStorage.getGcsName())
                            || StringUtils.isEmpty(reviewStorage.getGcsForm())) {
                        log.error("【审核申请单新增】{},请求参数：{}", "参数空异常！", entity);
                        return result.error("请完善必填项！");
                    }
                    if (!DBDictionaryEnumManager.coupon_code_1.getkey().equals(reviewStorage.getCouponStatus())) {
                        // 必须是已出库表的未验证数据才能操作
                        log.error("【审核申请单新增】{},请求参数：{}", "数据来源错误，非已出库表的数据不可以进行冻结操作！", entity);
                        return result.error("数据错误，请选择正确的数据进行操作！");
                    }
                    break;
                case "4":
                    // 加密导出申请
                    if (StringUtils.isEmpty(reviewStorage.getOrNotExport())
                            || DBDictionaryEnumManager.yes.getkey().equals(reviewStorage.getOrNotExport())
                            || StringUtils.isEmpty(reviewStorage.getExportPhone())
                            || StringUtils.isEmpty(reviewStorage.getExportName())) {
                        log.error("【审核申请单新增】{},请求参数：{}", "参数空异常！", entity);
                        return result.error("请完善必填项！");
                    }
                    break;
            }
        }
        // 判断正确，增加逻辑处理
        for (ReviewStorage reviewStorage : entity) {

        }
        return null;
    }
}
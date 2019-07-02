/**
 * @filename:ReviewStorageDetailsController 2019-06-12 11:57:59
 * @project ydsh-saas-service-stock  V1.0
 * Copyright(c) 2020 <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a> Co. Ltd.
 * All right reserved.
 */
package com.ydsh.stock.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ydsh.generator.common.JsonResult;
import com.ydsh.stock.web.controller.base.AbstractController;
import com.ydsh.stock.web.entity.ReviewStorageDetails;
import com.ydsh.stock.web.service.ReviewStorageDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>自定义方法写在这里</p>
 * <p>
 * <p>说明： 作废、延期、冻结、加密导出审核申请子表（关系型）API接口层</P>
 *
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 */
@Api(description = "作废、延期、冻结、加密导出审核申请子表（关系型）", value = "作废、延期、冻结、加密导出审核申请子表（关系型）")
@RestController
@RequestMapping("/reviewStorageDetails")
@Slf4j
public class ReviewStorageDetailsController extends AbstractController<ReviewStorageDetailsService, ReviewStorageDetails> {

    /**
     * 根据主表id 查询 子表信息
     *
     * @param entity
     * @return
     */
    @RequestMapping(value = "/select_review_storage_details", method = RequestMethod.POST)
    @ApiOperation(value = "根据申请单主表主键查询子表信息", notes = "作者：<a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>")
    public JsonResult<List<ReviewStorageDetails>> selectReviewStorageDetails(@RequestBody ReviewStorageDetails entity) {
        log.info("【根据申请单主表主键查询子表信息】{},请求参数：{}", "接口请求", entity);
        JsonResult<List<ReviewStorageDetails>> result = new JsonResult<List<ReviewStorageDetails>>();
        if (entity == null || entity.getReviewStorageId() == null) {
            log.error("【根据申请单主表主键查询子表信息】{},请求参数：{}", "参数空异常！", entity);
            return result.error("请完善必填项");
        }
        List<ReviewStorageDetails> reviewStorageDetails = baseService.list(
                new QueryWrapper<ReviewStorageDetails>().lambda().eq(
                        ReviewStorageDetails::getReviewStorageId, entity.getReviewStorageId()
                )
        );
        if (reviewStorageDetails == null || reviewStorageDetails.size() == 0) {
            log.error("【根据申请单主表主键查询子表信息】{},请求参数：{}", "子表数据不存在，数据异常！", entity);
            return result.error("数据不存在，异常操作！");
        }
        return result.success("操作成功！", reviewStorageDetails);
    }

}
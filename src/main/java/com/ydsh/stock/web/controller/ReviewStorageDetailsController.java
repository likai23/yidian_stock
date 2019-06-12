/**
 * @filename:ReviewStorageDetailsController 2019-06-12 11:57:59
 * @project ydsh-saas-service-stock  V1.0
 * Copyright(c) 2020 <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a> Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.stock.web.controller;

import com.ydsh.stock.web.controller.base.AbstractController;
import com.ydsh.stock.web.entity.ReviewStorageDetails;
import com.ydsh.stock.web.service.ReviewStorageDetailsService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**   
 * <p>自定义方法写在这里</p>
 * 
 * <p>说明： 作废、延期、冻结、加密导出审核申请子表（关系型）API接口层</P>
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 *
 */
@Api(description = "作废、延期、冻结、加密导出审核申请子表（关系型）",value="作废、延期、冻结、加密导出审核申请子表（关系型）" )
@RestController
@RequestMapping("/reviewStorageDetails")
@Slf4j
public class ReviewStorageDetailsController extends AbstractController<ReviewStorageDetailsService,ReviewStorageDetails>{
	
}
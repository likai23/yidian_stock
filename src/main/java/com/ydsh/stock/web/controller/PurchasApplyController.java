/**
 * @filename:PurchasApplyController 2019-06-12 11:57:58
 * @project ydsh-saas-service-stock  V1.0
 * Copyright(c) 2020 <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a> Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.stock.web.controller;

import com.ydsh.stock.web.controller.base.AbstractController;
import com.ydsh.stock.web.entity.PurchasApply;
import com.ydsh.stock.web.service.PurchasApplyService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**   
 * <p>自定义方法写在这里</p>
 * 
 * <p>说明： 采购申请主表API接口层</P>
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 *
 */
@Api(description = "采购申请主表",value="采购申请主表" )
@RestController
@RequestMapping("/purchasApply")
@Slf4j
public class PurchasApplyController extends AbstractController<PurchasApplyService,PurchasApply>{
	
}
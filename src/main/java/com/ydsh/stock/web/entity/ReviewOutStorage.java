/**
 * @filename:ReviewOutStorage 2019-07-09 08:35:06
 * @project ydsh-saas-service-stock  V1.0
 * Copyright(c) 2020 <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a> Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.stock.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**   
 * <p>代码自动生成，请勿修改</p>
 * 
 * <p>说明： 出库单申请主表实体类</P>
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReviewOutStorage implements Serializable {

	private static final long serialVersionUID = 1562675706319L;
	
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private Long id;
	@ApiModelProperty(name = "createId" , value = "提交人")
	private Long createId;
	@ApiModelProperty(name = "reviewId" , value = "处理人/审核人")
	private Long reviewId;
	@ApiModelProperty(name = "reviewStatus" , value = "审批状态")
	private String reviewStatus;
	@ApiModelProperty(name = "reviewRemarks" , value = "审核备注/审批意见")
	private String reviewRemarks;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "createTime" , value = "提交时间/创建时间（自动生成）")
	private Date createTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "reviewTime" , value = "审核时间")
	private Date reviewTime;
	@ApiModelProperty(name = "customerId" , value = "客户id")
	private Long customerId;
	@ApiModelProperty(name = "orderId" , value = "订单id")
	private Long orderId;
	@ApiModelProperty(name = "reviewOutStorageNo" , value = "出库单号")
	private String reviewOutStorageNo;
	@ApiModelProperty(name = "reviewOutStorageType" , value = "出库类型")
	private String reviewOutStorageType;
	@ApiModelProperty(name = "num" , value = "出库数量")
	private Integer num;
	@ApiModelProperty(name = "totalAmount" , value = "总金额")
	private Long totalAmount;
	@ApiModelProperty(name = "remarks" , value = "出库备注")
	private String remarks;
	@ApiModelProperty(name = "createName" , value = "提交人姓名")
	private String createName;
	@ApiModelProperty(name = "reviewName" , value = "审核人姓名")
	private String reviewName;
}

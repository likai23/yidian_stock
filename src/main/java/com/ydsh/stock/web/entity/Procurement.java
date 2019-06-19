/**
 * @filename:Procurement 2019-06-18 04:10:13
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
 * <p>说明： 采购订单主表实体类</P>
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Procurement implements Serializable {

	private static final long serialVersionUID = 1560845413120L;
	
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private Long id;
	@ApiModelProperty(name = "procurementNo" , value = "PA +年份日期+两位数字递增  PAS2018020201")
	private String procurementNo;
	@ApiModelProperty(name = "createId" , value = "提交人")
	private Long createId;
	@ApiModelProperty(name = "reviewId" , value = "处理人/审核人id")
	private Long reviewId;
	@ApiModelProperty(name = "supplierId" , value = "供应商id")
	private Long supplierId;
	@ApiModelProperty(name = "supplierNo" , value = "供应商编号")
	private String supplierNo;
	@ApiModelProperty(name = "supplierName" , value = "供应商名称")
	private String supplierName;
	@ApiModelProperty(name = "inStorageId" , value = "入库单id")
	private Long inStorageId;
	@ApiModelProperty(name = "procurementStype" , value = "采购类型 默认线下")
	private String procurementStype;
	@ApiModelProperty(name = "totalNum" , value = "采购总数量")
	private Integer totalNum;
	@ApiModelProperty(name = "totalAmount" , value = "总金额")
	private Long totalAmount;
	@ApiModelProperty(name = "inStorageNo" , value = "入库单号")
	private String inStorageNo;
	@ApiModelProperty(name = "remarks" , value = "采购备注")
	private String remarks;
	@ApiModelProperty(name = "reviewRemarks" , value = "审批意见")
	private String reviewRemarks;
	@ApiModelProperty(name = "reviewStatus" , value = "采购单状态（审核状态）")
	private String reviewStatus;
	@ApiModelProperty(name = "createName" , value = "提交人姓名")
	private String createName;
	@ApiModelProperty(name = "reviewName" , value = "审核人姓名")
	private String reviewName;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "createTime" , value = "提交时间/创建时间（自动生成）")
	private Date createTime;
}

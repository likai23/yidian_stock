/**
 * @filename:PurchasApplyDetail 2019-06-17 08:19:30
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
 * <p>说明： 采购申请子表（明细）实体类</P>
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PurchasApplyDetail implements Serializable {

	private static final long serialVersionUID = 1560773970263L;
	
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private Long id;
	@ApiModelProperty(name = "createId" , value = "提交人")
	private Long createId;
	@ApiModelProperty(name = "reviewId" , value = "处理人/审核人")
	private Long reviewId;
	@ApiModelProperty(name = "purchasApplyId" , value = "采购申请主表ID")
	private Long purchasApplyId;
	@ApiModelProperty(name = "gcsId" , value = "sku id")
	private Long gcsId;
	@ApiModelProperty(name = "gcsNo" , value = "编号")
	private String gcsNo;
	@ApiModelProperty(name = "gcsSku" , value = "sku")
	private String gcsSku;
	@ApiModelProperty(name = "gcsName" , value = "商品名称")
	private String gcsName;
	@ApiModelProperty(name = "gcsDenomination" , value = "面值")
	private Long gcsDenomination;
	@ApiModelProperty(name = "gcsType" , value = "商品属性")
	private String gcsType;
	@ApiModelProperty(name = "gcsNature" , value = "商品性质")
	private String gcsNature;
	@ApiModelProperty(name = "productNum" , value = "数量")
	private Integer productNum;
	@ApiModelProperty(name = "reviewStatus" , value = "审核状态 ： 待处理、暂不采购、无需采购")
	private String reviewStatus;
	@ApiModelProperty(name = "createName" , value = "提交人姓名")
	private String createName;
	@ApiModelProperty(name = "reviewName" , value = "审核人姓名")
	private String reviewName;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "createTime" , value = "提交时间/创建时间（自动生成）")
	private Date createTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "reviewTime" , value = "处理时间/审核时间")
	private Date reviewTime;
	@ApiModelProperty(name = "remarks" , value = "备注")
	private String remarks;
}

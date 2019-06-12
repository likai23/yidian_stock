/**
 * @filename:ProcurementDetail 2019-06-12 11:57:58
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
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**   
 * <p>代码自动生成，请勿修改</p>
 * 
 * <p>说明： 采购订单子表（明细）实体类</P>
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ProcurementDetail implements Serializable {

	private static final long serialVersionUID = 1560311878332L;
	
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private Long id;
	@ApiModelProperty(name = "procurementId" , value = "采购申请主表ID")
	private Long procurementId;
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
	@ApiModelProperty(name = "taxRate" , value = "税率")
	private Integer taxRate;
	@ApiModelProperty(name = "supplyPrice" , value = "供应价")
	private Long supplyPrice;
	@ApiModelProperty(name = "invoiceType" , value = "发票类型")
	private String invoiceType;
	@ApiModelProperty(name = "settlementType" , value = "结算方式")
	private String settlementType;
	@ApiModelProperty(name = "orNotExtension" , value = "是否能延期")
	private String orNotExtension;
	@ApiModelProperty(name = "productNum" , value = "数量")
	private Integer productNum;
	@ApiModelProperty(name = "remarks" , value = "备注")
	private String remarks;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "createTime" , value = "提交时间/创建时间（自动生成）")
	private Date createTime;
}

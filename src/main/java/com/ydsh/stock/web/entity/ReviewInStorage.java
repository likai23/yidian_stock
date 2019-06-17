/**
 * @filename:ReviewInStorage 2019-06-17 08:19:30
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
 * <p>说明： 入库单申请主表实体类</P>
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReviewInStorage implements Serializable {

	private static final long serialVersionUID = 1560773970346L;
	
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private Long id;
	@ApiModelProperty(name = "reviewInStorageNo" , value = "入库单号")
	private String reviewInStorageNo;
	@ApiModelProperty(name = "purchasingApplyId" , value = "采购单id")
	private Long purchasingApplyId;
	@ApiModelProperty(name = "purchasingApplyNo" , value = "采购单号")
	private String purchasingApplyNo;
	@ApiModelProperty(name = "supplierId" , value = "供应商id")
	private Long supplierId;
	@ApiModelProperty(name = "supplierName" , value = "供应商名称")
	private String supplierName;
	@ApiModelProperty(name = "reviewStatus" , value = "入库单状态/审核状态")
	private String reviewStatus;
	@ApiModelProperty(name = "reviewInStorageType" , value = "入库单类型")
	private String reviewInStorageType;
	@ApiModelProperty(name = "content" , value = "入库备注")
	private String content;
	@ApiModelProperty(name = "reviewRemarks" , value = "审核备注")
	private String reviewRemarks;
	@ApiModelProperty(name = "procurementNum" , value = "采购数量")
	private Integer procurementNum;
	@ApiModelProperty(name = "actualNum" , value = "实际入库数量")
	private Integer actualNum;
	@ApiModelProperty(name = "createId" , value = "提交人")
	private Long createId;
	@ApiModelProperty(name = "reviewId" , value = "处理人/审核人")
	private Long reviewId;
	@ApiModelProperty(name = "createName" , value = "提交人姓名")
	private String createName;
	@ApiModelProperty(name = "reviewName" , value = "审核人姓名")
	private String reviewName;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "reviewTime" , value = "审核时间")
	private Date reviewTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "createTime" , value = "提交时间/创建时间（自动生成）")
	private Date createTime;
	@ApiModelProperty(name = "remarks" , value = "备注")
	private Long remarks;
}

/**
 * @filename:ReviewInStorageDetails 2019-06-17 08:19:30
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
 * <p>说明： 入库单子表（明细）实体类</P>
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReviewInStorageDetails implements Serializable {

	private static final long serialVersionUID = 1560773970427L;
	
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private Long id;
	@ApiModelProperty(name = "reviewInStorageId" , value = "入库申请主表id")
	private Long reviewInStorageId;
	@ApiModelProperty(name = "gcsId" , value = "sku id")
	private Long gcsId;
	@ApiModelProperty(name = "supplyPrice" , value = "供应价")
	private Long supplyPrice;
	@ApiModelProperty(name = "taxRate" , value = "税率")
	private Integer taxRate;
	@ApiModelProperty(name = "invoiceType" , value = "发票类型")
	private String invoiceType;
	@ApiModelProperty(name = "settlementType" , value = "结算方式")
	private String settlementType;
	@ApiModelProperty(name = "orNotExtension" , value = "是否能延期")
	private String orNotExtension;
	@ApiModelProperty(name = "procurementNum" , value = "采购数量")
	private Integer procurementNum;
	@ApiModelProperty(name = "expectedInStorageNum" , value = "入库数量")
	private Integer expectedInStorageNum;
	@ApiModelProperty(name = "actualInStorageNum" , value = "实际导入数量")
	private Integer actualInStorageNum;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "effectiveStartTime" , value = "有效开始时间")
	private Date effectiveStartTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "effectiveEndTime" , value = "有效结束时间")
	private Date effectiveEndTime;
	@ApiModelProperty(name = "fileName" , value = "券码（文件名称）")
	private String fileName;
	@ApiModelProperty(name = "fileUrl" , value = "文件url")
	private String fileUrl;
	@ApiModelProperty(name = "content" , value = "明细备注")
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "createTime" , value = "提交时间/创建时间（自动生成）")
	private Date createTime;
	@ApiModelProperty(name = "remarks" , value = "备注")
	private Long remarks;
}

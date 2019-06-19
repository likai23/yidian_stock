/**
 * @filename:ReviewStorageDetails 2019-06-18 04:10:14
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
 * <p>说明： 作废、延期、冻结、加密导出审核申请子表（关系型）实体类</P>
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReviewStorageDetails implements Serializable {

	private static final long serialVersionUID = 1560845414104L;
	
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private Long id;
	@ApiModelProperty(name = "reviewStorageId" , value = "审核申请表id")
	private Long reviewStorageId;
	@ApiModelProperty(name = "storageId" , value = "已出库id、未出库id")
	private Long storageId;
	@ApiModelProperty(name = "couponStatus" , value = "（未出库、已过期）决定未出库id 和 已出库id 的绑定值")
	private String couponStatus;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "createTime" , value = "提交时间/创建时间（自动生成）")
	private Date createTime;
	@ApiModelProperty(name = "remarks" , value = "备注")
	private Long remarks;
}

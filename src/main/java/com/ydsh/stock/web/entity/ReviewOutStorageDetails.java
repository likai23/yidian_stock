/**
 * @filename:ReviewOutStorageDetails 2019-06-18 04:10:13
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
 * <p>说明： 出库单申请子表（明细）实体类</P>
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 * 
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReviewOutStorageDetails implements Serializable {

	private static final long serialVersionUID = 1560845413955L;
	
	@TableId(value = "id", type = IdType.AUTO)
	@ApiModelProperty(name = "id" , value = "主键ID")
	private Long id;
	@ApiModelProperty(name = "reviewOutStorageId" , value = "出库申请主表id")
	private Long reviewOutStorageId;
	@ApiModelProperty(name = "gcsId" , value = "商品类目id")
	private Long gcsId;
	@ApiModelProperty(name = "specialSellPrice" , value = "客户商品合作价")
	private Long specialSellPrice;
	@ApiModelProperty(name = "num" , value = "数量")
	private Integer num;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@ApiModelProperty(name = "createTime" , value = "提交时间/创建时间（自动生成）")
	private Date createTime;
	@ApiModelProperty(name = "remarks" , value = "备注")
	private Long remarks;
}

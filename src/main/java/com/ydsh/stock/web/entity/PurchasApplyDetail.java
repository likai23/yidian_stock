/**
 * @filename:PurchasApplyDetail 2019-07-09 08:35:06
 * @project ydsh-saas-service-stock  V1.0
 * Copyright(c) 2020 <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a> Co. Ltd.
 * All right reserved.
 */
package com.ydsh.stock.web.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>代码自动生成，请勿修改</p>
 * <p>
 * <p>说明： 采购申请子表（明细）实体类</P>
 *
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PurchasApplyDetail implements Serializable {

    private static final long serialVersionUID = 1562675706091L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(name = "id", value = "主键ID")
    private Long id;
    @ApiModelProperty(name = "createId", value = "提交人")
    private Long createId;
    @ApiModelProperty(name = "reviewId", value = "处理人/审核人")
    private Long reviewId;
    @ApiModelProperty(name = "purchasApplyId", value = "采购申请主表ID")
    private Long purchasApplyId;
    @ApiModelProperty(name = "gcId", value = "sku id")
    private Long gcId;
    @ApiModelProperty(name = "gcNo", value = "编号")
    private String gcNo;
    @ApiModelProperty(name = "gcSku", value = "sku")
    private String gcSku;
    @ApiModelProperty(name = "gcName", value = "商品名称")
    private String gcName;
    @ApiModelProperty(name = "gcDenomination", value = "面值")
    private Long gcDenomination;
    @ApiModelProperty(name = "gcType", value = "商品属性")
    private String gcType;
    @ApiModelProperty(name = "gcNature", value = "商品性质")
    private String gcNature;
    @ApiModelProperty(name = "productNum", value = "数量")
    private Integer productNum;
    @ApiModelProperty(name = "reviewStatus", value = "审核状态 ： 待处理、暂不采购、无需采购")
    private String reviewStatus;
    @ApiModelProperty(name = "createName", value = "提交人姓名")
    private String createName;
    @ApiModelProperty(name = "reviewName", value = "审核人姓名")
    private String reviewName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "createTime", value = "提交时间/创建时间（自动生成）")
    private Date createTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "reviewTime", value = "处理时间/审核时间")
    private Date reviewTime;
    @ApiModelProperty(name = "remarks", value = "备注")
    private String remarks;
    @ApiModelProperty(name = "idList", value = "批量处理，id传参")
    @JSONField(serialize = false)
    @TableField(exist = false)
    private List<Long> idList;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JSONField(serialize = false)
    @ApiModelProperty(name = "createStartTime", value = "提交开始时间")
    @TableField(exist = false)
    private Date createStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JSONField(serialize = false)
    @ApiModelProperty(name = "createEndtTime", value = "提交结束时间")
    @TableField(exist = false)
    private Date createEndTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JSONField(serialize = false)
    @ApiModelProperty(name = "reviewStartTime", value = "审核开始时间")
    @TableField(exist = false)
    private Date reviewStartTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JSONField(serialize = false)
    @ApiModelProperty(name = "reviewEndTime", value = "审核结束时间")
    @TableField(exist = false)
    private Date reviewEndTime;
}

/**
 * @filename:ReviewStorage 2019-07-09 08:35:06
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
 * <p>说明： 作废、延期、冻结、加密导出审核申请主表实体类</P>
 *
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ReviewStorage implements Serializable {

    private static final long serialVersionUID = 1562675706472L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(name = "id", value = "主键ID")
    private Long id;
    @ApiModelProperty(name = "reviewStorageNo", value = "业务主键，不同的类型规则不同")
    private String reviewStorageNo;
    @ApiModelProperty(name = "storageDetailsId", value = "已出库id、未出库id")
    private Long storageDetailsId;
    @ApiModelProperty(name = "gcId", value = "skuId")
    private Long gcId;
    @ApiModelProperty(name = "gcName", value = "商品名称")
    private String gcName;
    @ApiModelProperty(name = "gcForm", value = "券码形式（商品形式）")
    private String gcForm;
    @ApiModelProperty(name = "afterSaleOderId", value = "售后订单编号")
    private Long afterSaleOderId;
    @ApiModelProperty(name = "expiredType", value = "延期类型：原有效结束时间延期、原虚拟结束时间延期")
    private String expiredType;
    @ApiModelProperty(name = "reviewType", value = "（作废、延期）")
    private String reviewType;
    @ApiModelProperty(name = "reviewContent", value = "申请说明")
    private String reviewContent;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "effectiveEndTime", value = "原有效结束时间")
    private Date effectiveEndTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "virtualEndTime", value = "原虚拟结束时间")
    private Date virtualEndTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "finalEndTime", value = "延期后的最终结束时间")
    private Date finalEndTime;
    @ApiModelProperty(name = "couponStatus", value = "（未出库、已过期）决定未出库id 和 已出库id 的绑定值")
    private String couponStatus;
    @ApiModelProperty(name = "reviewStorageNum", value = "子表券码数量")
    private Integer reviewStorageNum;
    @ApiModelProperty(name = "isExport", value = "是否导出")
    private String isExport;
    @ApiModelProperty(name = "exportName", value = "导码人")
    private String exportName;
    @ApiModelProperty(name = "exportPhone", value = "导出人手机号码")
    private String exportPhone;
    @ApiModelProperty(name = "reviewStatus", value = "审核状态")
    private String reviewStatus;
    @ApiModelProperty(name = "reviewRemarks", value = "审核备注")
    private String reviewRemarks;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "reviewTime", value = "审核时间")
    private Date reviewTime;
    @ApiModelProperty(name = "createId", value = "提交人")
    private Long createId;
    @ApiModelProperty(name = "reviewId", value = "处理人/审核人")
    private Long reviewId;
    @ApiModelProperty(name = "createName", value = "提交人姓名")
    private String createName;
    @ApiModelProperty(name = "reviewName", value = "审核人姓名")
    private String reviewName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "createTime", value = "提交时间/创建时间（自动生成）")
    private Date createTime;
    @ApiModelProperty(name = "remarks", value = "备注")
    private String remarks;
    @ApiModelProperty(name = "detail", value = "子表数据")
    @JSONField(serialize = false)
    @TableField(exist = false)
    private List<ReviewStorageDetails> detail;
    @ApiModelProperty(name = "idList", value = "批量处理，id传参")
    @JSONField(serialize = false)
    @TableField(exist = false)
    private List<Long> idList;
}

/**
 * @filename:PurchasApply 2019-07-09 08:35:06
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
 * <p>说明： 采购申请主表实体类</P>
 *
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PurchasApply implements Serializable {

    private static final long serialVersionUID = 1562675706016L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(name = "id", value = "主键ID")
    private Long id;
    @ApiModelProperty(name = "purchasApplyNo", value = "业务主键")
    private String purchasApplyNo;
    @ApiModelProperty(name = "productNum", value = "采购总数量")
    private Integer productNum;
    @ApiModelProperty(name = "createId", value = "提交人")
    private Long createId;
    @ApiModelProperty(name = "createName", value = "提交人姓名")
    private String createName;
    @ApiModelProperty(name = "remarks", value = "申请采购说明")
    private String remarks;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(name = "createTime", value = "提交时间/创建时间（自动生成）")
    private Date createTime;
    @ApiModelProperty(name = "detail", value = "子表数据")
    @JSONField(serialize = false)
    @TableField(exist = false)
    private List<PurchasApplyDetail> detail;
    @ApiModelProperty(name = "type", value = "查询详情 查询：0 修改：1")
    @JSONField(serialize = false)
    @TableField(exist = false)
    private Integer type;
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
}

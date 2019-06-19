/**
 * @filename:PurchasApplyDao 2019-06-12 11:57:58
 * @project ydsh-saas-service-stock  V1.0
 * Copyright(c) 2020 <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a> Co. Ltd.
 * All right reserved.
 */
package com.ydsh.stock.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ydsh.stock.web.entity.PurchasApply;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>自定义mapper写在这里</p>
 * <p>
 * <p>说明： 采购申请主表数据访问层</p>
 *
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 */
@Mapper
public interface PurchasApplyDao extends BaseMapper<PurchasApply> {
    /**
     * 增加采购主表信息，并且返回主键id
     *
     * @param entity
     * @return
     */
    PurchasApply saveReId(PurchasApply entity);
}

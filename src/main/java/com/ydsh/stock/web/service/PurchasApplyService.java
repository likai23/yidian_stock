/**
 * @filename:PurchasApplyService 2019-06-12 11:57:58
 * @project ydsh-saas-service-stock  V1.0
 * Copyright(c) 2020 <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a> Co. Ltd.
 * All right reserved.
 */
package com.ydsh.stock.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ydsh.stock.web.entity.PurchasApply;

/**
 * <p>自定义service写在这里</p>
 *
 * <p>说明： 采购申请主表服务层</P>
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 *
 */
public interface PurchasApplyService extends IService<PurchasApply> {
    /**
     * 增加采购主表信息，并且返回主键id
     * @param entity
     * @return
     */
    PurchasApply saveReId(PurchasApply entity);
}
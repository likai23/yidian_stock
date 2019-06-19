/**
 * @filename:PurchasApplyServiceImpl 2019-06-12 11:57:58
 * @project ydsh-saas-service-stock  V1.0
 * Copyright(c) 2018 <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a> Co. Ltd.
 * All right reserved.
 */
package com.ydsh.stock.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydsh.stock.web.dao.PurchasApplyDao;
import com.ydsh.stock.web.entity.PurchasApply;
import com.ydsh.stock.web.service.PurchasApplyService;
import org.springframework.stereotype.Service;

/**
 * <p>自定义serviceImpl写在这里</p>
 * <p>
 * <p>说明： 采购申请主表服务实现层</P>
 *
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 */
@Service
public class PurchasApplyServiceImpl extends ServiceImpl<PurchasApplyDao, PurchasApply> implements PurchasApplyService {
    /**
     * 增加采购主表信息，并且返回主键id
     *
     * @param entity
     * @return
     */
    @Override
    public PurchasApply saveReId(PurchasApply entity) {
        return baseMapper.saveReId(entity);
    }
}
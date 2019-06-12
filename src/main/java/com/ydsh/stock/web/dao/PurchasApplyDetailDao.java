/**
 * @filename:PurchasApplyDetailDao 2019-06-12 11:57:58
 * @project ydsh-saas-service-stock  V1.0
 * Copyright(c) 2020 <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a> Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.stock.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ydsh.stock.web.entity.PurchasApplyDetail;
import org.apache.ibatis.annotations.Mapper;

/**   
 * <p>自定义mapper写在这里</p>
 * 
 * <p>说明： 采购申请子表（明细）数据访问层</p>
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 * 
 */
@Mapper
public interface PurchasApplyDetailDao extends BaseMapper<PurchasApplyDetail> {
	
}

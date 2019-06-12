/**
 * @filename:ProcurementServiceImpl 2019-06-12 11:57:57
 * @project ydsh-saas-service-stock  V1.0
 * Copyright(c) 2018 <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a> Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.stock.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydsh.stock.web.dao.ProcurementDao;
import com.ydsh.stock.web.entity.Procurement;
import com.ydsh.stock.web.service.ProcurementService;
import org.springframework.stereotype.Service;

/**   
 * <p>自定义serviceImpl写在这里</p>
 * 
 * <p>说明： 采购订单主表服务实现层</P>
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 * 
 */
@Service
public class ProcurementServiceImpl  extends ServiceImpl<ProcurementDao, Procurement> implements ProcurementService  {
	
}
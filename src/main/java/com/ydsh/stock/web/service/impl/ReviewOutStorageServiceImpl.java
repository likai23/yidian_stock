/**
 * @filename:ReviewOutStorageServiceImpl 2019-06-12 11:57:58
 * @project ydsh-saas-service-stock  V1.0
 * Copyright(c) 2018 <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a> Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.stock.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ydsh.stock.web.dao.ReviewOutStorageDao;
import com.ydsh.stock.web.entity.ReviewOutStorage;
import com.ydsh.stock.web.service.ReviewOutStorageService;
import org.springframework.stereotype.Service;

/**   
 * <p>自定义serviceImpl写在这里</p>
 * 
 * <p>说明： 出库单申请主表服务实现层</P>
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 * 
 */
@Service
public class ReviewOutStorageServiceImpl  extends ServiceImpl<ReviewOutStorageDao, ReviewOutStorage> implements ReviewOutStorageService  {
	
}
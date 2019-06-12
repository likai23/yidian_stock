/**
 * @filename:ReviewInStorageDao 2019-06-12 11:57:58
 * @project ydsh-saas-service-stock  V1.0
 * Copyright(c) 2020 <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a> Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.stock.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ydsh.stock.web.entity.ReviewInStorage;
import org.apache.ibatis.annotations.Mapper;

/**   
 * <p>自定义mapper写在这里</p>
 * 
 * <p>说明： 入库单申请主表数据访问层</p>
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 * 
 */
@Mapper
public interface ReviewInStorageDao extends BaseMapper<ReviewInStorage> {
	
}

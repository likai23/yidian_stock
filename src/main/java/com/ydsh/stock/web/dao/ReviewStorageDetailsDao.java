/**
 * @filename:ReviewStorageDetailsDao 2019-06-12 11:57:59
 * @project ydsh-saas-service-stock  V1.0
 * Copyright(c) 2020 <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a> Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.stock.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ydsh.stock.web.entity.ReviewStorageDetails;
import org.apache.ibatis.annotations.Mapper;

/**   
 * <p>自定义mapper写在这里</p>
 * 
 * <p>说明： 作废、延期、冻结、加密导出审核申请子表（关系型）数据访问层</p>
 * @version: V1.0
 * @author: <a href=mailto:yangyanrui@yidianlife.com>xiaoyang</a>
 * 
 */
@Mapper
public interface ReviewStorageDetailsDao extends BaseMapper<ReviewStorageDetails> {
	
}

package com.shop_online.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop_online.entity.SysManager;
import com.shop_online.query.SysManagerQuery;
import com.shop_online.vo.SysManagerVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author ycshang
 * @since 2023-11-28
 */
public interface SysManagerMapper extends BaseMapper<SysManager> {

	default SysManager getByUsername(String username) {
		return this.selectOne(new LambdaQueryWrapper<SysManager>().eq(SysManager::getUsername, username));
	}

	List<SysManagerVO> getManagerPage(Page<SysManagerVO> page, @Param("query") SysManagerQuery query);

}

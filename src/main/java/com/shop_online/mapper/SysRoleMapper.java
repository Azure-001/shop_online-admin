package com.shop_online.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shop_online.entity.SysRole;
import com.shop_online.query.SysRoleQuery;
import com.shop_online.vo.SysRoleVO;
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
public interface SysRoleMapper extends BaseMapper<SysRole> {
	List<SysRoleVO> getRolePage(Page<SysRoleVO> page, @Param("query") SysRoleQuery query);

}

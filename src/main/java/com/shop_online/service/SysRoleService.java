package com.shop_online.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop_online.common.result.PageResult;
import com.shop_online.entity.SysRole;
import com.shop_online.query.SysRoleQuery;
import com.shop_online.vo.SysRoleVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ycshang
 * @since 2023-11-28
 */
public interface SysRoleService extends IService<SysRole> {
	PageResult<SysRoleVO> page(SysRoleQuery query);

	List<SysRoleVO> getList(SysRoleQuery query);

	void save(SysRoleVO vo);

	void update(SysRoleVO vo);

	void delete(List<Integer> idList);

}

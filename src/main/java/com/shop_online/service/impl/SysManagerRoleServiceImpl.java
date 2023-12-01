package com.shop_online.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop_online.common.exception.ServerException;
import com.shop_online.entity.SysManagerRole;
import com.shop_online.mapper.SysManagerRoleMapper;
import com.shop_online.service.SysManagerRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ycshang
 * @since 2023-11-28
 */
@Service
public class SysManagerRoleServiceImpl extends ServiceImpl<SysManagerRoleMapper, SysManagerRole> implements SysManagerRoleService {

	@Override
	public void saveOrUpdate(Integer managerId, Integer roleId) {
		SysManagerRole sysManagerRole = baseMapper.selectOne(new LambdaQueryWrapper<SysManagerRole>()
				.eq(SysManagerRole::getManagerId, managerId));
		if (sysManagerRole == null) {
			sysManagerRole = new SysManagerRole();
			sysManagerRole.setManagerId(managerId);
		}
		sysManagerRole.setRoleId(roleId);
		saveOrUpdate(sysManagerRole);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void removeByManagerId(List<Integer> idList) {
		baseMapper.delete(new LambdaQueryWrapper<SysManagerRole>()
				.in(SysManagerRole::getManagerId, idList));
	}

	@Override
	public SysManagerRole getByManagerId(Integer managerId) {
		SysManagerRole sysManagerRole = baseMapper.selectOne(new LambdaQueryWrapper<SysManagerRole>()
				.eq(SysManagerRole::getManagerId, managerId));
		if (sysManagerRole == null) {
			throw new ServerException("该管理员暂未绑定角色");
		}
		return sysManagerRole;
	}

	@Override
	public Integer getByRoleId(Integer roleId) {
		Integer count = Math.toIntExact(count(new LambdaQueryWrapper<SysManagerRole>().eq(SysManagerRole::getRoleId, roleId)));
		return count;
	}
}

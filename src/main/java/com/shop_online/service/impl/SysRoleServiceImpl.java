package com.shop_online.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop_online.common.exception.ServerException;
import com.shop_online.common.result.PageResult;
import com.shop_online.convert.SysRoleConvert;
import com.shop_online.entity.SysRole;
import com.shop_online.mapper.SysRoleMapper;
import com.shop_online.query.SysRoleQuery;
import com.shop_online.service.SysManagerRoleService;
import com.shop_online.service.SysRoleMenuService;
import com.shop_online.service.SysRoleService;
import com.shop_online.vo.SysRoleVO;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
	private SysRoleMenuService sysRoleMenuService;
	private SysManagerRoleService sysManagerRoleService;

	@Override
	public PageResult<SysRoleVO> page(SysRoleQuery query) {
		Page<SysRoleVO> page = new Page<>(query.getPage(), query.getLimit());
		List<SysRoleVO> list = baseMapper.getRolePage(page, query);
		return new PageResult<>(list, page.getTotal());
	}

	@Override
	public List<SysRoleVO> getList(SysRoleQuery query) {
		List<SysRole> entityList = baseMapper.selectList(new LambdaQueryWrapper<>());
		return SysRoleConvert.INSTANCE.convertList(entityList);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysRoleVO vo) {
		SysRole entity = SysRoleConvert.INSTANCE.convert(vo);
		// 保存角色
		baseMapper.insert(entity);
		// 保存角色菜单关系
		sysRoleMenuService.saveOrUpdate(entity.getPkId(), vo.getMenuIds());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysRoleVO vo) {
		SysRole entity = SysRoleConvert.INSTANCE.convert(vo);

		// 更新角色
		updateById(entity);

		// 更新角色菜单关系
		sysRoleMenuService.saveOrUpdate(entity.getPkId(), vo.getMenuIds());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(List<Integer> idList) {
		// 已经绑定管理员的角色不能删除
		for (Integer roleId : idList) {
			Integer count = sysManagerRoleService.getByRoleId(roleId);
			if (count > 0) {
				throw new ServerException("该角色绑定了管理员，请解绑后再删除");
			}
		}
		// 删除角色
		removeByIds(idList);
		// 删除角色菜单关系
		sysRoleMenuService.deleteByRoleIdList(idList);
	}

}

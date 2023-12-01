package com.shop_online.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop_online.entity.SysMenu;
import com.shop_online.query.SysMenuQuery;
import com.shop_online.security.user.ManagerDetail;
import com.shop_online.vo.SysMenuVO;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ycshang
 * @since 2023-11-28
 */
public interface SysMenuService extends IService<SysMenu> {

	Set<String> getManagerAuthority(ManagerDetail manager);

	/**
	 * 用户菜单列表
	 *
	 * @param type 菜单类型
	 */
	List<SysMenuVO> getManagerMenuList(ManagerDetail manager, String type);

	/**
	 * 菜单列表
	 *
	 * @param query 菜单筛选
	 */
	List<SysMenuVO> getMenuList(SysMenuQuery query);

	List<SysMenu> getFormMenuList();

	SysMenuVO infoById(Integer id);

	void save(SysMenuVO vo);


	void update(SysMenuVO vo);

	void delete(Integer id);

	List<SysMenuVO> getRoleFormMenuList();
}

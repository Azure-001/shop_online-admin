package com.shop_online.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop_online.common.constant.Constant;
import com.shop_online.common.exception.ServerException;
import com.shop_online.convert.SysMenuConvert;
import com.shop_online.entity.SysMenu;
import com.shop_online.enums.DeleteFlagEnum;
import com.shop_online.enums.MenuTypeEnum;
import com.shop_online.enums.SuperAdminEnum;
import com.shop_online.mapper.SysMenuMapper;
import com.shop_online.query.SysMenuQuery;
import com.shop_online.security.user.ManagerDetail;
import com.shop_online.service.SysMenuService;
import com.shop_online.service.SysRoleMenuService;
import com.shop_online.utils.TreeUtils;
import com.shop_online.vo.SysMenuVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final SysRoleMenuService sysRoleMenuService;

    @Override
    public Set<String> getManagerAuthority(ManagerDetail manager) {
        // 系统管理员，拥有最高权限
        List<String> authorityList;
        if (manager.getSuperAdmin().equals(SuperAdminEnum.YES.getValue())) {
            authorityList = baseMapper.getAuthorityList();
        } else {
            authorityList = baseMapper.getManagerAuthorityList(manager.getPkId());
        }

        // 用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String authority : authorityList) {
            if (StrUtil.isBlank(authority)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(authority.trim().split(",")));
        }

        return permsSet;
    }

    @Override
    public List<SysMenuVO> getManagerMenuList(ManagerDetail manager, String type) {
        List<SysMenu> menuList;
        // 系统管理员，拥有最高权限
        if (manager.getSuperAdmin().equals(SuperAdminEnum.YES.getValue())) {
            menuList = baseMapper.getMenuList(type, false);
        } else {
            menuList = baseMapper.getManagerMenuList(manager.getPkId(), type, false);
        }

        return TreeUtils.build(SysMenuConvert.INSTANCE.convertList(menuList));
    }

    @Override
    public List<SysMenuVO> getMenuList(SysMenuQuery query) {
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper();
        List<SysMenu> menus = new ArrayList<>();
        List<SysMenu> result = new ArrayList<>();
        if (StringUtils.isNotBlank(query.getTitle())) {
            wrapper.like(SysMenu::getTitle, query.getTitle());
        }
        wrapper.orderByAsc(SysMenu::getSort);
        wrapper.eq(SysMenu::getDeleteFlag, DeleteFlagEnum.NOT_DELETE.getValue());
        List<SysMenu> menuList = baseMapper.selectList(wrapper);

        if (StringUtils.isNotBlank(query.getTitle())) {
            menus = baseMapper.selectList(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getDeleteFlag, DeleteFlagEnum.NOT_DELETE.getValue()));

            if (menuList.size() > 0) {
                for (SysMenu menu : menuList) {
                    result.add(menu);
//                    找子节点
                    result.addAll(getChildList(menu, menus));
//                    父级节点
                    result.addAll(getParentList(menu, menus));


                }
            }
            Integer rootNode = result.size() > 0 ? result.stream().min(Comparator.comparing(SysMenu::getParentId)).get().getParentId() : Constant.ROOT;
            return TreeUtils.build(SysMenuConvert.INSTANCE.convertList(result), rootNode);

        }

        return TreeUtils.build(SysMenuConvert.INSTANCE.convertList(menuList), Constant.ROOT);
    }

    @Override
    public List<SysMenu> getFormMenuList() {
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SysMenu::getType, MenuTypeEnum.MENU.name(), MenuTypeEnum.MENU_DIR.name());
        List<SysMenuVO> treeMenu = TreeUtils.build(SysMenuConvert.INSTANCE.convertList(baseMapper.selectList(wrapper)), Constant.ROOT);
        List<SysMenu> menuList = new ArrayList<>();
        SysMenu sysMenu = new SysMenu();
        sysMenu.setPkId(0);
        sysMenu.setTitle("根节点");
        menuList.add(sysMenu);
        for (SysMenuVO menu : treeMenu) {
            menuList.add(SysMenuConvert.INSTANCE.convert(menu));
            for (int i = 0; i < menu.getChildren().size(); i++) {
                SysMenuVO item = menu.getChildren().get(i);
                if (i < menu.getChildren().size() - 1) {
                    item.setTitle("      " + item.getTitle());
                } else {
                    item.setTitle("      " + item.getTitle());
                }
                menuList.add(SysMenuConvert.INSTANCE.convert(item));
            }
        }
        return menuList;
    }

    @Override
    public SysMenuVO infoById(Integer id) {
        SysMenu entity = baseMapper.selectById(id);
        SysMenuVO vo = SysMenuConvert.INSTANCE.convert(entity);

        // 获取上级菜单名称
        if (!Constant.ROOT.equals(entity.getPkId())) {
            SysMenu parentEntity = baseMapper.selectById(entity.getPkId());
            vo.setParentName(parentEntity.getName());
        }
        return vo;
    }

    @Override
    public void save(SysMenuVO vo) {
        SysMenu entity = SysMenuConvert.INSTANCE.convert(vo);
        baseMapper.insert(entity);

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysMenuVO vo) {
        SysMenu entity = SysMenuConvert.INSTANCE.convert(vo);
        // 上级菜单不能为自己
        if (entity.getPkId().equals(entity.getParentId())) {
            throw new ServerException("上级菜单不能为自己");
        }
        // 更新菜单
        updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer id) {
        long count = count(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getParentId, id));
        if (count > 0) {
            throw new ServerException("请先删除子菜单");
        }
        // 删除菜单
        removeById(id);

        // 删除角色菜单关系
        sysRoleMenuService.deleteByMenuId(id);
    }

    @Override
    public List<SysMenuVO> getRoleFormMenuList() {
        LambdaQueryWrapper<SysMenu> wrapper = new LambdaQueryWrapper();
        // 排除菜单管理 只给开发者配置
        wrapper.ne(SysMenu::getTitle, "菜单管理");
        wrapper.orderByAsc(SysMenu::getSort);
        List<SysMenu> menuList = baseMapper.selectList(wrapper);
        return TreeUtils.build(SysMenuConvert.INSTANCE.convertList(menuList), Constant.ROOT);
    }

    /**
     * 递归查询子集
     *
     * @param menu
     * @param list
     * @return
     */
    public List<SysMenu> getChildList(SysMenu menu, List<SysMenu> list) {
        List<SysMenu> menuList = new ArrayList<>();
        list.forEach(item -> {
            if (menu.getPkId() == item.getParentId()) {
                menuList.add(item);
                menuList.addAll(getChildList(item, list));
            }
        });
        return menuList;
    }

    /**
     * 递归查询父级
     *
     * @param menu
     * @param list
     * @return
     */
    public List<SysMenu> getParentList(SysMenu menu, List<SysMenu> list) {
        List<SysMenu> menuList = new ArrayList<>();
        list.forEach(item -> {
            if (menu.getParentId() == item.getPkId()) {
                menuList.add(item);
                menuList.addAll(getParentList(item, list));
            }
        });
        System.out.println(menuList);
        return menuList;
    }
}

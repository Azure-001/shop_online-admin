package com.shop_online.service.impl;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shop_online.common.constant.Constant;
import com.shop_online.common.exception.ServerException;
import com.shop_online.common.result.PageResult;
import com.shop_online.convert.SysManagerConvert;
import com.shop_online.entity.SysManager;
import com.shop_online.enums.SuperAdminEnum;
import com.shop_online.mapper.SysManagerMapper;
import com.shop_online.query.ChangePasswordQuery;
import com.shop_online.query.SysManagerQuery;
import com.shop_online.security.user.ManagerDetail;
import com.shop_online.service.SysManagerRoleService;
import com.shop_online.service.SysManagerService;
import com.shop_online.vo.SysManagerVO;
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
public class SysManagerServiceImpl extends ServiceImpl<SysManagerMapper, SysManager> implements SysManagerService {

    private final SysManagerRoleService sysManagerRoleService;

    @Override
    public PageResult<SysManagerVO> page(SysManagerQuery query) {
        Page<SysManagerVO> page = new Page<>(query.getPage(), query.getLimit());
        List<SysManagerVO> list = baseMapper.getManagerPage(page, query);
        return new PageResult<>(list, page.getTotal());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(SysManagerVO vo) {
        SysManager entity = SysManagerConvert.INSTANCE.convert(vo);
        entity.setSuperAdmin(SuperAdminEnum.NO.getValue());

        // 判断用户名是否存在
        SysManager manager = baseMapper.getByUsername(entity.getUsername());
        if (manager != null) {
            throw new ServerException("用户名已经存在");
        }
        if (StringUtils.isBlank(vo.getAvatar())) {
            entity.setAvatar(Constant.DEFAULT_AVATAR);
        }
        // 保存用户
        baseMapper.insert(entity);
        // 保存用户角色关系
        sysManagerRoleService.saveOrUpdate(entity.getPkId(), vo.getRoleId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysManagerVO vo) {
        SysManager entity = SysManagerConvert.INSTANCE.convert(vo);
        // 判断用户名是否存在
        SysManager manager = baseMapper.getByUsername(entity.getUsername());
        if (manager != null && !manager.getPkId().equals(entity.getPkId())) {
            throw new ServerException("用户名已经存在");
        }
        // 更新用户
        updateById(entity);
        // 更新用户角色关系
        sysManagerRoleService.saveOrUpdate(entity.getPkId(), vo.getRoleId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Integer> idList) {
        // 删除管理员
        removeByIds(idList);
        // 删除用户角色关系
        sysManagerRoleService.removeByManagerId(idList);
    }

    @Override
    public SysManagerVO getManagerInfo(ManagerDetail manage) {
        SysManagerVO sysManagerVO = new SysManagerVO();
        SysManager sysManager = baseMapper.selectById(manage.getPkId());
        if (sysManager == null) {
            throw new ServerException("管理员不存在");
        }
        sysManagerVO.setPkId(sysManager.getPkId());
        sysManagerVO.setAvatar(sysManager.getAvatar());
        sysManagerVO.setUsername(sysManager.getUsername());
        sysManagerVO.setRealName(sysManager.getRealName());
        sysManagerVO.setStatus(sysManager.getStatus());
        sysManagerVO.setRoleId(sysManagerRoleService.getByManagerId(manage.getPkId()).getRoleId());
        sysManagerVO.setCreateTime(sysManager.getCreateTime());
        return sysManagerVO;
    }

    @Override
    public void changePassword(ChangePasswordQuery query) {
        SysManager sysManager = baseMapper.selectById(query.getPkId());
        if (sysManager == null) {
            throw new ServerException("管理员不存在");
        }
        sysManager.setPassword(query.getPassword());
        updateById(sysManager);
    }
}

package com.shop_online.controller;

import cn.hutool.core.util.StrUtil;
import com.shop_online.common.exception.ServerException;
import com.shop_online.common.result.PageResult;
import com.shop_online.common.result.Result;
import com.shop_online.query.ChangePasswordQuery;
import com.shop_online.query.SysManagerQuery;
import com.shop_online.security.user.ManagerDetail;
import com.shop_online.security.user.SecurityUser;
import com.shop_online.service.SysManagerService;
import com.shop_online.vo.SysManagerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ycshang
 * @since 2023-11-28
 */
@Api(tags = "管理员管理")
@AllArgsConstructor
@RestController
@RequestMapping("sys/manager")
public class SysManagerController {

    private final SysManagerService sysManagerService;

    private final PasswordEncoder passwordEncoder;


    @PostMapping("getManagerInfo")
    @ApiOperation("获取管理员信息")
    public Result<SysManagerVO> getManagerInfo() {
        ManagerDetail manager = SecurityUser.getManager();
        return Result.ok(sysManagerService.getManagerInfo(manager));
    }

    @PostMapping("page")
    @ApiOperation("分页")
    @PreAuthorize("hasAuthority('sys:manager:list')")
    public Result<PageResult<SysManagerVO>> page(@RequestBody @Valid SysManagerQuery query) {
        PageResult<SysManagerVO> page = sysManagerService.page(query);
        return Result.ok(page);
    }

    @PostMapping("add")
    @ApiOperation("保存")
    @PreAuthorize("hasAuthority('sys:manager:add')")
    public Result<String> save(@RequestBody @Valid SysManagerVO vo) {
        // 新增密码不能为空
        if (StrUtil.isBlank(vo.getPassword())) {
            Result.error("密码不能为空");
        }
        // 密码加密
        vo.setPassword(passwordEncoder.encode(vo.getPassword()));
        // 保存
        sysManagerService.save(vo);

        return Result.ok();
    }

    @PostMapping("edit")
    @ApiOperation("修改")
    @PreAuthorize("hasAuthority('sys:manager:edit') or hasAuthority('sys:manager:reset-psw')")
    public Result<String> update(@RequestBody @Valid SysManagerVO vo) {
        // 如果密码不为空，则进行加密处理
        if (StrUtil.isBlank(vo.getPassword())) {
            vo.setPassword(null);
        } else {
            vo.setPassword(passwordEncoder.encode(vo.getPassword()));
        }

        sysManagerService.update(vo);

        return Result.ok();
    }

    @PostMapping("remove")
    @ApiOperation("删除")
    @PreAuthorize("hasAuthority('sys:manager:remove')")
    public Result<String> delete(@RequestBody List<Integer> idList) {
        Integer managerId = SecurityUser.getManagerId();
        if (idList.contains(managerId)) {
            return Result.error("不能删除当前登录管理员");
        }

        sysManagerService.delete(idList);

        return Result.ok();
    }

    @PostMapping("changePassword")
    @ApiOperation("修改密码")
    public Result<String> editPassword(@RequestBody @Valid ChangePasswordQuery query) {
        ManagerDetail manager = SecurityUser.getManager();
        if (manager.getPkId() == null) {
            throw new ServerException("管理员不存在");
        }
        query.setPkId(manager.getPkId());

        query.setPassword(passwordEncoder.encode(query.getPassword()));
        sysManagerService.changePassword(query);
        return Result.ok();
    }


}


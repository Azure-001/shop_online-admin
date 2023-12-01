package com.shop_online.controller;

import com.shop_online.common.result.PageResult;
import com.shop_online.common.result.Result;
import com.shop_online.query.SysRoleQuery;
import com.shop_online.service.SysMenuService;
import com.shop_online.service.SysRoleService;
import com.shop_online.vo.SysMenuVO;
import com.shop_online.vo.SysRoleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
@Api(tags = "角色管理")
@AllArgsConstructor
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

	private SysRoleService sysRoleService;

	private SysMenuService sysMenuService;

	@PostMapping("page")
	@ApiOperation("分页")
	@PreAuthorize("hasAuthority('sys:role:list')")
	public Result<PageResult<SysRoleVO>> page(@RequestBody @Valid SysRoleQuery query) {
		PageResult<SysRoleVO> page = sysRoleService.page(query);
		return Result.ok(page);
	}

	@PostMapping("list")
	@ApiOperation("列表")
	@PreAuthorize("hasAuthority('sys:role:list')")

	public Result<List<SysRoleVO>> list() {
		List<SysRoleVO> list = sysRoleService.getList(new SysRoleQuery());
		return Result.ok(list);
	}

	@PostMapping("add")
	@ApiOperation("保存")
	@PreAuthorize("hasAuthority('sys:role:add')")
	public Result<String> save(@RequestBody @Valid SysRoleVO vo) {
		sysRoleService.save(vo);
		return Result.ok();
	}

	@PostMapping("edit")
	@ApiOperation("修改")
	@PreAuthorize("hasAuthority('sys:role:edit')")
	public Result<String> update(@RequestBody @Valid SysRoleVO vo) {
		sysRoleService.update(vo);
		return Result.ok();
	}

	@PostMapping("remove")
	@ApiOperation("删除")
	@PreAuthorize("hasAuthority('sys:role:remove')")
	public Result<String> delete(@RequestBody List<Integer> idList) {
		sysRoleService.delete(idList);
		return Result.ok();
	}

	@PostMapping("menu")
	@ApiOperation("角色表单菜单列表")
	public Result<List<SysMenuVO>> getRoleFormMenuList() {
		List<SysMenuVO> list = sysMenuService.getRoleFormMenuList();
		return Result.ok(list);
	}

}

package com.shop_online.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shop_online.common.result.PageResult;
import com.shop_online.entity.SysManager;
import com.shop_online.query.ChangePasswordQuery;
import com.shop_online.query.SysManagerQuery;
import com.shop_online.security.user.ManagerDetail;
import com.shop_online.vo.SysManagerVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author ycshang
 * @since 2023-11-28
 */
public interface SysManagerService extends IService<SysManager> {

	PageResult<SysManagerVO> page(SysManagerQuery query);

	void save(SysManagerVO vo);

	void update(SysManagerVO vo);

	void delete(List<Integer> idList);

	void changePassword(ChangePasswordQuery query);

	SysManagerVO getManagerInfo(ManagerDetail manage);

}

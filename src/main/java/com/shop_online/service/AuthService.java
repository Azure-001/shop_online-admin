package com.shop_online.service;

import com.shop_online.vo.SysAccountLoginVO;
import com.shop_online.vo.SysTokenVO;

/**
 * 认证服务
 *
 * @Author ycshang
 * @Date 2023-05-18 17:26
 */
public interface AuthService {

	/**
	 * 使用账号密码登录
	 *
	 * @param params
	 * @return
	 */
	SysTokenVO loginByAccount(SysAccountLoginVO params);

	/**
	 * 退出登录
	 *
	 * @param accessToken accessToken
	 */
	void logout(String accessToken);

}

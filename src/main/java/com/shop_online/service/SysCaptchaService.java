package com.shop_online.service;

import com.shop_online.vo.SysCaptchaVO;

/**
 * @Author ycshang
 * @Date 2023/7/11
 * @Description TODO
 */
public interface SysCaptchaService {
	/**
	 * 生成验证码
	 */
	SysCaptchaVO generate();


	/**
	 * 验证码校验
	 *
	 * @param key
	 * @param code 验证码
	 * @return true:校验成功 false:校验失败
	 */
	boolean validate(String key, String code);
}

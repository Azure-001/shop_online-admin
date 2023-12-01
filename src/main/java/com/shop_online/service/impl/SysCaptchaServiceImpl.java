package com.shop_online.service.impl;

import cn.hutool.core.util.StrUtil;
import com.shop_online.common.cache.RedisCache;
import com.shop_online.common.cache.RedisKeys;
import com.shop_online.service.SysCaptchaService;
import com.shop_online.vo.SysCaptchaVO;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author ycshang
 * @Date 2023/7/11
 * @Description TODO
 */
@Service
@AllArgsConstructor
public class SysCaptchaServiceImpl implements SysCaptchaService {

	private final RedisCache redisCache;

	@Override
	public SysCaptchaVO generate() {
//		生成验证码的key
		String key = UUID.randomUUID().toString();

//		生成验证码图片
		SpecCaptcha captcha = new SpecCaptcha(150, 60);
		captcha.setLen(5);
		captcha.setCharType(Captcha.TYPE_DEFAULT);
		String image = captcha.toBase64();

//		保存到redis
		String redisKey = RedisKeys.getCaptchaKey(key);
		redisCache.set(redisKey, captcha.text(), 300);

		// 封装返回数据
		SysCaptchaVO captchaVO = new SysCaptchaVO();
		captchaVO.setKey(key);
		captchaVO.setImage(image);

		return captchaVO;
	}


	@Override
	public boolean validate(String key, String code) {

		if (StrUtil.isBlank(key) || StrUtil.isBlank(code)) {
			return false;
		}
//		获取验证码
		String captcha = getCache(key);
//		校验成功
		return code.equalsIgnoreCase(captcha);
	}


	private String getCache(String key) {
		key = RedisKeys.getCaptchaKey(key);
		String captcha = (String) redisCache.get(key);
//		删除验证码
		if (captcha != null) {
			redisCache.delete(key);
		}
		return captcha;
	}
}

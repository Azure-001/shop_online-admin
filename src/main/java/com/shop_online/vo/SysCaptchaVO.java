package com.shop_online.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author ycshang
 * @Date 2023/7/11
 * @Description TODO
 */

@Data
@Schema(description = "图片验证码")
public class SysCaptchaVO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "key")
	private String key;
	@Schema(description = "image base64")
	private String image;
}

package com.shop_online.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author ycshang
 */
@Data
@Schema(description = "图片url上传地址")
@AllArgsConstructor
public class FileUrlVO {

	@Schema(description = "file_url")
	private String fileUrl;
}
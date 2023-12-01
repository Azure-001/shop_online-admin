package com.shop_online.controller;

import com.shop_online.common.result.Result;
import com.shop_online.service.CommonService;
import com.shop_online.vo.FileUrlVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author ycshang
 * @Date 2023/7/12
 * @Description TODO
 */
@Api(tags = "通用模块")
@RestController
@RequestMapping("common")
@AllArgsConstructor
public class CommonController {

	private final CommonService commonService;

	@PostMapping(value = "/upload/img")
	@ResponseBody
	@ApiOperation("图片上传")
	public Result<FileUrlVO> upload(@RequestBody MultipartFile file) {
		return Result.ok(commonService.upload(file));
	}

}

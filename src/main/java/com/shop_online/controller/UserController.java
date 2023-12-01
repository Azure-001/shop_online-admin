package com.shop_online.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ycshang
 * @since 2023-11-28
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("user")
@AllArgsConstructor
public class UserController {
    
}

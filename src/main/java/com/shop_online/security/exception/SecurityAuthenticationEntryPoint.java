package com.shop_online.security.exception;


import com.alibaba.fastjson2.JSONObject;
import com.shop_online.common.exception.ErrorCode;
import com.shop_online.common.result.Result;
import com.shop_online.utils.HttpContextUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


import java.io.IOException;
/**
 * @author ycshang
 */
public class SecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
        response.getWriter().print(JSONObject.toJSONString(Result.error(ErrorCode.UNAUTHORIZED)));
    }
}
package com.wangsl.config.security;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyAuthenticationFaliureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("message", "login failure");
		result.put("data", exception.getLocalizedMessage());

		// 将结果转换为 json 字符串
		String json = JSON.toJSONString(result);

		// 返回 json 数据到前端
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().println(json);
	}
}

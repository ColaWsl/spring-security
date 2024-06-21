package com.wangsl.config.security;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {

	// 当访问一个需要认证的资源时，会调用这个方法
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
		Map<String, Object> result = new HashMap<>();
		result.put("code", -1);
		result.put("message", "请登录后访问");
		HashMap<String, Object> data = new HashMap<>();
		data.put("url", "http://localhost/login");
		result.put("data", data);

		// 将结果转换为 json 字符串
		String json = JSON.toJSONString(result);

		// 返回 json 数据到前端
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().println(json);
	}
}

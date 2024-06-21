package com.wangsl.config.security;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		Map<String, Object> result = new HashMap<>();
		result.put("code", 1);
		result.put("message", "logout success");

		// 将结果转换为 json 字符串
		String json = JSON.toJSONString(result);

		// 返回 json 数据到前端
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().println(json);
	}
}

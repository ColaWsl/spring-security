package com.wangsl.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@ResponseBody
	@GetMapping("/test")
	public Map test() {
		// 获取认证的相关信息
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		Object principal = authentication.getPrincipal();
		Object credentials = authentication.getCredentials();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		Map<String, Object> map = new HashMap<>();
		map.put("name", authentication.getName());
		map.put("principal", principal);
		map.put("authorities", authorities);
		return map;
	}

}

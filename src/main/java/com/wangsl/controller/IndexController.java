package com.wangsl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@ResponseBody
	@GetMapping("/test")
	public String test() {
		return "hello";
	}
}

package com.wangsl.controller;

import com.wangsl.domain.User;
import com.wangsl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/list")
	public List<User> user() {
		List<User> list = userService.list();
		return list;
	}

	/**
	 * 添加用户
	 * @return
	 */
	@PostMapping("/add")
	public String add(@RequestBody User user) {
		userService.saveUserDetials(user);
		return "success";
	}

	@PostMapping("/update")
	public String update(@RequestBody User user, String newPassword){
		userService.updatePassword(user, newPassword);
		return "success";
	}
}

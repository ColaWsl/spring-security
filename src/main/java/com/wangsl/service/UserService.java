package com.wangsl.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.wangsl.domain.User;

public interface UserService extends IService<User> {

	void saveUserDetials(User user);

	void updatePassword(User user, String newPassword);
}

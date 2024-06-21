package com.wangsl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wangsl.config.DBUserDetailsManager;
import com.wangsl.domain.User;
import com.wangsl.mapper.UserMapper;
import com.wangsl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
		implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private DBUserDetailsManager dbUserDetailsManager;

	@Override
	public void saveUserDetials(User user) {
		dbUserDetailsManager.createUser(
				org.springframework.security.core.userdetails.User
						.withUsername(user.getUsername())
						.password(user.getPassword())
						// .roles("USER")
						.build()
		);
	}

	@Override
	public void updatePassword(User user, String newPassword) {
		// 校验用户是否存在
		User dbUser = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, user.getUsername()));
		if(dbUser == null){
			throw new RuntimeException("用户不存在");
		}
		dbUserDetailsManager.updatePassword(
				org.springframework.security.core.userdetails.User
						.withUsername(user.getUsername())
						.password(user.getPassword())
						// .roles("USER")
						.build(),
				newPassword);
	}

}

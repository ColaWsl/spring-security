package com.wangsl.config.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wangsl.domain.User;
import com.wangsl.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Component
public class DBUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

	private static final Logger log = LoggerFactory.getLogger(DBUserDetailsManager.class);

	@Resource
	private UserMapper userMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * 通过用户名从数据库中获取用户信息
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 根据用户名从数据库中查询用户
		User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
		if(Objects.isNull(user)){
			log.error("username is not exist");
			throw new UsernameNotFoundException(username);
		}

		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(() -> "USER_LIST");
		// authorities.add(() -> "USER_ADD");
		// return new org.springframework.security.core.userdetails.User(
		// 		user.getUsername(),
		// 		user.getPassword(),
		// 		user.getEnabled() == 1,
		// 		true,
		// 		true,
		// 		true,
		// 		authorities
		// );

		return org.springframework.security.core.userdetails.User
				.withUsername(user.getUsername())
				.password(user.getPassword())
				.disabled(user.getEnabled() != 1)
				.credentialsExpired(false)
				.accountLocked(false)
				.roles("ADMIN")
				.build();

	}

	// 更新密码
	@Override
	public UserDetails updatePassword(UserDetails userDetails, String newPassword) {
		User user = new User();
		user.setUsername(userDetails.getUsername());
		user.setPassword(passwordEncoder.encode(newPassword)); // 加密
		int update = userMapper.update(user, new LambdaQueryWrapper<User>().eq(User::getUsername, userDetails.getUsername()));
		if(update < 1){
			throw new RuntimeException("更新密码失败");
		}
		return userDetails;
	}

	// 新增用户
	@Override
	public void createUser(UserDetails userDetails) {
		User user = new User();
		user.setUsername(userDetails.getUsername());
		user.setPassword(passwordEncoder.encode(userDetails.getPassword())); // 加密
		user.setEnabled(1); // 1 为有效用户
		int insert = userMapper.insert(user);
		if(insert < 1){
			throw new RuntimeException("新增用户失败");
		}
	}

	@Override
	public void updateUser(UserDetails user) {

	}

	@Override
	public void deleteUser(String username) {

	}

	@Override
	public void changePassword(String oldPassword, String newPassword) {

	}

	@Override
	public boolean userExists(String username) {
		return false;
	}



}

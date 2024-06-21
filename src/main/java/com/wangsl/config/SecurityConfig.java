package com.wangsl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 开启自定义配置（在springboot可以省略）
public class SecurityConfig {

	// 过滤器链
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				// .csrf(Customizer.withDefaults())
				.csrf(AbstractHttpConfigurer::disable) // 关闭csrf保护
				.authorizeHttpRequests( // 开启授权保护
						authorize -> authorize.anyRequest() // 对所有请求开启授权保护
								.authenticated() // 已认证的请求会被自动授权
				)
				.httpBasic(Customizer.withDefaults()) // 使用基本授权方式
				.formLogin(Customizer.withDefaults()); // 自动使用表单登录
		return http.build();
	}

	// // 基于数据源的用户详情管理器
	// @Bean
	// public UserDetailsManager UserDetailsManager() {
	// 	// db based
	// 	DBUserDetailsManager manager = new DBUserDetailsManager();
	// 	return manager;
	// }

	// 密码编码器
	@Bean
	public PasswordEncoder passwordEncoder() {
		// 自适应单向函数 验证密码时占用系统资源
		return new BCryptPasswordEncoder();
	}

}

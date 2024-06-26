package com.wangsl.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
// @EnableWebSecurity // 开启自定义配置（在springboot可以省略）
@EnableMethodSecurity // 开启方法授权
public class SecurityConfig {

	// 过滤器链
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests( // 开启授权保护
						authorize -> authorize
								// .requestMatchers("/user/list").hasAuthority("USER_LIST")
								// .requestMatchers("/user/add").hasAuthority("USER_ADD")
								// .requestMatchers("/user/**").hasRole("ADMIN")
								// .requestMatchers("/test").hasRole("TEST")
								.anyRequest() // 对所有请求开启授权保护
								.authenticated() // 已认证的请求会被自动授权
		);


		// .formLogin(Customizer.withDefaults()); // 自动使用表单登录
		http.formLogin(form -> form.loginPage("/login").permitAll()
						.successHandler(new MyAuthenticationSuccessHandler())
						.failureHandler(new MyAuthenticationFaliureHandler())
		);
		// http.httpBasic(Customizer.withDefaults()) // 使用基本授权方式


		// 注销时的处理
		http.logout(logout -> logout.logoutSuccessHandler(new MyLogoutSuccessHandler()));

		// 未认证的请求处理
		http.exceptionHandling(exception ->
			exception.authenticationEntryPoint(new MyAuthenticationEntryPoint())
					.accessDeniedHandler(new MyAccessDeniedHandler()));

		// 并发会话控制
		http.sessionManagement(session -> session.maximumSessions(1).expiredSessionStrategy(new MySessionInformationExpiredStrategy()));

		// 开启跨域权限
		http.cors(Customizer.withDefaults());

		// .csrf(Customizer.withDefaults())
		http.csrf(AbstractHttpConfigurer::disable);// 关闭csrf保护

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

package com.wangsl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SpringSecurityApplicationTests {

	@Test
	void contextLoads() {

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encoded = passwordEncoder.encode("123");
		System.out.println(encoded);

		System.out.println(passwordEncoder.matches("123", "$2a$10$PfMkyF20Njwp/4G3TcI5hu15l8grFrCZa8d2HldZjRVH3dXv5TxG6"));
	}

}

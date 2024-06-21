package com.wangsl.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.wangsl.mapper")
public class MybatisPlusConfig {
}

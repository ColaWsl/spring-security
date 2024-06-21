package com.wangsl.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.wangsl.mapper")
public class MybatisPlusConfig {
}

package com.ssafy.enjoyTrip.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableAspectJAutoProxy
@MapperScan(basePackages = {"com.ssafy.enjoyTrip.**.model.mapper"})
public class WebMVCConfig implements WebMvcConfigurer{

}

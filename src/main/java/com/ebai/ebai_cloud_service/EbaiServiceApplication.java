package com.ebai.ebai_cloud_service;

import com.ebai.ebai_cloud_service.common.filter.ProjectFilter;
import com.ebai.ebai_cloud_service.common.filter.SessionFilter;
import com.ebai.ebai_cloud_service.common.util.impl.InitUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

@Slf4j
@SpringBootApplication
@CrossOrigin
public class EbaiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbaiServiceApplication.class, args);
		InitUtil.init();
	}

	@Bean
	public FilterRegistrationBean<ProjectFilter> projectFilterRegistration() {
		FilterRegistrationBean<ProjectFilter> filterRegistrationBean = new FilterRegistrationBean<>(new ProjectFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setName("project monitor");
		filterRegistrationBean.setOrder(1);
		return filterRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean<SessionFilter> sessionFilterRegistration() {
		FilterRegistrationBean<SessionFilter> filterRegistrationBean = new FilterRegistrationBean<>(new SessionFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setName("session filter");
		filterRegistrationBean.setOrder(2);
		return filterRegistrationBean;
	}

}

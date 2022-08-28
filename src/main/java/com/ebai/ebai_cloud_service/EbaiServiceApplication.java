package com.ebai.ebai_cloud_service;

import com.ebai.ebai_cloud_service.common.filter.ProjectFilter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@SpringBootApplication
public class EbaiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbaiServiceApplication.class, args);
		TimerTask ipCheck = new TimerTask() {
			@SneakyThrows
			@Override
			public void run() {
				try {
//					HeartFilter.heartService();
				} catch (Exception e) {
					log.warn(e.toString());
				}
			}
		};
		Timer timer = new Timer();
		timer.schedule(ipCheck, 1000,  10 * 60 * 1000);
	}

	@Bean
	public FilterRegistrationBean projectFilterRegistration() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new ProjectFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setName("project monitor");
		filterRegistrationBean.setOrder(1);
		return filterRegistrationBean;
	}

//	@Bean
//	public FilterRegistrationBean sessionFilterRegistration() {
//		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new SessionFilter());
//		filterRegistrationBean.addUrlPatterns("/*");
//		filterRegistrationBean.setName("session filter");
//		filterRegistrationBean.setOrder(2);
//		return filterRegistrationBean;
//	}

}

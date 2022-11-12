package com.ebai.ebai_cloud_service.common.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new Test()).addPathPatterns("/**");

        registry.addInterceptor(new ProjectInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/internal/**")
                .excludePathPatterns("/error/**")
                .excludePathPatterns("/log/**");
//        super.addInterceptors(registry);
    }
}
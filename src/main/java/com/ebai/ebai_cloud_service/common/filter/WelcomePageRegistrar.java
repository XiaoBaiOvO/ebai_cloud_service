package com.ebai.ebai_cloud_service.common.filter;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class WelcomePageRegistrar implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage[] errorPages = new ErrorPage[] {
                new ErrorPage(HttpStatus.FORBIDDEN,"/index,html"),
                new ErrorPage(HttpStatus.NOT_FOUND,"/index.html"),
                new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/index.html"),
        };
        registry.addErrorPages(errorPages);
    }

}

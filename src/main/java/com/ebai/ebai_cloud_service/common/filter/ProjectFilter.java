package com.ebai.ebai_cloud_service.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@ResponseBody
public class ProjectFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String userName = (String) request.getSession().getAttribute("userName");
        log.info("请求拦截：uri == > {}",request.getRequestURI());
//        HeartFilter.ipService(request, userName);
        log.info("------------------ Service  Log ------------------");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}

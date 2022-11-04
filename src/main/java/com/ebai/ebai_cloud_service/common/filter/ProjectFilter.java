package com.ebai.ebai_cloud_service.common.filter;

import com.ebai.ebai_cloud_service.common.util.impl.NetworkUtil;
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
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        NetworkUtil networkUtil = new NetworkUtil();
        log.info("请求拦截：URL == > {}",httpServletRequest.getRequestURI());
        log.info("网络信息：IP == > {} ({})",networkUtil.getIp(httpServletRequest), networkUtil.getLocation(httpServletRequest));
        log.info("设备信息：User-Agent == > {}", httpServletRequest.getHeader("User-Agent"));

        filterChain.doFilter(servletRequest, servletResponse);
        log.info("--------------  Request  Completed  --------------");
    }



}



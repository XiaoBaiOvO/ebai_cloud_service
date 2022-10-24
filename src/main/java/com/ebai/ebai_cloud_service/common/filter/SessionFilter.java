package com.ebai.ebai_cloud_service.common.filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class SessionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        final Logger log = LoggerFactory.getLogger(SessionFilter.class);

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute("username");
        Date currentDate = new Date();
        long sessionExistTime = currentDate.getTime() - session.getCreationTime();
        long sessionLastActiveTime = currentDate.getTime() - session.getLastAccessedTime();
        log.info("session userName => {} , sessionExistTime => {} ({}分钟) , sessionLastActiveTime => {} ({}分钟) ", userName, sessionExistTime, sessionExistTime / ( 60 * 1000 ), sessionLastActiveTime, sessionLastActiveTime / ( 60 * 1000 ));
        // 用户在线半小时或十分钟未操作
        if (userName == null) {
            log.info("session初始化");
            session.invalidate();
        } else if (sessionExistTime > 30 * 60 * 1000 || sessionLastActiveTime > 10 * 60 * 1000) {
            log.info("session过期");
            session.invalidate();
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }


}

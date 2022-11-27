package com.ebai.ebai_cloud_service.common.filter;


import com.ebai.ebai_cloud_service.common.util.HttpUtils;
import com.ebai.ebai_cloud_service.common.util.impl.NetworkUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.EntityUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.ebai.ebai_cloud_service.constants.CommonConstants.*;
import static com.ebai.ebai_cloud_service.constants.FieldNameConstants.*;

@Slf4j
public class ProjectInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        NetworkUtil networkUtil = new NetworkUtil();

        String url = request.getRequestURI();
        String ip = networkUtil.getIp(request);
        Map<String, String> getLocationQuery = new HashMap<>();
        getLocationQuery.put(IP, ip);
        String location = EntityUtils.toString(HttpUtils.doGet(LOCALHOST, GET_LOCATION_PATH, EMPTY_HEADER, getLocationQuery).getEntity());
        String userAgent = request.getHeader(USER_AGENT);
        HttpSession session = request.getSession();
        String userName = (String) session.getAttribute(USER_NAME);


        Date currentDate = new Date();
        long sessionExistTime = currentDate.getTime() - session.getCreationTime();
        long sessionLastActiveTime = currentDate.getTime() - session.getLastAccessedTime();

        log.info("请求拦截：URL == > {}", url);
        log.info("网络信息：IP == > {} ({})",ip, location);
        log.info("设备信息：User-Agent == > {}", userAgent);
        log.info("session userName => {} , sessionExistTime => {} ({}分钟) , sessionLastActiveTime => {} ({}分钟) ",
                userName, sessionExistTime, sessionExistTime / ( 60 * 1000 ), sessionLastActiveTime, sessionLastActiveTime / ( 60 * 1000 ));

        // 用户在线半小时或十分钟未操作
//        if (userName == null) {
//            log.info("session初始化");
//            session.invalidate();
//        } else if (sessionExistTime > 30 * 60 * 1000 || sessionLastActiveTime > 10 * 60 * 1000) {
//            log.info("session过期");
//            session.invalidate();
//        }

        Map<String, String> saveRequestQuery = new HashMap<>();
        saveRequestQuery.put("url", url);
        saveRequestQuery.put("ip", ip);
        saveRequestQuery.put("location", location);
        saveRequestQuery.put("userAgent", userAgent);
        saveRequestQuery.put("userName", userName);
        HttpUtils.doGet(LOCALHOST, GET_SAVE_REQUEST_PATH, new HashMap<>(), saveRequestQuery);

        log.info("------------------ Service  Log ------------------");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("--------------  Request  Completed  --------------");
    }

}
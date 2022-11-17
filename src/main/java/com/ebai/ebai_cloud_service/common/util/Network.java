package com.ebai.ebai_cloud_service.common.util;

import com.alibaba.fastjson.JSONObject;
import com.ebai.ebai_cloud_service.model.dto.MailRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface Network {

    String getLocation(HttpServletRequest httpServletRequest);

    String getLocationByIp(String ip);

    Boolean sendMail(MailRequest mail);
    Boolean sendMail(List<MailRequest> mailList);

    JSONObject httpGetClient(String url, Boolean retry);

    JSONObject httpGetClient(String url, Integer delay);

    JSONObject httpGetClient(String url, Boolean retry, Integer delay);

    JSONObject doPost(String url) throws IOException;

    String getIp(HttpServletRequest httpServletRequest);

    JSONObject httpGetClient(String url);
}

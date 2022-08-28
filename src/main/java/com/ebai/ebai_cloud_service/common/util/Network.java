package com.ebai.ebai_cloud_service.common.util;

import com.alibaba.fastjson.JSONObject;
import com.ebai.ebai_cloud_service.model.dto.MailRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface Network {

    JSONObject getHttp(String urlString);

    String getIp(HttpServletRequest httpServletRequest);

    Boolean sendMail(MailRequest mail);
}

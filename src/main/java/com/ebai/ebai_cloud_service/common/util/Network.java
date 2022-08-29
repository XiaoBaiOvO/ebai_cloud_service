package com.ebai.ebai_cloud_service.common.util;

import com.alibaba.fastjson.JSONObject;
import com.ebai.ebai_cloud_service.model.dto.MailRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface Network {

    JSONObject getHttp(String urlString);

    Boolean sendMail(MailRequest mail);
    Boolean sendMail(List<MailRequest> mailList);

    String getIp(HttpServletRequest httpServletRequest);

}

package com.ebai.ebai_cloud_service.common.util.impl;

import com.alibaba.fastjson.JSONObject;
import com.ebai.ebai_cloud_service.common.util.Network;
import com.ebai.ebai_cloud_service.model.dto.MailRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Service
@Slf4j
public class NetworkUtil implements Network {

    @Override
    public Boolean sendMail(MailRequest mail) {
        return sendByQQMail(mail.getTitle(), mail.getMessage(), mail.getSender(), mail.getRecipient(), mail.getRecipientAccount());
    }

    @Override
    public String getIp(HttpServletRequest httpServletRequest) {
        return getRequestIp(httpServletRequest);
    }

    @Override
    public JSONObject getHttp(String urlString) {
        return getHttpRequest(urlString);
    }

    private JSONObject getHttpRequest(String urlString) {
        log.info("Http Get => {}", urlString);
        try {
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line;
            StringBuilder stringBuffer = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            bufferedReader.close();
            httpURLConnection.disconnect();
            JSONObject result = JSONObject.parseObject(String.valueOf(stringBuffer));
            log.info("<= 接收成功");
            return result;
        } catch (Exception e) {
            log.warn("<= 接收失败");
            log.warn(String.valueOf(e));
            return null;
        }
    }

    private String getRequestIp(HttpServletRequest httpServletRequest) {
        String ip = httpServletRequest.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getRemoteAddr();
        }
        // 处理多IP的情况（只取第一个IP）
        if (ip != null && ip.contains(",")) {
            String[] ipArray = ip.split(",");
            ip = ipArray[0];
        }
        return ip;
    }

    private static final String senderAccount = "2987772087@qq.com";

    private static final String senderAccountPassword = "zeztsvvtmtymdheh";

    private Boolean sendByQQMail(String title, String message, String sender, String Recipient, String RecipientAccount) {
        log.info("邮件发送 => {}", RecipientAccount);
        try {
            Properties properties = new Properties();
            properties.setProperty("mail.host","smtp.qq.com");
            properties.setProperty("mail.transport.protocol","smtp");
            properties.setProperty("mail.smtp.auth","true");
            Session session = Session.getInstance(properties);
//            session.setDebug(true);
            Transport transport = session.getTransport();
            transport.connect("smtp.qq.com",senderAccount,senderAccountPassword);
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(senderAccount, sender, "UTF-8"));

//            List<InternetAddress> adds = new ArrayList<>();
//            InternetAddress internetAddress = new InternetAddress(RecipientAccount, Recipient, "UTF-8");
//            adds.add(internetAddress);
//            adds.add(internetAddress);
//            InternetAddress[] address =adds.toArray(new InternetAddress[0]);
//            mimeMessage.setRecipients(Message.RecipientType.TO,address);

            mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(RecipientAccount, Recipient, "UTF-8"));
            mimeMessage.setSubject(title);
            mimeMessage.setContent(message,"text/html;charset=UTF-8");
            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
            transport.close();
            log.info("<= 发送成功");
            return true;
        } catch (Exception e) {
            log.warn("<= 发送失败");
            log.warn(String.valueOf(e));
            return false;
        }
    }
}

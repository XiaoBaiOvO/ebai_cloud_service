package com.ebai.ebai_cloud_service.common.util.impl;

import com.alibaba.fastjson.JSONObject;
import com.ebai.ebai_cloud_service.common.util.Network;
import com.ebai.ebai_cloud_service.model.dto.MailRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Service
@Slf4j
public class NetworkUtil implements Network {

    @Override
    public JSONObject httpGetClient(String url) {
        return httpGetClient(url, false);
    }

    @Override
    public JSONObject httpGetClient(String url, Boolean retry) {
        return  httpGetClient(url, true, 1000);
    }

    @Override
    public JSONObject httpGetClient(String url, Integer delay) {
        return  httpGetClient(url, true, delay);
    }

    @Override
    public JSONObject httpGetClient(String url, Boolean retry, Integer delay) {

        JSONObject result = new JSONObject();
        if (retry) {
            while (Objects.equals(result, new JSONObject())) {
                try {
                    Thread.sleep(delay);
                    result = doGet(url);
                } catch (Exception e) {
                    log.warn("Http Get Client Exception");
                }
            }
        } else {
            try {
                result = doGet(url);
            } catch (Exception e) {
                log.warn("Http Get Client Exception");
            }
        }
        return result;
    }

    private JSONObject doGet(String url) throws IOException {
        CloseableHttpClient httpClient;
        CloseableHttpResponse response;
        // 通过址默认配置创建一个httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpGet远程连接实例
        HttpGet httpGet = new HttpGet(url);
        // 设置请求头信息，鉴权
//        httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
        // 设置配置请求参数
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 请求超时时间
                .setSocketTimeout(60000)// 数据读取超时时间
                .build();
        // 为httpGet实例设置配置
        httpGet.setConfig(requestConfig);
        // 执行get请求得到返回对象
        response = httpClient.execute(httpGet);
        // 通过返回对象获取返回数据
        HttpEntity entity = response.getEntity();
        // 通过EntityUtils中的toString方法将结果转换为字符串
        JSONObject result = JSONObject.parseObject(EntityUtils.toString(entity));
        // 关闭资源
        response.close();
        httpClient.close();
//        try {
//            response.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            httpClient.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return result;
    }

    @Override
    public String getIp(HttpServletRequest httpServletRequest) {
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

    @Override
    public Boolean sendMail(MailRequest mail) {
        log.info("邮件发送 => {}", mail.getRecipientAccount());
        try {
            List<InternetAddress> addressList = new ArrayList<>();
            addressList.add(new InternetAddress(mail.getRecipientAccount(), mail.getRecipient(), "UTF-8"));
            InternetAddress[] address = addressList.toArray(new InternetAddress[0]);
            return sendByQQMail(mail.getTitle(), mail.getMessage(), mail.getSender(), address);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean sendMail(List<MailRequest> mailList) {
        try {
            List<InternetAddress> addressList = new ArrayList<>();
            for (MailRequest mail : mailList) {
                log.info("邮件发送 => {}", mail.getRecipientAccount());
                addressList.add(new InternetAddress(mail.getRecipientAccount(), mail.getRecipient(), "UTF-8"));
            }
            InternetAddress[] address = addressList.toArray(new InternetAddress[0]);
            return sendByQQMail(mailList.get(0).getTitle(), mailList.get(0).getMessage(), mailList.get(0).getSender(), address);
        } catch (Exception e) {
            return false;
        }
    }

    private static final String senderAccount = "2987772087@qq.com";

    private static final String senderAccountPassword = "zeztsvvtmtymdheh";

    private Boolean sendByQQMail(String title, String message, String sender, InternetAddress[] internetAddresses) {
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
            mimeMessage.setRecipients(Message.RecipientType.TO,internetAddresses);
            mimeMessage.setSubject(title);
            mimeMessage.setContent(message,"text/html;charset=UTF-8");
            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
            transport.close();
            log.info("=> 发送成功");
            return true;
        } catch (Exception e) {
            log.warn("=> 发送失败");
            log.warn(String.valueOf(e));
            return false;
        }
    }

}

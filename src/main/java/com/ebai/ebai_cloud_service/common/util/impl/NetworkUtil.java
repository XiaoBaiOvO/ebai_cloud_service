package com.ebai.ebai_cloud_service.common.util.impl;

import com.alibaba.fastjson.JSONObject;
import com.ebai.ebai_cloud_service.common.util.Network;
import com.ebai.ebai_cloud_service.model.dto.MailRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

@Service
@Slf4j
public class NetworkUtil implements Network {

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
            log.info("=> 接收成功");
            return result;
        } catch (Exception e) {
            log.warn("=> 接收失败");
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

    private static String localIp;
    private static LocalDateTime startDate;

    public void initServiceLocalIp() {
        NetworkUtil networkUtil = new NetworkUtil();
//        JSONObject result = networkUtil.getHttp("https://forge.speedtest.cn/api/location/info");
//        JSONObject result = (JSONObject) networkUtil.getHttp("https://api-v3.speedtest.cn/ip").get("data");
        JSONObject result = (JSONObject) networkUtil.doGet("https://api-v3.speedtest.cn/ip").get("data");
        localIp = result.get("ip").toString();
        startDate = LocalDateTime.now();
        log.info("=> 服务启动成功");
        log.info("=> 本地IP地址: " + localIp);
        MailRequest mail = new MailRequest();
        mail.setTitle("服务器启动成功");
        mail.setMessage("当前IP地址: " + localIp);
        mail.setSender("小白云");
        mail.setRecipient("Administrator");
        mail.setRecipientAccount("2643372457@qq.com");
//            sendMail(mail);
        mail.setRecipientAccount("2081414628@qq.com");
        sendMail(mail);
    }

    public void checkServiceLocalIp() {
        NetworkUtil networkUtil = new NetworkUtil();
        JSONObject result = networkUtil.getHttp("https://forge.speedtest.cn/api/location/info");
        String ip = result.get("ip").toString();
        if (!Objects.equals(ip, localIp)) {
            log.warn("本地IP发生变化: {} ==> {}", localIp, ip);
            MailRequest mail = new MailRequest();
            mail.setTitle("服务器IP地址变更");
            mail.setMessage("服务器IP变更: <br>" + localIp + " ==> " + ip);
            mail.setSender("小白云");
            mail.setRecipient("Administrator");
            mail.setRecipientAccount("2081414628@qq.com");
            sendMail(mail);
            localIp = ip;
        }
        String runningTime = Duration.between(startDate, LocalDateTime.now()).toString().substring(2);
        log.info("=> 本地IP地址: {} => 累计运行时长: {}", ip, runningTime);
    }

    private JSONObject doGet(String url) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        JSONObject result = new JSONObject();
        try {
            // 通过址默认配置创建一个httpClient实例
            httpClient = HttpClients.createDefault();
            // 创建httpGet远程连接实例
            HttpGet httpGet = new HttpGet(url);
            // 设置请求头信息，鉴权
            httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
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
            result = JSONObject.parseObject(EntityUtils.toString(entity));
            log.info(result.get("data").toString());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != response) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}

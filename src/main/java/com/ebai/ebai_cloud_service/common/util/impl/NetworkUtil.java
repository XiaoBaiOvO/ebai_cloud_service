package com.ebai.ebai_cloud_service.common.util.impl;

import com.alibaba.fastjson.JSONObject;
import com.ebai.ebai_cloud_service.common.util.HttpUtils;
import com.ebai.ebai_cloud_service.common.util.Network;
import com.ebai.ebai_cloud_service.mapper.IpQueryLogRepository;
import com.ebai.ebai_cloud_service.mapper.entity.IpQueryLogEntity;
import com.ebai.ebai_cloud_service.model.dto.MailRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class NetworkUtil implements Network {

    @Resource
    IpQueryLogRepository ipQueryLogRepository;

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
//                    log.warn(e.toString());
//                    log.warn(e.getLocalizedMessage());
                }
            }
        } else {
            try {
                result = doGet(url);
            } catch (Exception e) {
                log.warn("Http Get Client Exception");
                log.warn(e.getLocalizedMessage());
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
    public JSONObject doPost(String url) throws IOException {

        CloseableHttpClient httpClient;
        CloseableHttpResponse response;
        // 通过址默认配置创建一个httpClient实例
        httpClient = HttpClients.createDefault();
        // 创建httpGet远程连接实例
        HttpPost httpPost = new HttpPost(url);
        // 设置请求头信息，鉴权
//        httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
        // 设置配置请求参数
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 请求超时时间
                .setSocketTimeout(60000)// 数据读取超时时间
                .build();
        // 为httpGet实例设置配置
        httpPost.setConfig(requestConfig);
        // 执行get请求得到返回对象
        response = httpClient.execute(httpPost);
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
//        v1
//        String ip = httpServletRequest.getHeader("X-Real-IP");
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = httpServletRequest.getHeader("X-Forwarded-For");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = httpServletRequest.getHeader("Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = httpServletRequest.getHeader("WL-Proxy-Client-IP");
//        }
//        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//            ip = httpServletRequest.getRemoteAddr();
//        }

//        v2
        String ip = httpServletRequest.getHeader("x-forwarded-for");
        String unknown = "unknown";
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
            ip = httpServletRequest.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || unknown.equalsIgnoreCase(ip)) {
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
    public String getLocation(HttpServletRequest httpServletRequest) {
        return getLocationByIp(getIp(httpServletRequest));
    }

    @Override
    public String getLocationByIp(String ip) {
        String result = "Unknown";

        if (ip.startsWith("192.168") && ipQueryLogRepository.findAllByIp(ip).isEmpty()) {
            result = "内网访问";
            IpQueryLogEntity ipQueryLogEntity = new IpQueryLogEntity();
            ipQueryLogEntity.setIp(ip);
            ipQueryLogEntity.setSummary(result);
            ipQueryLogEntity.setDate(LocalDateTime.now());
            ipQueryLogRepository.save(ipQueryLogEntity);
            return result;
        }

        List<IpQueryLogEntity> ipLogList = ipQueryLogRepository.findAllByIp(ip);
        if (ipLogList.size() > 0) {

            result = ipLogList.get(0).getSummary();
        } else {
            try {
//                百度地图查询
//                String url = "https://api.map.baidu.com/location/ip?ak=Tco6gfz2hZFqtoXGIzoQavlz49dLCtOS&ip=" + ip + "&coor=bd09ll";
//                JSONObject resp = httpGetClient(url, true);
////
//                JSONObject contentJson = JSONObject.parseObject(resp.getString("content"));
//                JSONObject addressDetailJson = JSONObject.parseObject(contentJson.getString("address_detail"));
//                log.info("详细地址: " + resp.getString("address"));
//                log.info("省份: " + addressDetailJson.getString("province"));
//                log.info("城市: " + addressDetailJson.getString("city"));
//                log.info("城市代码: " + addressDetailJson.getString("adcode"));
//                result = resp.getString("address");

                String host = "https://ipcity.market.alicloudapi.com";
                String path = "/ip/city/query";
                String appcode = "6b9b3d1c9af24536a0d4aa47f1a985fc";
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "APPCODE " + appcode);
                Map<String, String> querys = new HashMap<String, String>();
                querys.put("ip", ip);
                HttpResponse response = HttpUtils.doGet(host, path, headers, querys);
                JSONObject resultJson = JSONObject.parseObject(EntityUtils.toString(response.getEntity())).getJSONObject("data").getJSONObject("result");
                result = resultJson.getString("country") + "-" +
                        resultJson.getString("areacode") + "|" +
                        resultJson.getString("prov") + "|" +
                        resultJson.getString("city") + "|" +
                        resultJson.getString("isp")+ "|" +
                        resultJson.getString("owner");

                IpQueryLogEntity ipQueryLogEntity = new IpQueryLogEntity();
                ipQueryLogEntity.setIp(ip);
                ipQueryLogEntity.setResult(JSONObject.toJSONString(resultJson));
                ipQueryLogEntity.setSummary(result);
                ipQueryLogEntity.setDate(LocalDateTime.now());
                ipQueryLogRepository.save(ipQueryLogEntity);
            } catch (Exception e) {
                log.warn("Get Location Exception");
            }
        }
        return result;
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

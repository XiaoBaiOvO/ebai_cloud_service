package com.ebai.ebai_cloud_service.common.util.impl;

import com.alibaba.fastjson.JSONObject;
import com.ebai.ebai_cloud_service.model.dto.MailRequest;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static java.util.concurrent.TimeUnit.MINUTES;

@Slf4j
public class InitUtil {

    public static void init() {
        startInit();
        TimerTask heartTask = new TimerTask() {
            public void run() {
                heartService();
            }
        };
        Timer timer = new Timer();
        timer.schedule(heartTask, MINUTES.toMillis(BigInteger.ONE.longValue()), MINUTES.toMillis(BigInteger.TEN.longValue()));
//        timer.schedule(heartTask, 100, 2000);
        log.info("------------ Completed initialization ------------");
    }

    private static void startInit() {
        log.info("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        log.info("----- Ebai Cloud Service Is Initializing ... -----");
        InitUtil initUtil = new InitUtil();
        initUtil.initServiceLocalIp();
        initUtil.startAutoDailyMail();
    }

    private static void heartService() {
        log.info("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        log.info("-------- Ebai Cloud Service Is Running .. --------");
        InitUtil initUtil = new InitUtil();
        initUtil.checkServiceLocalIp();
    }

    private static String localIp;

    private static LocalDateTime startDate;

    private void initServiceLocalIp() {
        NetworkUtil networkUtil = new NetworkUtil();
        JSONObject result = (JSONObject) networkUtil.httpGetClient("https://api-v3.speedtest.cn/ip", true).get("data");
        localIp = result.get("ip").toString();
        startDate = LocalDateTime.now();
        log.info("=> 服务启动成功");
        log.info("=> 本地IP地址: " + localIp);
        MailRequest mail = new MailRequest();
        mail.setTitle("服务器启动成功");
        mail.setMessage("当前IP地址: " + localIp);
        mail.setSender("小白云");
        mail.setRecipient("Administrator");
        mail.setRecipientAccount("2081414628@qq.com");
        networkUtil.sendMail(mail);
        mail.setRecipientAccount("2643372457@qq.com");
        networkUtil.sendMail(mail);
    }

    private void startAutoDailyMail() {
        NetworkUtil networkUtil = new NetworkUtil();
        networkUtil.httpGetClient("http://localhost:9000/internal/mail/initAutoSending", true);
    }

    public void checkServiceLocalIp() {
        NetworkUtil networkUtil = new NetworkUtil();
        JSONObject result = networkUtil.httpGetClient("https://forge.speedtest.cn/api/location/info", 3000);

        String ip = result.get("ip").toString();
        if (!Objects.equals(ip, localIp)) {
            log.warn("本地IP发生变化: {} ==> {}", localIp, ip);
            MailRequest mail = new MailRequest();
            mail.setTitle("服务器IP地址变更");
            mail.setMessage("服务器IP变更: <br>" + localIp + " ==> " + ip);
            mail.setSender("小白云");
            mail.setRecipient("Administrator");
            mail.setRecipientAccount("2081414628@qq.com");
            networkUtil.sendMail(mail);
            localIp = ip;
        }
        String runningTime = Duration.between(startDate, LocalDateTime.now()).toString().substring(2);
        log.info("=> 本地IP地址: {} => 累计运行时长: {}", ip, runningTime);
    }
}

package com.ebai.ebai_cloud_service.common.util.impl;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.Timer;
import java.util.TimerTask;

import static java.util.concurrent.TimeUnit.MINUTES;

@Slf4j
public class InitUtil {

    public static void init() {
        log.info("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        log.info("----- Ebai Cloud Service Is Initializing ... -----");
        TimerTask initTask = new TimerTask() {
            public void run() {
                startAutoDailyMail();
            }
        };
        initTask.run();

        TimerTask heartTask = new TimerTask() {
            public void run() {
                log.info("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
                log.info("-------- Ebai Cloud Service Is Running .. --------");
                checkServiceLocalIp();
            }
        };
        Timer timer = new Timer();
        timer.schedule(heartTask, 100, MINUTES.toMillis(BigInteger.TEN.longValue()));
//        timer.schedule(heartTask, 100, 2000);
        log.info("------------ Completed initialization ------------");
    }

    private static void startAutoDailyMail() {
        NetworkUtil networkUtil = new NetworkUtil();
        networkUtil.initServiceLocalIp();
//        MailServiceImpl MailServiceImpl = new MailServiceImpl();
//        MailServiceImpl.startAutoDailyMail();
        networkUtil.getHttp("http://localhost:9000/startAutoDailyMail");
    }

    private static void checkServiceLocalIp() {
        NetworkUtil networkUtil = new NetworkUtil();
        networkUtil.checkServiceLocalIp();
    }

}

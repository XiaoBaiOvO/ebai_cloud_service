package com.ebai.ebai_cloud_service.common.util.impl;

import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
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
        NetworkUtil networkUtil = new NetworkUtil();
        networkUtil.initServiceLocalIp();
        networkUtil.getHttp("http://localhost:9000/startAutoDailyMail");
    }

    private static void heartService() {
        log.info("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        log.info("-------- Ebai Cloud Service Is Running .. --------");
        NetworkUtil networkUtil = new NetworkUtil();
        networkUtil.checkServiceLocalIp();
    }

}

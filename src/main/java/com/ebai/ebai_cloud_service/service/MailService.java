package com.ebai.ebai_cloud_service.service;

public interface MailService {
    void startAutoDailyMail(Integer timeSet);

    String sendDailyMail() throws Exception;

}

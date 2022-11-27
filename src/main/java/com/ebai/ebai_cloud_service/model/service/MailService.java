package com.ebai.ebai_cloud_service.model.service;

public interface MailService {
    void startAutoDailyMail(Integer timeSet);

    String sendDailyMail() throws Exception;

}

package com.ebai.ebai_cloud_service.controller;

import com.ebai.ebai_cloud_service.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/internal/mail")
public class MailController {

    @Resource
    MailService mailService;

    @RequestMapping(value = "/manualSending")
    public String manualSending() throws Exception {
        return mailService.sendDailyMail();
    }

    @GetMapping(value = "/initAutoSending")
    public void initAutoSending() {
        mailService.startAutoDailyMail(6);
    }
}

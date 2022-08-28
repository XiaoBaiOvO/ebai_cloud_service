package com.ebai.ebai_cloud_service.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.mail.Session;

@Getter
@Setter
@ToString
public class MailRequest {

    private String sender;

    private String recipient;

    private String recipientAccount;

    private String title;

    private String message;

}

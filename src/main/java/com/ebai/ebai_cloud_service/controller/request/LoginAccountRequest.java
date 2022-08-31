package com.ebai.ebai_cloud_service.controller.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginAccountRequest {

    String username;

    String password;

    Boolean autoLogin;

    String type;

    String mobile;

    String captcha;

}

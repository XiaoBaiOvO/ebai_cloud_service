package com.ebai.ebai_cloud_service.controller.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LoginRequest {

    String username;

    String password;

    Boolean autoLogin;

    String loginType;

    String mobile;

    String captcha;

}

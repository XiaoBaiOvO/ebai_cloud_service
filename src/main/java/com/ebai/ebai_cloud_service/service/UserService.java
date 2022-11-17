package com.ebai.ebai_cloud_service.service;

import com.ebai.ebai_cloud_service.controller.request.LoginAccountRequest;
import com.ebai.ebai_cloud_service.controller.response.CurrentUserResponse;
import com.ebai.ebai_cloud_service.controller.response.LoginAccountResponse;
import com.ebai.ebai_cloud_service.model.vo.UserInfoVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    LoginAccountResponse login (LoginAccountRequest request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    String sendMobileCaptcha(LoginAccountRequest request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    CurrentUserResponse checkCurrentUser (HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest);

    CurrentUserResponse outLogin (HttpServletRequest httpServletRequest);
    Boolean register(UserInfoVo userInfoVo);

}

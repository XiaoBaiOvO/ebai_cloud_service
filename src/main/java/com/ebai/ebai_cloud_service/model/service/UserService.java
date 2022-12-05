package com.ebai.ebai_cloud_service.model.service;

import com.ebai.ebai_cloud_service.controller.request.LoginRequest;
import com.ebai.ebai_cloud_service.controller.response.CurrentUserResponse;
import com.ebai.ebai_cloud_service.controller.response.LoginAccountResponse;
import com.ebai.ebai_cloud_service.model.vo.CurrentUserVo;
import com.ebai.ebai_cloud_service.model.vo.UserInfoVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    LoginAccountResponse login (LoginRequest request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    String sendMobileCaptcha(LoginRequest request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    CurrentUserResponse checkCurrentUser (HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest);

    CurrentUserResponse outLogin (HttpServletRequest httpServletRequest);
    Boolean register(UserInfoVo userInfoVo);

    Boolean isLoginSuccess(LoginRequest request);

    CurrentUserVo logCurrentUser(LoginRequest request, HttpServletRequest httpServletRequest);
}

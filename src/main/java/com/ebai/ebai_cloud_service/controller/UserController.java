package com.ebai.ebai_cloud_service.controller;

import com.ebai.ebai_cloud_service.controller.request.LoginRequest;
import com.ebai.ebai_cloud_service.controller.response.CurrentUserResponse;
import com.ebai.ebai_cloud_service.controller.response.RestResponse;
import com.ebai.ebai_cloud_service.model.service.UserService;
import com.ebai.ebai_cloud_service.model.vo.CurrentUserVo;
import com.ebai.ebai_cloud_service.model.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping (value = "/api/user")
public class UserController extends BaseController{

    @Resource
    UserService userService;

    @PostMapping (value = "/login")
    public RestResponse<CurrentUserVo> loginByAccount(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest) {
        log.info("Login By Account => request: {}",request.toString());
        return userService.isLoginSuccess(request) ?
                standardResponse(userService.logCurrentUser(request, httpServletRequest), "登录成功") :
                failResponse("登录失败 请重试");




//        return standardResponse(userService.login(request, httpServletRequest, httpServletResponse));
    }

    @PostMapping (value = "/currentUser")
    public CurrentUserResponse checkCurrentUser (HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        log.info("Check Current User => Session Id: {}", httpServletRequest.getSession().getId());
        return userService.checkCurrentUser(httpServletResponse, httpServletRequest);
    }

    @PostMapping (value = "/outLogin")
    public CurrentUserResponse outLogin (HttpServletRequest httpServletRequest) {
        log.info("Out Login => Session Id: {}", httpServletRequest.getSession().getId());
        return userService.outLogin(httpServletRequest);
    }

    @PostMapping (value = "/login/mobile/captcha")
    public String sendMobileCaptcha(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        log.info("Send Mobile Captcha => Mobile: {}",request.getMobile());
        return userService.sendMobileCaptcha(request, httpServletRequest, httpServletResponse);
    }

    @PostMapping (value = "/register")
    public Boolean register(@RequestBody UserInfoVo userInfoVo) {
        log.info("Out Login => Session request: {}", userInfoVo);
        return userService.register(userInfoVo);
    }

}

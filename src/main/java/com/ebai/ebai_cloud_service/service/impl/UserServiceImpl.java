package com.ebai.ebai_cloud_service.service.impl;

import com.ebai.ebai_cloud_service.controller.request.LoginAccountRequest;
import com.ebai.ebai_cloud_service.controller.response.CurrentUserResponse;
import com.ebai.ebai_cloud_service.controller.response.LoginAccountResponse;
import com.ebai.ebai_cloud_service.mapper.UserInfoRepository;
import com.ebai.ebai_cloud_service.mapper.entity.UserInfoEntity;
import com.ebai.ebai_cloud_service.model.vo.CurrentUserVo;
import com.ebai.ebai_cloud_service.model.vo.UserInfoVo;
import com.ebai.ebai_cloud_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    UserInfoRepository userDetailRepository;

    @Override
    public LoginAccountResponse login(LoginAccountRequest request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if (Objects.equals(request.getType(), "account")) {
            return loginByAccount(request, httpServletRequest, httpServletResponse);
        } else if (Objects.equals(request.getType(), "mobile")) {
            return null;
        } else {
            return loginFailed(httpServletResponse);
        }
    }

    private LoginAccountResponse loginByAccount(LoginAccountRequest request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        UserInfoEntity userInfo = userDetailRepository.findTopByUserNameAndPassword(request.getUsername(), request.getPassword());
        if (Objects.equals(userInfo, null)) {
            return loginFailed(httpServletResponse);
        }
        log.info("User Info: {}", userInfo);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("userName", userInfo.getUserName());
        session.setAttribute("id", userInfo.getId());
        return LoginAccountResponse.builder().status("success").username(userInfo.getUserName()).build();
    }

    private LoginAccountResponse loginFailed(HttpServletResponse httpServletResponse){
        log.info("Login Failed");
        httpServletResponse.setStatus(401);
        return LoginAccountResponse.builder().status("fail").build();
    }

    @Override
    public CurrentUserResponse checkCurrentUser (HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        CurrentUserResponse response = new CurrentUserResponse();
        //        Exception
        if (Objects.equals(httpServletRequest.getSession().getAttribute("id"), null)) {
            httpServletResponse.setStatus(401);
            response.setSuccess(false);
            return response;
        }

        UserInfoEntity userInfo = userDetailRepository.findTopById(httpServletRequest.getSession().getAttribute("id").toString());
        CurrentUserVo currentUserVo = new CurrentUserVo();
        currentUserVo.setName(userInfo.getUserName());
        currentUserVo.setAvatar(userInfo.getAvatar());
        currentUserVo.setUserid(userInfo.getId());
        currentUserVo.setEmail(userInfo.getEmail());
        currentUserVo.setSignature(userInfo.getSignature());
        currentUserVo.setTitle(userInfo.getTitle());
        currentUserVo.setGroup(userInfo.getGroup());
        currentUserVo.setTags(null);
        currentUserVo.setNotifyCount(5);
        currentUserVo.setUnreadCount(5);
        currentUserVo.setCountry(userInfo.getCountry());
        currentUserVo.setAccess(userInfo.getAccess());
        currentUserVo.setGeographic(null);
        currentUserVo.setAddress(userInfo.getAddress());
        currentUserVo.setPhone(userInfo.getMobile());
        response.setData(currentUserVo);
        response.setSuccess(true);
        return response;
    }

    @Override
    public CurrentUserResponse outLogin (HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().invalidate();
        CurrentUserResponse response = new CurrentUserResponse();
        response.setSuccess(true);
        return response;
    }

    @Override
    public Boolean register(UserInfoVo userInfoVo) {
        log.info("注册请求： " + userInfoVo.toString());
        UserInfoEntity entity = UserInfoEntity.builder()
                .userName(userInfoVo.getUserName())
                .password(userInfoVo.getPassword())
                .email(userInfoVo.getEmail())
                .mobileType(userInfoVo.getMobileType())
                .mobile(userInfoVo.getMobile())
                .createDate(new Date())
                .build();
        userDetailRepository.save(entity);
        return true;
    }

}

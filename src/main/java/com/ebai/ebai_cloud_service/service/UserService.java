package com.ebai.ebai_cloud_service.service;

import com.ebai.ebai_cloud_service.model.vo.UserInfoVo;

public interface UserService {
    String login(UserInfoVo userInfoVo);

    Boolean register(UserInfoVo userInfoVo);

}

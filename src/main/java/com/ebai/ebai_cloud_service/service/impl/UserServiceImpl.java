package com.ebai.ebai_cloud_service.service.impl;

import com.ebai.ebai_cloud_service.mapper.UserInfoRepository;
import com.ebai.ebai_cloud_service.mapper.entity.UserInfoEntity;
import com.ebai.ebai_cloud_service.model.vo.UserInfoVo;
import com.ebai.ebai_cloud_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    UserInfoRepository userDetailRepository;

    @Override
    public String login(UserInfoVo userInfoVo) {
        log.info("登录请求： " + userInfoVo.toString());
        List<UserInfoEntity> result = userDetailRepository.findAllByUserNameAndPassword(userInfoVo.getUserName(), userInfoVo.getPassword());
        if (result.size() > 0) {
            return result.get(0).getId();
        } else {
            return null;
        }
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

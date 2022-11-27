package com.ebai.ebai_cloud_service.model.service.impl;

import com.ebai.ebai_cloud_service.mapper.UserInfoRepository;
import com.ebai.ebai_cloud_service.mapper.entity.UserInfoEntity;
import com.ebai.ebai_cloud_service.model.service.BaseService;
import com.ebai.ebai_cloud_service.model.vo.TestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class BaseServiceImpl implements BaseService {

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public void insert() {

        log.info("insert service");

        UserInfoEntity entity = UserInfoEntity.builder()
                .userName("xiaobai")
                .password("123")
                .createDate(new Date())
                .build();
        userInfoRepository.save(entity);

        System.out.println(userInfoRepository.findAll());
    }


    @Override
    public String user() {

        log.info("user api");
        TestVo testVo = new TestVo();

        testVo.setUserName("xiaobai");
        testVo.setPassword("123123");

        System.out.println(testVo);
        System.out.println(testVo.getPassword());

        return testVo.getPassword();
    }

}

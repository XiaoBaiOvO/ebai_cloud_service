package com.ebai.ebai_cloud_service.service.impl;

import com.ebai.ebai_cloud_service.mapper.UserDetailRepository;
import com.ebai.ebai_cloud_service.mapper.entity.UserDetailEntity;
import com.ebai.ebai_cloud_service.model.vo.TestVo;
import com.ebai.ebai_cloud_service.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Slf4j
public class BaseServiceImpl implements BaseService {

    @Autowired
    UserDetailRepository userDetailRepository;


    @Override
    public void insert() {

        log.info("insert service");

//        UserDetailEntity entity = new UserDetailEntity();
//
//        entity.setUserName("aaa");
//        entity.setCreateDate(new Date());

        UserDetailEntity entity = UserDetailEntity.builder().userName("aaa").createDate(new Date()).build();
        userDetailRepository.save(entity);


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

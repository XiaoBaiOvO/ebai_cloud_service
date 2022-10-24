package com.ebai.ebai_cloud_service.controller;

import com.ebai.ebai_cloud_service.common.util.impl.NetworkUtil;
import com.ebai.ebai_cloud_service.mapper.ClassScheduleRepository;
import com.ebai.ebai_cloud_service.mapper.entity.ClassScheduleEntity;
import com.ebai.ebai_cloud_service.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class TestController {

    @Resource
    ClassScheduleRepository classScheduleRepository;

    @Resource
    BaseService baseService;

    @Resource
    NetworkUtil networkUtil;

    @GetMapping(value = "/test")
    public String test() {
        log.info("test success");
        return "success";
    }

    @PostMapping(value = "/api/test")
    public String testLogin() {
        List<String> aa = new ArrayList<>();
        List<ClassScheduleEntity> result1 = classScheduleRepository.findByDateIn(null);
        log.info(String.valueOf(result1.size()));
        return "Success Login";
    }

    @GetMapping(value = "/insert")
    public String insert() {
        log.info("insert");

        baseService.insert();

        return "Success !";
    }

}

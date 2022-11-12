package com.ebai.ebai_cloud_service.controller;

import com.ebai.ebai_cloud_service.common.util.Network;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/internal")
public class CommonController {

    @Resource
    Network network;

    @RequestMapping(value = "/getLocation")
    public String getLocation(@RequestParam String ip) {
        return network.getLocationByIp(ip);
    }

}

package com.ebai.ebai_cloud_service.controller;

import com.ebai.ebai_cloud_service.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping(value = "/internal/log")
public class LogController {

    @Resource
    LogService logService;

    @RequestMapping(value = "/saveServiceRequestLog")
    public void saveServiceRequestLog(@RequestParam String url,
                                      @RequestParam String ip,
                                      @RequestParam String location,
                                      @RequestParam String userAgent,
                                      @RequestParam String userName) {
        logService.saveServiceRequestLog(url, ip, location, userAgent, userName);
    }

    @RequestMapping(value = "/saveIpLocationLog")
    public void saveIpLocationLog(@RequestParam String ip,
                                  @RequestParam String result,
                                  @RequestParam String summary) {
        logService.saveIpLocationLog(ip, result, summary);
    }

}

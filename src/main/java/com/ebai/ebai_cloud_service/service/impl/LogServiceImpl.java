package com.ebai.ebai_cloud_service.service.impl;

import com.ebai.ebai_cloud_service.mapper.IpQueryLogRepository;
import com.ebai.ebai_cloud_service.mapper.ServiceRequestLogRepository;
import com.ebai.ebai_cloud_service.mapper.entity.IpQueryLogEntity;
import com.ebai.ebai_cloud_service.mapper.entity.ServiceRequestLogEntity;
import com.ebai.ebai_cloud_service.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
@Slf4j
public class LogServiceImpl implements LogService {

    @Resource
    ServiceRequestLogRepository serviceRequestLogRepository;

    @Resource
    IpQueryLogRepository ipQueryLogRepository;

    @Override
    public void saveServiceRequestLog(String url, String ip, String location, String userAgent, String userName) {
        ServiceRequestLogEntity entity = new ServiceRequestLogEntity();
        entity.setUrl(url);
        entity.setIp(ip);
        entity.setLocation(location);
        entity.setUserAgent(userAgent);
        entity.setUserName(userName);
        entity.setDate(new Date());
        serviceRequestLogRepository.save(entity);
    }

    @Override
    public void saveIpLocationLog(String ip, String result, String summary) {
        IpQueryLogEntity entity = new IpQueryLogEntity();
        entity.setIp(ip);
        entity.setResult(result);
        entity.setSummary(summary);
        ipQueryLogRepository.save(entity);
    }
}

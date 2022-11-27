package com.ebai.ebai_cloud_service.model.service;

public interface LogService {

    void saveServiceRequestLog(String url, String ip, String location, String userAgent, String userName);

    void saveIpLocationLog(String ip, String result, String summary);

}

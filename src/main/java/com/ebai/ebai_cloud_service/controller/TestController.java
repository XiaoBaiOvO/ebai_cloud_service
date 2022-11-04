package com.ebai.ebai_cloud_service.controller;

import com.alibaba.fastjson.JSONObject;
import com.ebai.ebai_cloud_service.common.util.impl.NetworkUtil;
import com.ebai.ebai_cloud_service.controller.response.NewsListResponse;
import com.ebai.ebai_cloud_service.mapper.ClassScheduleRepository;
import com.ebai.ebai_cloud_service.mapper.entity.ClassScheduleEntity;
import com.ebai.ebai_cloud_service.model.vo.NewsDetailVo;
import com.ebai.ebai_cloud_service.model.vo.RouterVo;
import com.ebai.ebai_cloud_service.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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

    @GetMapping(value = "/getMenuList")
    public List<RouterVo> getMenuList() {
        log.info("Get Menu List");

        List<RouterVo> routerList = new ArrayList<>();
        RouterVo router1 = new RouterVo();
        router1.setTitle("首页");
        router1.setIcon("home");
        router1.setLink("/welcome");
        routerList.add(router1);

        RouterVo router2 = new RouterVo();
        router2.setTitle("菜单一");
        router2.setIcon("profile");
        router2.setOpen(false);

        List<RouterVo> list1 = new ArrayList<>();

        RouterVo router3 = new RouterVo();
        router3.setTitle("目录1");
        router3.setOpen(false);

        List<RouterVo> list2 = new ArrayList<>();
        RouterVo router4 = new RouterVo();
        router4.setTitle("页面1");
        router4.setLink("/11");
        list2.add(router4);
        router3.setChildren(list2);
        list1.add(router3);
        list1.add(router4);
        router2.setChildren(list1);

        routerList.add(router2);
        return routerList;
    }

    @PostMapping(value = "/api/getNewsList")
    public NewsListResponse getNewsList() {
        try {
            CloseableHttpClient httpClient;
            CloseableHttpResponse response;
            httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("https://news.cctv.com/2019/07/gaiban/cmsdatainterface/page/news_1.jsonp?cb=news");
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)
                    .setConnectionRequestTimeout(35000)
                    .setSocketTimeout(60000)
                    .build();
            httpGet.setConfig(requestConfig);
            response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String news = EntityUtils.toString(entity, "UTF-8");
            response.close();
            httpClient.close();
            JSONObject result = JSONObject.parseObject(news.substring(5, news.length()-1));
            JSONObject data = result.getJSONObject("data");
            List<NewsDetailVo> list = (List<NewsDetailVo>) data.get("list");
            NewsListResponse newsListResponse = new NewsListResponse();
            newsListResponse.setNewsList(list.subList(0,5));
            return newsListResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

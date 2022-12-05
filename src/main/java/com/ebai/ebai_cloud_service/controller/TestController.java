package com.ebai.ebai_cloud_service.controller;

import com.alibaba.fastjson.JSONObject;
import com.ebai.ebai_cloud_service.common.util.HttpUtils;
import com.ebai.ebai_cloud_service.mapper.ClassScheduleRepository;
import com.ebai.ebai_cloud_service.model.service.BaseService;
import com.ebai.ebai_cloud_service.model.vo.NewsDetailVo;
import com.ebai.ebai_cloud_service.model.vo.RouterVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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

    @RequestMapping(value = "/api/currentUser")
    public String currentUser() {
        return "Success Login";
    }

    @GetMapping(value = "/api/test")
    public void test() {
        log.info("test");
        try {
            String jsonBody = "{\n" +
                    "    \"username\": \"admin\",\n" +
                    "    \"password\": \"Optical@1cli\",\n" +
                    "    \"token\": \"jj\"\n" +
                    "}";
            JSONObject result = HttpUtils.doPost("http://47.102.195.212:40443/api/login/account", jsonBody);
            log.info(result.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    public List<NewsDetailVo> getNewsList() {
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
//            NewsListResponse newsListResponse = new NewsListResponse();
//            newsListResponse.setNewsList(list.subList(0,5));
//            return newsListResponse;
            return list.subList(0,5);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping(value = "/download")
    public void download(HttpServletResponse response) {
        String fileName = "19042.1469.220115-2112.20H2_RELEASE_SVC_IM_CLIENTPRO_OEMRET_A64FRE_ZH-CN.ISO";
        response.reset();
        response.setContentType("binary/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        String baseFilePath = "D:\\Download\\" + fileName;
        System.out.println(baseFilePath);
        File file = new File(baseFilePath);
        try (
                InputStream in = Files.newInputStream(file.toPath());
                OutputStream os = response.getOutputStream();
        ) {
            Integer fileSize = in.available();
            response.addHeader("content-length", String.valueOf(fileSize));
            response.addHeader("accept-ranges", "bytes");
            byte[] buffer = new byte[1024];
            int length;
            int finished = 0;
            int round = 0;
            while ((length = in.read(buffer)) > 0) {
                os.write(in.available());
                os.write(buffer, 0, length);
                finished ++;
                if (finished >= round * fileSize / 1024 / 10) {
                    log.info(round * 10+"%");round ++;
                }
            }
            os.flush();
            log.info("成功");
        } catch (Exception e) {
            log.warn("失败");
        }
    }

}

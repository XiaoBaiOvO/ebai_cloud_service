package com.ebai.ebai_cloud_service.controller;

import com.ebai.ebai_cloud_service.common.util.Network;
import com.ebai.ebai_cloud_service.controller.request.LoginAccountRequest;
import com.ebai.ebai_cloud_service.controller.response.CurrentUserResponse;
import com.ebai.ebai_cloud_service.controller.response.LoginAccountResponse;
import com.ebai.ebai_cloud_service.model.vo.UserInfoVo;
import com.ebai.ebai_cloud_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Slf4j
@RestController
@CrossOrigin
@RequestMapping (value = "/api/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping (value = "/login")
    public LoginAccountResponse loginByAccount(@RequestBody LoginAccountRequest request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        log.info("Login By Account => request: {}",request.toString());
        return userService.login(request, httpServletRequest, httpServletResponse);
    }

    @PostMapping (value = "/currentUser")
    public CurrentUserResponse checkCurrentUser (HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        log.info("Check Current User => Session Id: {}", httpServletRequest.getSession().getId());
        return userService.checkCurrentUser(httpServletResponse, httpServletRequest);
    }

    @PostMapping (value = "/outLogin")
    public CurrentUserResponse outLogin (HttpServletRequest httpServletRequest) {
        log.info("Out Login => Session Id: {}", httpServletRequest.getSession().getId());
        return userService.outLogin(httpServletRequest);
    }

    @Resource
    Network network;

    @PostMapping (value = "/login/mobile/captcha")
    public String sendMobileCaptcha(@RequestBody LoginAccountRequest request1, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        log.info("Send Mobile Captcha => Mobile: {}",request1);

//        JSONObject captchaResponse = JSONObject.parseObject(EntityUtils.toString(HttpUtils.doPost("https://www.cz88.net", "/api/cz88/user/register", EMPTY_HEADER, null, captchaQuery).getEntity()));
//        log.info(captchaResponse.toString());




//        CloseableHttpClient httpClient;
//        CloseableHttpResponse response;
//        // 通过址默认配置创建一个httpClient实例
//        httpClient = HttpClients.createDefault();
//        // 创建httpGet远程连接实例
//        HttpPost httpPost = new HttpPost("https://www.cz88.net/api/cz88/sms/get?countryCode=%2B86&phone=18964808449&type=register");
//        // 设置请求头信息，鉴权
////        httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
//        // 设置配置请求参数
//        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
//                .setConnectionRequestTimeout(35000)// 请求超时时间
//                .setSocketTimeout(60000)// 数据读取超时时间
//                .build();
//        // 为httpGet实例设置配置
//        httpPost.setConfig(requestConfig);
//        // 执行get请求得到返回对象
//        response = httpClient.execute(httpPost);
//        // 通过返回对象获取返回数据
//        HttpEntity entity = response.getEntity();
//        // 通过EntityUtils中的toString方法将结果转换为字符串
//        JSONObject result = JSONObject.parseObject(EntityUtils.toString(entity));
//        // 关闭资源
//        response.close();
//        httpClient.close();
//        log.info(result.toString());


//        Map<String, String> captchaQuery = new HashMap<>();
//        captchaQuery.put("name", "123");
//        captchaQuery.put("company", "123");
//        captchaQuery.put("phoneNo", "18964808449");
//        captchaQuery.put("countryCode", "86");
//        captchaQuery.put("smsCode", "2144");
//        captchaQuery.put("tokenCode", "a462804c-8f60-4acc-bd5f-c2ba28c22db0");
//        CloseableHttpClient httpClient;
//        CloseableHttpResponse response;
//        // 通过址默认配置创建一个httpClient实例
//        httpClient = HttpClients.createDefault();
//        // 创建httpGet远程连接实例
//        HttpPost httpPost = new HttpPost("https://www.cz88.net/api/cz88/user/register");
//        // 设置请求头信息，鉴权
////        httpGet.setHeader("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
//        // 设置配置请求参数
//        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 连接主机服务超时时间
//                .setConnectionRequestTimeout(35000)// 请求超时时间
//                .setSocketTimeout(60000)// 数据读取超时时间
//                .build();
//        // 为httpGet实例设置配置
//        httpPost.setConfig(requestConfig);
//
//
//        List<BasicNameValuePair> basicNameValuePairList = new ArrayList<BasicNameValuePair>();
//        basicNameValuePairList.add(new BasicNameValuePair("name", "123"));
//        basicNameValuePairList.add(new BasicNameValuePair("company", "123"));
//        basicNameValuePairList.add(new BasicNameValuePair("phoneNo", "18964808449"));
//        basicNameValuePairList.add(new BasicNameValuePair("countryCode", "86"));
//        basicNameValuePairList.add(new BasicNameValuePair("smsCode", "2144"));
//        basicNameValuePairList.add(new BasicNameValuePair("tokenCode", "a462804c-8f60-4acc-bd5f-c2ba28c22db0"));
//        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
//        nameValuePairList.addAll(basicNameValuePairList);
//
//        HttpEntity http = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
//        httpPost.setEntity(http);
//
//
//        // 执行get请求得到返回对象
//        response = httpClient.execute(httpPost);
//        // 通过返回对象获取返回数据
//        HttpEntity entity = response.getEntity();
//        // 通过EntityUtils中的toString方法将结果转换为字符串
//        JSONObject result = JSONObject.parseObject(EntityUtils.toString(entity));
//        // 关闭资源
//        response.close();
//        httpClient.close();
//        log.info(result.toString());







//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost("https://www.cz88.net/api/cz88/user/register");
//        httpPost.setHeader("Accept", "application/json");
//        httpPost.setHeader("Content-Type", "application/json");
//        StringEntity entity = new StringEntity("{\"name\": \"123\",\"company\": \"123\",\"phoneNo\": \"13122744447\",\"countryCode\": \"86\",\"smsCode\": \"3081\",\"tokenCode\": \"bbf4eb01-6c92-43e8-816b-ca1720446e4d\"}", StandardCharsets.UTF_8);
//        httpPost.setEntity(entity);
//        CloseableHttpResponse response = null;
//        // 执行get请求得到返回对象
//        response = httpclient.execute(httpPost);
//        // 通过返回对象获取返回数据
//        HttpEntity httpEntity = response.getEntity();
//        // 通过EntityUtils中的toString方法将结果转换为字符串
//        JSONObject result = JSONObject.parseObject(EntityUtils.toString(httpEntity));
//        // 关闭资源
//        response.close();
//        httpclient.close();
//        log.info(result.toString());

        return "success";
    }

    @PostMapping (value = "/register")
    public Boolean register(@RequestBody UserInfoVo userInfoVo) {
        log.info("Out Login => Session request: {}", userInfoVo);
        return userService.register(userInfoVo);
    }

}

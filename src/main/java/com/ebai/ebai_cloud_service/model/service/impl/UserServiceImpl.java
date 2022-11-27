package com.ebai.ebai_cloud_service.model.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ebai.ebai_cloud_service.common.util.HttpUtils;
import com.ebai.ebai_cloud_service.common.util.Network;
import com.ebai.ebai_cloud_service.controller.request.LoginAccountRequest;
import com.ebai.ebai_cloud_service.controller.response.CurrentUserResponse;
import com.ebai.ebai_cloud_service.controller.response.LoginAccountResponse;
import com.ebai.ebai_cloud_service.mapper.CaptchaTokenRepository;
import com.ebai.ebai_cloud_service.mapper.UserInfoRepository;
import com.ebai.ebai_cloud_service.mapper.entity.CaptchaTokenEntity;
import com.ebai.ebai_cloud_service.mapper.entity.UserInfoEntity;
import com.ebai.ebai_cloud_service.model.service.UserService;
import com.ebai.ebai_cloud_service.model.vo.CurrentUserVo;
import com.ebai.ebai_cloud_service.model.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    UserInfoRepository userDetailRepository;

    @Resource
    CaptchaTokenRepository captchaTokenRepository;

    @Resource
    Network network;

    @Override
    public LoginAccountResponse login(LoginAccountRequest request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        if (Objects.equals(request.getType(), "account")) {
            return loginByAccount(request, httpServletRequest, httpServletResponse);
        } else if (Objects.equals(request.getType(), "mobile")) {
            return loginByMobile(request, httpServletRequest, httpServletResponse);
        } else {
            return loginFailed(httpServletResponse);
        }
    }

    private LoginAccountResponse loginByAccount(LoginAccountRequest request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        UserInfoEntity userInfo = userDetailRepository.findTopByUserNameAndPassword(request.getUsername(), request.getPassword());
        if (Objects.equals(userInfo, null)) {
            return loginFailed(httpServletResponse);
        }
        log.info("User Info: {}", userInfo);
        HttpSession session = httpServletRequest.getSession();
        session.setAttribute("userName", userInfo.getUserName());
        session.setAttribute("id", userInfo.getId());
        return LoginAccountResponse.builder().status("success").username(userInfo.getUserName()).build();
    }

    private LoginAccountResponse loginFailed(HttpServletResponse httpServletResponse){
        log.info("Login Failed");
        httpServletResponse.setStatus(401);
        return LoginAccountResponse.builder().status("fail").build();
    }

    private LoginAccountResponse loginByMobile(LoginAccountRequest request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        CaptchaTokenEntity captchaTokenEntity = captchaTokenRepository.findTopByMobileOrderByCreateDateDesc(request.getMobile());
        try {
            String jsonBody = "{\"name\": \"TEST\",\"company\": \"TEST\",\"phoneNo\": \""
                    + request.getMobile() + "\",\"countryCode\": \"86\",\"smsCode\": \""
                    + request.getCaptcha()+ "\",\"tokenCode\": \""
                    + captchaTokenEntity.getToken() + "\"}";
            JSONObject result = HttpUtils.doPost("https://www.cz88.net/api/cz88/user/register", jsonBody);
            captchaTokenEntity.setResponseJson(result.toString());
            captchaTokenRepository.save(captchaTokenEntity);
            if (Objects.equals(result.get("code").toString(), "5101") || Objects.equals(result.get("code").toString(), "200")) {
//                {"code": 5101,"success": false,"time": "2022-11-16 00:28:11","message": "用户名已存在"}
//                {"code": 200,"success": true,"message": "操作成功","data": null,"time": "2022-11-16 00:26:54"}
                log.info("Login Success ({})", result.get("code").toString());
                UserInfoEntity userInfo = userDetailRepository.findTopByMobile(request.getMobile());
                if (Objects.equals(userInfo, null)) {
                    log.info("Register New Account");
                    UserInfoVo userInfoVo = new UserInfoVo();
                    userInfoVo.setUserName(request.getMobile());
                    userInfoVo.setMobile(request.getMobile());
                    userInfoVo.setMobileType("86");
                    register(userInfoVo);
                    userInfo = userDetailRepository.findTopByMobile(request.getMobile());
                    HttpSession session = httpServletRequest.getSession();
                    session.setAttribute("userName", userInfo.getUserName());
                    session.setAttribute("id", userInfo.getId());
                    return LoginAccountResponse.builder().status("success").username(userInfo.getUserName()).build();
                }
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("userName", userInfo.getUserName());
                session.setAttribute("id", userInfo.getId());
                return LoginAccountResponse.builder().status("success").username(userInfo.getUserName()).build();
            } else {
//                {"code":5103,"success":false,"time":"2022-11-16 00:25:09","message":"验证码不正确或过期"}
                return loginFailed(httpServletResponse);
            }
        } catch (Exception e) {
            log.info("Exception loginByMobile");
        }
        httpServletResponse.setStatus(500);
        return LoginAccountResponse.builder().status("fail").build();
    }

    @Override
    public String sendMobileCaptcha(LoginAccountRequest request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        try {
            String url = "https://www.cz88.net/api/cz88/sms/get?countryCode=%2B86&phone=" + request.getMobile() + "&type=register";
            JSONObject result = network.doPost(url);
            if (Objects.equals(result.get("code").toString(), "200")) {
//            {"code":200,"data":{"tokenCode":"fbeff558-5410-4127-b2b9-7cc4d919dc71"},"success":true,"time":"2022-11-15 23:13:49","message":"操作成功"}
                JSONObject data = JSONObject.parseObject(result.getString("data"));
                log.info("Success");

                CaptchaTokenEntity captchaTokenEntity = new CaptchaTokenEntity();
                captchaTokenEntity.setCountryCode("86");
                captchaTokenEntity.setMobile(request.getMobile());
                captchaTokenEntity.setStatus(result.get("message").toString());
                captchaTokenEntity.setToken(data.get("tokenCode").toString());
                captchaTokenEntity.setRequestJson(result.toString());
                captchaTokenEntity.setCreateDate(new Date());
                captchaTokenRepository.save(captchaTokenEntity);

                return data.get("tokenCode").toString();
            } else {
//            {"code":5101,"success":false,"time":"2022-11-15 23:25:16","message":"无效手机号"}
                httpServletResponse.setStatus(500);
                log.info("Fail");
                return result.get("message").toString();
            }
        } catch (Exception e) {
            log.error("Exception sendMobileCaptcha");
        }
        httpServletResponse.setStatus(500);
        log.info("Exception");
        return "Exception";

//        List<BasicNameValuePair> basicNameValuePairList = new ArrayList<BasicNameValuePair>();
//        basicNameValuePairList.add(new BasicNameValuePair("name", "123"));
//        basicNameValuePairList.add(new BasicNameValuePair("company", "123"));
//        basicNameValuePairList.add(new BasicNameValuePair("phoneNo", "18964808449"));
//        basicNameValuePairList.add(new BasicNameValuePair("countryCode", "86"));
//        basicNameValuePairList.add(new BasicNameValuePair("smsCode", "2144"));
//        basicNameValuePairList.add(new BasicNameValuePair("tokenCode", "a462804c-8f60-4acc-bd5f-c2ba28c22db0"));
//        List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
//        nameValuePairList.addAll(basicNameValuePairList);
//        HttpEntity http = new UrlEncodedFormEntity(nameValuePairList, "UTF-8");
//        httpPost.setEntity(http);
    }

    @Override
    public CurrentUserResponse checkCurrentUser (HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        CurrentUserResponse response = new CurrentUserResponse();
        //        Exception
        if (Objects.equals(httpServletRequest.getSession().getAttribute("id"), null)) {
            httpServletResponse.setStatus(401);
            response.setSuccess(false);
            return response;
        }

        UserInfoEntity userInfo = userDetailRepository.findTopById(httpServletRequest.getSession().getAttribute("id").toString());
        CurrentUserVo currentUserVo = new CurrentUserVo();
        currentUserVo.setName(userInfo.getUserName());
        currentUserVo.setAvatar(userInfo.getAvatar());
        currentUserVo.setUserid(userInfo.getId());
        currentUserVo.setEmail(userInfo.getEmail());
        currentUserVo.setSignature(userInfo.getSignature());
        currentUserVo.setTitle(userInfo.getTitle());
        currentUserVo.setGroup(userInfo.getGroup());
        currentUserVo.setTags(null);
        currentUserVo.setNotifyCount(5);
        currentUserVo.setUnreadCount(5);
        currentUserVo.setCountry(userInfo.getCountry());
        currentUserVo.setAccess(userInfo.getAccess());
        currentUserVo.setGeographic(null);
        currentUserVo.setAddress(userInfo.getAddress());
        currentUserVo.setPhone(userInfo.getMobile());
        response.setData(currentUserVo);
        response.setSuccess(true);
        return response;
    }

    @Override
    public CurrentUserResponse outLogin (HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().invalidate();
        CurrentUserResponse response = new CurrentUserResponse();
        response.setSuccess(true);
        return response;
    }

    @Override
    public Boolean register(UserInfoVo userInfoVo) {
        log.info("注册请求： " + userInfoVo.toString());
        UserInfoEntity entity = UserInfoEntity.builder()
                .userName(userInfoVo.getUserName())
                .password(userInfoVo.getPassword())
                .email(userInfoVo.getEmail())
                .mobileType(userInfoVo.getMobileType())
                .mobile(userInfoVo.getMobile())
                .createDate(new Date())
                .build();
        userDetailRepository.save(entity);
        return true;
    }

}

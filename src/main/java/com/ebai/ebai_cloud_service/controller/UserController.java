package com.ebai.ebai_cloud_service.controller;

import com.alibaba.fastjson.JSONObject;
import com.ebai.ebai_cloud_service.common.util.Network;
import com.ebai.ebai_cloud_service.controller.request.LoginAccountRequest;
import com.ebai.ebai_cloud_service.controller.response.LoginAccountResponse;
import com.ebai.ebai_cloud_service.model.vo.RouterVo;
import com.ebai.ebai_cloud_service.model.vo.UserInfoVo;
import com.ebai.ebai_cloud_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@CrossOrigin
public class UserController {

    @Resource
    UserService userService;

    @Resource
    Network network;

    @PostMapping (value = "/api/login/account")
    public LoginAccountResponse loginAccount(@RequestBody LoginAccountRequest request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        log.info(request.toString());
        if (Objects.equals(request.getType(), "account")) {
            if (Objects.equals(request.getUsername(), "xiaobai") && Objects.equals(request.getPassword(), "123123")) {
                HttpSession session = httpServletRequest.getSession();
                session.setAttribute("username", request.getUsername());
                session.setAttribute("userid", "00000001");
                return LoginAccountResponse.builder().status("ok").username(request.getUsername()).build();
            }

        } else {
            log.info("mobile");
        }
        httpServletResponse.setStatus(401);
        return null;
    }

//    @PostMapping (value = "/api/currentUser")
//    public JSONObject currentUser () {
//        log.info("currentUser");
//        String data = "{\"success\":true,\"data\":{\"name\":\"Xiao Bai\",\"avatar\":\"https://joeschmoe.io/api/v1/random\",\"userid\":\"00000001\",\"email\":\"antdesign@alipay.com\",\"signature\":\"海纳百川，有容乃大\",\"title\":\"交互专家\",\"group\":\"蚂蚁金服－某某某事业群－某某平台部－某某技术部－UED\",\"tags\":[{\"key\":\"0\",\"label\":\"很有想法的\"},{\"key\":\"1\",\"label\":\"专注设计\"},{\"key\":\"2\",\"label\":\"辣~\"},{\"key\":\"3\",\"label\":\"大长腿\"},{\"key\":\"4\",\"label\":\"川妹子\"},{\"key\":\"5\",\"label\":\"海纳百川\"}],\"notifyCount\":12,\"unreadCount\":11,\"country\":\"China\",\"access\":\"admin\",\"geographic\":{\"province\":{\"label\":\"浙江省\",\"key\":\"330000\"},\"city\":{\"label\":\"杭州市\",\"key\":\"330100\"}},\"address\":\"西湖区工专路 77 号\",\"phone\":\"0752-268888888\"}}";
//        JSONObject.toJSON(data);
//        return JSONObject.parseObject(data);
//    }

    @PostMapping (value = "/api/currentUser")
    public JSONObject currentUser (HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest) {
        log.info("currentUser");
        String userAgent = httpServletRequest.getHeader("User-Agent");
        log.info(userAgent);
        log.info(network.getIp(httpServletRequest));
        String data;
        if (httpServletRequest.getSession().getAttribute("username") != null) {
            data = "{\"success\":true,\"data\":{\"name\":\"Xiao Bai\",\"avatar\":\"https://joeschmoe.io/api/v1/random\",\"userid\":\"00000001\",\"email\":\"antdesign@alipay.com\",\"signature\":\"海纳百川，有容乃大\",\"title\":\"交互专家\",\"group\":\"蚂蚁金服－某某某事业群－某某平台部－某某技术部－UED\",\"tags\":[{\"key\":\"0\",\"label\":\"很有想法的\"},{\"key\":\"1\",\"label\":\"专注设计\"},{\"key\":\"2\",\"label\":\"辣~\"},{\"key\":\"3\",\"label\":\"大长腿\"},{\"key\":\"4\",\"label\":\"川妹子\"},{\"key\":\"5\",\"label\":\"海纳百川\"}],\"notifyCount\":12,\"unreadCount\":11,\"country\":\"China\",\"access\":\"admin\",\"geographic\":{\"province\":{\"label\":\"浙江省\",\"key\":\"330000\"},\"city\":{\"label\":\"杭州市\",\"key\":\"330100\"}},\"address\":\"西湖区工专路 77 号\",\"phone\":\"0752-268888888\"}}";
        } else {
            data = "{\n" +
                    "        data: {\n" +
                    "          isLogin: false,\n" +
                    "        },\n" +
                    "        errorCode: '401',\n" +
                    "        errorMessage: '请先登录！',\n" +
                    "        success: true,\n" +
                    "      }";
            httpServletResponse.setStatus(401);
        }
        JSONObject.toJSON(data);
        return JSONObject.parseObject(data);
    }

    @PostMapping (value = "/api/login/outLogin")
    public JSONObject loginOutLogin (HttpServletRequest httpServletRequest) {
        log.info("loginOutLogin");
        HttpSession session = httpServletRequest.getSession();
        session.invalidate();
        String data = "{\"data\":{},\"success\":true}";
        JSONObject.toJSON(data);
        return JSONObject.parseObject(data);
    }

    @PostMapping (value = "/login")
    public String loginAccount(@RequestBody UserInfoVo userInfoVo) {
        return userService.login(userInfoVo);
    }

    @PostMapping (value = "/register")
    public Boolean register(@RequestBody UserInfoVo userInfoVo) {
        return userService.register(userInfoVo);
    }

    @PostMapping (value = "/loggedCheck")
    public Boolean loggedCheck() {
        log.info("logged Check");
        return true;
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


}

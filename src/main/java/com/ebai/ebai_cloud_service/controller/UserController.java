package com.ebai.ebai_cloud_service.controller;

import com.ebai.ebai_cloud_service.model.vo.RouterVo;
import com.ebai.ebai_cloud_service.model.vo.UserInfoVo;
import com.ebai.ebai_cloud_service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class UserController {

    @Resource
    UserService userService;

    @PostMapping (value = "/login")
    public String login(@RequestBody UserInfoVo userInfoVo) {
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

package com.ebai.ebai_cloud_service.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class CurrentUserVo {
    String name;
    String avatar;
    String userid;
    String email;
    String signature;
    String title;
    String group;
    List<String> tags;
    Integer notifyCount;
    Integer unreadCount;
    String country;
    String access;
    String geographic;
    String address;
    String phone;
}

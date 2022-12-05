package com.ebai.ebai_cloud_service.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class CurrentUserVo {
    String userName;
    String userAvatar;
    String userId;
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

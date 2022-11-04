package com.ebai.ebai_cloud_service.mapper.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import sun.net.www.content.text.Generic;

import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_info")
public class UserInfoEntity {

    @Id
    String id;

    String userName;

    String password;

    String email;

    String mobileType;

    String mobile;

    Date createDate;

//    Detail
    String avatar;
    String signature;
    String title;
    String group;
    List<String> tags;
    String country;
    String access;
    Generic geographic;
    String address;

}

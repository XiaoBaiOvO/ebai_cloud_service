package com.ebai.ebai_cloud_service.mapper.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

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

}

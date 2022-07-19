package com.ebai.ebai_cloud_service.mapper.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "UserDetail")
public class UserDetailEntity {

    @Id
    String id;

    String userName;

    Date createDate;

}

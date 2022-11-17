package com.ebai.ebai_cloud_service.mapper.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "captcha_token")
public class CaptchaTokenEntity {

    @Id
    String id;

    String countryCode;

    String mobile;

    String token;

    String requestJson;

    String responseJson;

    String status;

    Date createDate;

}

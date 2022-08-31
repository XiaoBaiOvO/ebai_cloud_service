package com.ebai.ebai_cloud_service.controller.request;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class CurrentUserRequest {

    String error;

    String path;

    Integer status;

    LocalDate timestamp;

}

package com.ebai.ebai_cloud_service.model.vo;

import com.ebai.ebai_cloud_service.annotation.MyAnnotation;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@MyAnnotation(value = "ABC")
public class TestVo {

    String userName;

    @MyAnnotation(value = "ABC")
    private String password;

}

package com.ebai.ebai_cloud_service.controller.request;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class CollectedStatusRequest {

    private String classNumber;

    private String formType;

    private Date date;

}

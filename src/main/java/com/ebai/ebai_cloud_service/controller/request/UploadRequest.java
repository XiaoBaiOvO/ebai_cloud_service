package com.ebai.ebai_cloud_service.controller.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UploadRequest {

    private String formType;

    private String fileType;

    private String name;

    private String classNumber;

    private String fileId;

}

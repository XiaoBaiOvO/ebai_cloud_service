package com.ebai.ebai_cloud_service.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommonResponse {

    private String message;

    private Object data;

}

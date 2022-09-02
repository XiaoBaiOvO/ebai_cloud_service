package com.ebai.ebai_cloud_service.controller.response;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class CurrentUserResponse {

    Boolean success;

    JSONObject data;

}

package com.ebai.ebai_cloud_service.model.dto;

import lombok.Builder;
import lombok.Data;
import org.bson.json.JsonObject;

@Data
@Builder
public class NetworkResponse {

    private JsonObject jsonObject;

}

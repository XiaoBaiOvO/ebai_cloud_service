package com.ebai.ebai_cloud_service.controller.response;

import com.ebai.ebai_cloud_service.model.vo.CurrentUserVo;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CurrentUserResponse {

    Boolean success;

    CurrentUserVo data;

}

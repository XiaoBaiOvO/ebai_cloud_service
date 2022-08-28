package com.ebai.ebai_cloud_service.model.vo;

import com.ebai.ebai_cloud_service.controller.BaseController;
import lombok.Data;

import java.util.List;

@Data
public class RouterVo {

    String title;

    String icon;

    Boolean open;

    String link;

    List<RouterVo> children;

}

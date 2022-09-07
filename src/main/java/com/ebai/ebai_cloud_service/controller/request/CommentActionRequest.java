package com.ebai.ebai_cloud_service.controller.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CommentActionRequest {

    String id;
    String userid;
    String name;
    String avatar;
    String content;

}

package com.ebai.ebai_cloud_service.controller.response;

import com.ebai.ebai_cloud_service.model.vo.NewsDetailVo;
import lombok.Data;

import java.util.List;

@Data
public class NewsListResponse {
    List<NewsDetailVo> newsList;
}

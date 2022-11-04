package com.ebai.ebai_cloud_service.mapper.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "request_log")
public class RequestLogEntity {

    String url;

    String IP;

    String Location;

    String userAgent;

    String userName;

    String date;

}

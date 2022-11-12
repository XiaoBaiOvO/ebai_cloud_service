package com.ebai.ebai_cloud_service.mapper.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "service_request_log")
public class ServiceRequestLogEntity {

    String url;

    String ip;

    String location;

    String userAgent;

    String userName;

    LocalDateTime date;

}

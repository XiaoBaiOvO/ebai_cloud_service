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
@Document(collection = "ip_query_log")
public class IpQueryLogEntity {

    String ip;

    String result;

    String summary;

    LocalDateTime date;

}

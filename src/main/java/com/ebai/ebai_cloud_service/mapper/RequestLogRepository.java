package com.ebai.ebai_cloud_service.mapper;

import com.ebai.ebai_cloud_service.mapper.entity.RequestLogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestLogRepository extends MongoRepository<RequestLogEntity, String> {

}

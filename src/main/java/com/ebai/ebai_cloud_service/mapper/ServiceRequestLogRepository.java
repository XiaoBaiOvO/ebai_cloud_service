package com.ebai.ebai_cloud_service.mapper;

import com.ebai.ebai_cloud_service.mapper.entity.ServiceRequestLogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ServiceRequestLogRepository extends MongoRepository<ServiceRequestLogEntity, String> {

}

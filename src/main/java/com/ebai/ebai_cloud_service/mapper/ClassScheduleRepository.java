package com.ebai.ebai_cloud_service.mapper;

import com.ebai.ebai_cloud_service.mapper.entity.ClassScheduleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClassScheduleRepository  extends MongoRepository<ClassScheduleEntity, String> {

}

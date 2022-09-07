package com.ebai.ebai_cloud_service.mapper;

import com.ebai.ebai_cloud_service.mapper.entity.ClassScheduleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ClassScheduleRepository  extends MongoRepository<ClassScheduleEntity, String> {

    List<ClassScheduleEntity> findAllByDateOrderByOrder(String date);

    List<ClassScheduleEntity> findByDateIn(String date);

}

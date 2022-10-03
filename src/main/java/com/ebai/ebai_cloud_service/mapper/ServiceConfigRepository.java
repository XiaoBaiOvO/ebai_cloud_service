package com.ebai.ebai_cloud_service.mapper;

import com.ebai.ebai_cloud_service.mapper.entity.ServiceConfigEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ServiceConfigRepository extends MongoRepository<ServiceConfigEntity, String> {

   ServiceConfigEntity findTopByName(String name);

   List<ServiceConfigEntity> findAllByName(String name);

}

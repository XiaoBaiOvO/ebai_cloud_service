package com.ebai.ebai_cloud_service.mapper;


import com.ebai.ebai_cloud_service.mapper.entity.CollectionNameEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CollectionNameRepository extends MongoRepository<CollectionNameEntity, String> {

    List<CollectionNameEntity> findAllByRequestFormAndClassNumber(String requestForm, String classNumber);

}

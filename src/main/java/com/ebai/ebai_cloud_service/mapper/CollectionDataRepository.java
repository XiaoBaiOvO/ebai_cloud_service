package com.ebai.ebai_cloud_service.mapper;

import com.ebai.ebai_cloud_service.mapper.entity.CollectionDataEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CollectionDataRepository extends MongoRepository<CollectionDataEntity, String> {

    CollectionDataEntity findTopByFormTypeAndClassNumberAndNameAndDate(String formType, String classNumber, String name, String date);

    List<CollectionDataEntity> findAllByFormTypeAndClassNumberAndDate(String formType, String classNumber, String date);

}

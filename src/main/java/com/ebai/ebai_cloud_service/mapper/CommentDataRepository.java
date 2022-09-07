package com.ebai.ebai_cloud_service.mapper;

import com.ebai.ebai_cloud_service.mapper.entity.CommentDataEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentDataRepository extends MongoRepository<CommentDataEntity, String> {

}

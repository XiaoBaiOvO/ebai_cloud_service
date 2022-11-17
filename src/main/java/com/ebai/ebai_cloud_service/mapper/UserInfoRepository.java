package com.ebai.ebai_cloud_service.mapper;

import com.ebai.ebai_cloud_service.mapper.entity.UserInfoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserInfoRepository extends MongoRepository<UserInfoEntity, String> {

    List<UserInfoEntity> findAllByUserNameAndPassword(String userName, String password);

    UserInfoEntity findTopByUserNameAndPassword(String userName, String password);

    UserInfoEntity findTopByMobile(String mobile);

    UserInfoEntity findTopById(String id);

}

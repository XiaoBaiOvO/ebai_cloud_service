package com.ebai.ebai_cloud_service.mapper;

import com.ebai.ebai_cloud_service.mapper.entity.CaptchaTokenEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CaptchaTokenRepository extends MongoRepository<CaptchaTokenEntity, String> {

    CaptchaTokenEntity findTopByMobileOrderByCreateDateDesc(String mobile);

}

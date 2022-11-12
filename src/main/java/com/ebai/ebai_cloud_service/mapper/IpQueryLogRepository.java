package com.ebai.ebai_cloud_service.mapper;

import com.ebai.ebai_cloud_service.mapper.entity.IpQueryLogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IpQueryLogRepository extends MongoRepository<IpQueryLogEntity, String> {

    List<IpQueryLogEntity> findAllByIp(String ip);

}

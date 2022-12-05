package com.ebai.ebai_cloud_service.mapper.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "collection_name")
public class CollectionNameEntity {

    @Id
    private String id;

    private String classNumber;

    private String name;

    private String requestForm;

}

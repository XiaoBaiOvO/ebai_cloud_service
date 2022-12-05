package com.ebai.ebai_cloud_service.mapper.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "collection_data")
public class CollectionDataEntity {

    @Id
    String id;

    String formType;

    String classNumber;

    String name;

    String location;

    String healthCodeFileId;

    String travelCodeFileId;

    String testScreenshotFileId;

    List<String> position;

    String date;

    String time;

    LocalDateTime datetime;

}

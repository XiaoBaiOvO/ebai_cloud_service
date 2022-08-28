package com.ebai.ebai_cloud_service.mapper.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "class_schedule")
public class ClassScheduleEntity {

    private String causes;

    private String classroom;

    private String date;

    private String time;

    private String order;

    private String teacher;

    private String status;

    private String mark;

}

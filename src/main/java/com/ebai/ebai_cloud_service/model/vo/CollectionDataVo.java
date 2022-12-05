package com.ebai.ebai_cloud_service.model.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CollectionDataVo {

    private String classNumber;

    private String formType;

    private Integer collectedCount;

    private Integer submittedCount;

    private Integer totalCount;

    private Integer reminderCount;

    private List<String> nameList;

    private List<String> reminderList;

}

package com.ebai.ebai_cloud_service.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CollectionRequest {

    private String formType;

    private String classNumber;

    private String name;

    private String location;

    private List<String> position;

    @JsonProperty("twoCodeFile")
    private List<String> twoCodeFileId;

    @JsonProperty("testScreenshotFile")
    private String testScreenshotFileId;

}

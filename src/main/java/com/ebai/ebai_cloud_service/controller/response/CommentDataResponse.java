package com.ebai.ebai_cloud_service.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@ToString
public class CommentDataResponse {

    private static final String dataFormat = "MM/dd/yyyy";

    List<String> actions;
    String author;
    String avatar;
    String content;
    @JsonFormat(pattern = dataFormat)
    LocalDate datetime;
    List<CommentDataResponse> reply;

}

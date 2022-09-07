package com.ebai.ebai_cloud_service.mapper.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "comment_data")
@Builder
public class CommentDataEntity {
    @Id
    String id;
    String commentId;
    String author;
    String avatar;
    String content;
    List<String> likes;
    List<String> dislikes;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime datetime;
    List<CommentDataEntity> reply;
}

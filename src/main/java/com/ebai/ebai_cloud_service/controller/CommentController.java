package com.ebai.ebai_cloud_service.controller;

import com.ebai.ebai_cloud_service.controller.request.CommentActionRequest;
import com.ebai.ebai_cloud_service.mapper.CommentDataRepository;
import com.ebai.ebai_cloud_service.mapper.entity.CommentDataEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin
public class CommentController {

    @Resource
    CommentDataRepository commentDataRepository;

    @PostMapping(value = "/api/comment/getCommentList")
    public List<CommentDataEntity> getCommentList () {
        log.info("getCommentList");
        return getCommentListFromDb();
    }

    @PostMapping (value = "/api/comment/like")
    public List<CommentDataEntity> commentLike (@RequestBody CommentActionRequest request) {
        log.info("commentLike");
        Optional<CommentDataEntity> originComment = commentDataRepository.findById(request.getId());
        if (originComment.isPresent()) {
            CommentDataEntity comment = originComment.get();
            if (comment.getLikes().contains(request.getUserid())) {
                comment.getLikes().remove(request.getUserid());
            } else {
                comment.getLikes().add(request.getUserid());
                comment.getDislikes().remove(request.getUserid());
            }
            commentDataRepository.save(comment);
        } else {
            log.info("id不存在");
        }
        return getCommentListFromDb();
    }

    @PostMapping (value = "/api/comment/dislike")
    public List<CommentDataEntity> commentDislike (@RequestBody CommentActionRequest request) {
        log.info("commentDislike");
        Optional<CommentDataEntity> originComment = commentDataRepository.findById(request.getId());
        if (originComment.isPresent()) {
            CommentDataEntity comment = originComment.get();
            if (comment.getDislikes().contains(request.getUserid())) {
                comment.getDislikes().remove(request.getUserid());
            } else {
                comment.getDislikes().add(request.getUserid());
                comment.getLikes().remove(request.getUserid());
            }
            commentDataRepository.save(comment);
        } else {
            log.info("id不存在");
        }
        return getCommentListFromDb();
    }

    @PostMapping (value = "/api/comment/reply")
    public List<CommentDataEntity> commentReply (@RequestBody CommentActionRequest request) {
        log.info("commentReply");
        Optional<CommentDataEntity> originComment = commentDataRepository.findById(request.getId());
        if (originComment.isPresent()) {
            CommentDataEntity comment = originComment.get();
            CommentDataEntity reply = CommentDataEntity.builder()
                    .id(String.valueOf(comment.getReply().size() + 1))
                    .author(request.getName())
                    .avatar(request.getAvatar())
                    .content(request.getContent())
                    .datetime(LocalDateTime.now())
                    .build();
            comment.getReply().add(reply);
            commentDataRepository.save(comment);
        } else {
            log.info("id不存在");
        }
        return getCommentListFromDb();
    }

    private List<CommentDataEntity> getCommentListFromDb () {
//        String a = UUID.randomUUID().toString().replaceAll("-", "");
//        log.info(a);
        List<CommentDataEntity> result = commentDataRepository.findAll();
        for (CommentDataEntity entity : result) {
//            Collections.reverse(entity.getReply());
        }
        return result;
    }

}

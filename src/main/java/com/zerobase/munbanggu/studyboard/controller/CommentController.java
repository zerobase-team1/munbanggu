package com.zerobase.munbanggu.studyboard.controller;

import static com.zerobase.munbanggu.type.ErrorCode.INVALID_TOKEN;

import com.zerobase.munbanggu.studyboard.model.dto.CommentRequest;
import com.zerobase.munbanggu.studyboard.service.CommentService;
import com.zerobase.munbanggu.user.exception.InvalidTokenException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post-comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;


    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_PREFIX = "Bearer ";

    @PostMapping("/{post_id}")
    public ResponseEntity<?> create(@PathVariable("post_id") Long postId, @RequestBody CommentRequest commentRequest,
            @RequestHeader(value = AUTHORIZATION_HEADER) String authHeader) {
        if (!StringUtils.hasText(authHeader)) {
            throw new InvalidTokenException(INVALID_TOKEN);
        }

        String token = authHeader.replace(AUTHORIZATION_PREFIX, "");
        commentService.create(postId, commentRequest, token);
        return ResponseEntity.ok().body("댓글이 작성되었습니다.");
    }
}
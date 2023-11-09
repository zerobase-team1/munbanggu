package com.zerobase.munbanggu.studyboard.model.dto;

import com.zerobase.munbanggu.studyboard.model.entity.StudyBoardPost;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostResponse {

    private Long id;

    private String title;

    private LocalDateTime createdDate;

    public static PostResponse from(StudyBoardPost post) {
        return PostResponse.builder().id(post.getId()).title(post.getTitle())
                .createdDate(post.getCreatedDate()).build();

    }
}
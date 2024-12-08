package com.traffictest.common.entity.dto;

public record CommentDto(
        Long id,
        String content,
        Long writerId,
        String writerName
) {
}

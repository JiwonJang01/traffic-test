package com.traffictest.common.entity;

import com.traffictest.common.entity.dto.BoardSummaryDto;

import java.util.List;

public interface BoardRepositoryCustom {
    List<BoardSummaryDto> findBoardSummaryDtos(String hashTag, String subjectLike);
}

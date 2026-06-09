package com.hubilon.modules.schedule.adapter.in.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hubilon.modules.schedule.domain.model.ScheduleStatus;

import java.time.LocalDateTime;

public record ScheduleListResponse(
        Long id,
        String title,
        @JsonFormat(pattern = "yyyyMMddHHmmss")
        LocalDateTime startDateTime,
        @JsonFormat(pattern = "yyyyMMddHHmmss")
        LocalDateTime endDateTime,
        String location,
        ScheduleStatus status
) {
}

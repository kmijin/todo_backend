package com.hubilon.modules.schedule.application.dto;

import com.hubilon.modules.schedule.domain.model.ScheduleStatus;

import java.time.LocalDateTime;

public record ScheduleListResult(
        Long id,
        String title,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        String location,
        ScheduleStatus status
) {
}

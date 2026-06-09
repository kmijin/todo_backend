package com.hubilon.modules.schedule.application.dto;

import com.hubilon.modules.schedule.domain.model.ScheduleStatus;

import java.time.LocalDate;

public record ScheduleSearchQuery(
        String title,
        ScheduleStatus status,
        LocalDate startDate,
        LocalDate endDate
) {
}

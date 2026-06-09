package com.hubilon.modules.schedule.domain.search;

import com.hubilon.modules.schedule.domain.model.ScheduleStatus;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ScheduleSearchFilter(
        String title,
        ScheduleStatus status,
        LocalDate startDate,
        LocalDate endDate
) {
}

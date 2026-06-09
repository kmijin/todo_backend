package com.hubilon.modules.schedule.adapter.in.web;

import com.hubilon.modules.schedule.domain.model.ScheduleStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ScheduleRegisterRequest(
        @NotBlank(message = "제목은 필수입니다.")
        @Size(max = 200, message = "제목은 200자를 초과할 수 없습니다.")
        @Schema(description = "제목", example = "팀 회의")
        String title,

        @Size(max = 2000, message = "설명은 2000자를 초과할 수 없습니다.")
        @Schema(description = "설명", example = "주간 팀 회의입니다.")
        String description,

        @NotNull(message = "시작 일시는 필수입니다.")
        @Schema(description = "시작 일시 (yyyyMMddHHmmss)", example = "20260610100000", type = "string")
        LocalDateTime startDateTime,

        @NotNull(message = "종료 일시는 필수입니다.")
        @Schema(description = "종료 일시 (yyyyMMddHHmmss)", example = "20260610110000", type = "string")
        LocalDateTime endDateTime,

        @Size(max = 300, message = "장소는 300자를 초과할 수 없습니다.")
        @Schema(description = "장소", example = "회의실 A")
        String location,

        @Schema(description = "상태", example = "PLANNED")
        ScheduleStatus status
) {
}

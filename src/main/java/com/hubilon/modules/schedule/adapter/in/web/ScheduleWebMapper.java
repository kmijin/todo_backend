package com.hubilon.modules.schedule.adapter.in.web;

import com.hubilon.modules.schedule.application.dto.ScheduleDetailResult;
import com.hubilon.modules.schedule.application.dto.ScheduleListResult;
import com.hubilon.modules.schedule.application.dto.ScheduleModifyCommand;
import com.hubilon.modules.schedule.application.dto.ScheduleRegisterCommand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleWebMapper {

    ScheduleRegisterCommand toRegisterCommand(ScheduleRegisterRequest request);

    default ScheduleModifyCommand toModifyCommand(Long id, ScheduleModifyRequest request) {
        return new ScheduleModifyCommand(
                id,
                request.title(),
                request.description(),
                request.startDateTime(),
                request.endDateTime(),
                request.location(),
                request.status()
        );
    }

    ScheduleRegisterResponse toRegisterResponse(ScheduleDetailResult result);

    ScheduleDetailResponse toDetailResponse(ScheduleDetailResult result);

    ScheduleListResponse toListResponse(ScheduleListResult result);
}

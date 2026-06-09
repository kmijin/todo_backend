package com.hubilon.modules.schedule.application.mapper;

import com.hubilon.modules.schedule.application.dto.ScheduleDetailResult;
import com.hubilon.modules.schedule.application.dto.ScheduleListResult;
import com.hubilon.modules.schedule.application.dto.ScheduleModifyCommand;
import com.hubilon.modules.schedule.application.dto.ScheduleRegisterCommand;
import com.hubilon.modules.schedule.domain.model.Schedule;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ScheduleAppMapper {

    Schedule toSchedule(ScheduleRegisterCommand command);

    ScheduleDetailResult toDetailResult(Schedule schedule);

    ScheduleListResult toListResult(Schedule schedule);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    default Schedule updateDomain(Schedule schedule, ScheduleModifyCommand command) {
        Schedule.ScheduleBuilder builder = schedule.toBuilder();
        if (command.title() != null) {
            builder.title(command.title());
        }
        if (command.description() != null) {
            builder.description(command.description());
        }
        if (command.startDateTime() != null) {
            builder.startDateTime(command.startDateTime());
        }
        if (command.endDateTime() != null) {
            builder.endDateTime(command.endDateTime());
        }
        if (command.location() != null) {
            builder.location(command.location());
        }
        if (command.status() != null) {
            builder.status(command.status());
        }
        return builder.build();
    }
}

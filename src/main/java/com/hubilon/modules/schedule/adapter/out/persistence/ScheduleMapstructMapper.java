package com.hubilon.modules.schedule.adapter.out.persistence;

import com.hubilon.modules.schedule.domain.model.Schedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapstructMapper {

    ScheduleJpaEntity toJpaEntity(Schedule schedule);

    Schedule toDomain(ScheduleJpaEntity entity);
}

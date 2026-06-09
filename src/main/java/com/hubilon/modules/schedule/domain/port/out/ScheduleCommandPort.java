package com.hubilon.modules.schedule.domain.port.out;

import com.hubilon.modules.schedule.domain.model.Schedule;

public interface ScheduleCommandPort {

    Schedule save(Schedule schedule);

    void deleteById(Long id);
}

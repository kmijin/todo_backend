package com.hubilon.modules.schedule.domain.port.in;

import com.hubilon.modules.schedule.application.dto.ScheduleRegisterCommand;
import com.hubilon.modules.schedule.application.dto.ScheduleDetailResult;

public interface ScheduleRegisterUseCase {

    ScheduleDetailResult register(ScheduleRegisterCommand command);
}

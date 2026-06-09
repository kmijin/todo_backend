package com.hubilon.modules.schedule.domain.port.in;

import com.hubilon.modules.schedule.application.dto.ScheduleModifyCommand;
import com.hubilon.modules.schedule.application.dto.ScheduleDetailResult;

public interface ScheduleModifyUseCase {

    ScheduleDetailResult modify(ScheduleModifyCommand command);
}

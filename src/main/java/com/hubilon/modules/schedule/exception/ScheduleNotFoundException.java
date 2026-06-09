package com.hubilon.modules.schedule.exception;

import com.hubilon.common.exception.custom.NotFoundException;

public class ScheduleNotFoundException extends NotFoundException {

    public ScheduleNotFoundException(Long id) {
        super("일정을 찾을 수 없습니다. id=" + id);
    }

    public ScheduleNotFoundException(String message) {
        super(message);
    }
}

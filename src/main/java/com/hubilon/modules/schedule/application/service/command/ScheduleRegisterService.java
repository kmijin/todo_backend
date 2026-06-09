package com.hubilon.modules.schedule.application.service.command;

import com.hubilon.modules.schedule.application.dto.ScheduleDetailResult;
import com.hubilon.modules.schedule.application.dto.ScheduleRegisterCommand;
import com.hubilon.modules.schedule.application.mapper.ScheduleAppMapper;
import com.hubilon.modules.schedule.domain.model.Schedule;
import com.hubilon.modules.schedule.domain.port.in.ScheduleRegisterUseCase;
import com.hubilon.modules.schedule.domain.port.out.ScheduleCommandPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleRegisterService implements ScheduleRegisterUseCase {

    private final ScheduleCommandPort scheduleCommandPort;
    private final ScheduleAppMapper scheduleAppMapper;

    @Override
    public ScheduleDetailResult register(ScheduleRegisterCommand command) {
        log.info("Registering schedule with title: {}", command.title());
        Schedule schedule = scheduleAppMapper.toSchedule(command);
        Schedule saved = scheduleCommandPort.save(schedule);
        return scheduleAppMapper.toDetailResult(saved);
    }
}

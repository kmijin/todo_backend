package com.hubilon.modules.schedule.application.service.command;

import com.hubilon.modules.schedule.application.dto.ScheduleDetailResult;
import com.hubilon.modules.schedule.application.dto.ScheduleModifyCommand;
import com.hubilon.modules.schedule.application.mapper.ScheduleAppMapper;
import com.hubilon.modules.schedule.domain.model.Schedule;
import com.hubilon.modules.schedule.domain.port.in.ScheduleModifyUseCase;
import com.hubilon.modules.schedule.domain.port.out.ScheduleCommandPort;
import com.hubilon.modules.schedule.domain.port.out.ScheduleQueryPort;
import com.hubilon.modules.schedule.exception.ScheduleNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleModifyService implements ScheduleModifyUseCase {

    private final ScheduleQueryPort scheduleQueryPort;
    private final ScheduleCommandPort scheduleCommandPort;
    private final ScheduleAppMapper scheduleAppMapper;

    @Override
    public ScheduleDetailResult modify(ScheduleModifyCommand command) {
        log.info("Modifying schedule with id: {}", command.id());
        Schedule schedule = scheduleQueryPort.findById(command.id())
                .orElseThrow(() -> new ScheduleNotFoundException(command.id()));
        Schedule updated = scheduleAppMapper.updateDomain(schedule, command);
        Schedule saved = scheduleCommandPort.save(updated);
        return scheduleAppMapper.toDetailResult(saved);
    }
}

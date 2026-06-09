package com.hubilon.modules.schedule.application.service.command;

import com.hubilon.modules.schedule.domain.port.in.ScheduleDeleteUseCase;
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
public class ScheduleDeleteService implements ScheduleDeleteUseCase {

    private final ScheduleQueryPort scheduleQueryPort;
    private final ScheduleCommandPort scheduleCommandPort;

    @Override
    public void delete(Long id) {
        log.info("Deleting schedule with id: {}", id);
        if (!scheduleQueryPort.existsById(id)) {
            throw new ScheduleNotFoundException(id);
        }
        scheduleCommandPort.deleteById(id);
    }
}

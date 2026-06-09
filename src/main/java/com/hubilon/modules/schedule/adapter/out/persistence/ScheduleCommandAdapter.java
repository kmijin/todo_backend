package com.hubilon.modules.schedule.adapter.out.persistence;

import com.hubilon.modules.schedule.domain.model.Schedule;
import com.hubilon.modules.schedule.domain.port.out.ScheduleCommandPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduleCommandAdapter implements ScheduleCommandPort {

    private final ScheduleJpaRepository scheduleJpaRepository;
    private final ScheduleMapstructMapper scheduleMapstructMapper;

    @Override
    public Schedule save(Schedule schedule) {
        ScheduleJpaEntity entity = scheduleMapstructMapper.toJpaEntity(schedule);
        ScheduleJpaEntity saved = scheduleJpaRepository.saveAndFlush(entity);
        return scheduleMapstructMapper.toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        scheduleJpaRepository.deleteById(id);
    }
}

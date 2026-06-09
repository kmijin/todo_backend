package com.hubilon.modules.schedule.adapter.out.persistence;

import com.hubilon.common.page.PageResult;
import com.hubilon.modules.schedule.domain.model.Schedule;
import com.hubilon.modules.schedule.domain.port.out.ScheduleQueryPort;
import com.hubilon.modules.schedule.domain.search.ScheduleSearchFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScheduleQueryAdapter implements ScheduleQueryPort {

    private final ScheduleJpaRepository scheduleJpaRepository;
    private final ScheduleMapstructMapper scheduleMapstructMapper;

    @Override
    public Optional<Schedule> findById(Long id) {
        return scheduleJpaRepository.findById(id)
                .map(scheduleMapstructMapper::toDomain);
    }

    @Override
    public boolean existsById(Long id) {
        return scheduleJpaRepository.existsById(id);
    }

    @Override
    public PageResult<Schedule> findAll(ScheduleSearchFilter filter, PageRequest pageRequest) {
        Page<ScheduleJpaEntity> page = scheduleJpaRepository.findAllByFilter(filter, pageRequest);
        return PageResult.of(
                page.getContent().stream()
                        .map(scheduleMapstructMapper::toDomain)
                        .toList(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements()
        );
    }
}

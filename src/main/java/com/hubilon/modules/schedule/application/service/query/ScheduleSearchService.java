package com.hubilon.modules.schedule.application.service.query;

import com.hubilon.common.page.PageResult;
import com.hubilon.modules.schedule.application.dto.ScheduleDetailResult;
import com.hubilon.modules.schedule.application.dto.ScheduleListResult;
import com.hubilon.modules.schedule.application.dto.ScheduleSearchQuery;
import com.hubilon.modules.schedule.application.mapper.ScheduleAppMapper;
import com.hubilon.modules.schedule.domain.model.Schedule;
import com.hubilon.modules.schedule.domain.port.in.ScheduleSearchUseCase;
import com.hubilon.modules.schedule.domain.port.out.ScheduleQueryPort;
import com.hubilon.modules.schedule.domain.search.ScheduleSearchFilter;
import com.hubilon.modules.schedule.exception.ScheduleNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleSearchService implements ScheduleSearchUseCase {

    private final ScheduleQueryPort scheduleQueryPort;
    private final ScheduleAppMapper scheduleAppMapper;

    @Override
    public ScheduleDetailResult searchDetail(Long id) {
        log.info("Searching schedule detail for id: {}", id);
        Schedule schedule = scheduleQueryPort.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException(id));
        return scheduleAppMapper.toDetailResult(schedule);
    }

    @Override
    public PageResult<ScheduleListResult> searchList(ScheduleSearchQuery query, PageRequest pageRequest) {
        log.info("Searching schedule list with query: {}", query);
        ScheduleSearchFilter filter = ScheduleSearchFilter.builder()
                .title(query.title())
                .status(query.status())
                .startDate(query.startDate())
                .endDate(query.endDate())
                .build();
        PageResult<Schedule> pageResult = scheduleQueryPort.findAll(filter, pageRequest);
        return PageResult.of(
                pageResult.content().stream()
                        .map(scheduleAppMapper::toListResult)
                        .toList(),
                pageResult.page(),
                pageResult.size(),
                pageResult.totalElements()
        );
    }
}

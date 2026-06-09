package com.hubilon.modules.schedule.domain.port.out;

import com.hubilon.modules.schedule.domain.model.Schedule;
import com.hubilon.modules.schedule.domain.search.ScheduleSearchFilter;
import com.hubilon.common.page.PageResult;
import org.springframework.data.domain.PageRequest;

import java.util.Optional;

public interface ScheduleQueryPort {

    Optional<Schedule> findById(Long id);

    boolean existsById(Long id);

    PageResult<Schedule> findAll(ScheduleSearchFilter filter, PageRequest pageRequest);
}

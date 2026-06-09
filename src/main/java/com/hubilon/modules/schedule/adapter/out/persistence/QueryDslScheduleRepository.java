package com.hubilon.modules.schedule.adapter.out.persistence;

import com.hubilon.modules.schedule.domain.search.ScheduleSearchFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QueryDslScheduleRepository {

    Page<ScheduleJpaEntity> findAllByFilter(ScheduleSearchFilter filter, Pageable pageable);
}

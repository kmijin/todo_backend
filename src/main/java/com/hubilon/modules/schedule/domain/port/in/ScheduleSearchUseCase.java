package com.hubilon.modules.schedule.domain.port.in;

import com.hubilon.modules.schedule.application.dto.ScheduleDetailResult;
import com.hubilon.modules.schedule.application.dto.ScheduleListResult;
import com.hubilon.modules.schedule.application.dto.ScheduleSearchQuery;
import com.hubilon.common.page.PageResult;
import org.springframework.data.domain.PageRequest;

public interface ScheduleSearchUseCase {

    ScheduleDetailResult searchDetail(Long id);

    PageResult<ScheduleListResult> searchList(ScheduleSearchQuery query, PageRequest pageRequest);
}

package com.hubilon.modules.schedule.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleJpaRepository extends JpaRepository<ScheduleJpaEntity, Long>, QueryDslScheduleRepository {
}

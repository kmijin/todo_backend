package com.hubilon.modules.schedule.adapter.out.persistence;

import com.hubilon.modules.schedule.domain.search.ScheduleSearchFilter;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import java.util.List;

@RequiredArgsConstructor
public class QueryDslScheduleRepositoryImpl implements QueryDslScheduleRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ScheduleJpaEntity> findAllByFilter(ScheduleSearchFilter filter, Pageable pageable) {
        QScheduleJpaEntity schedule = QScheduleJpaEntity.scheduleJpaEntity;
        BooleanBuilder predicate = buildPredicate(filter, schedule);

        List<ScheduleJpaEntity> content = queryFactory
                .selectFrom(schedule)
                .where(predicate)
                .orderBy(schedule.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(schedule.count())
                .from(schedule)
                .where(predicate)
                .fetchOne();

        return new PageImpl<>(content, pageable, total == null ? 0L : total);
    }

    private BooleanBuilder buildPredicate(ScheduleSearchFilter filter, QScheduleJpaEntity schedule) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (StringUtils.hasText(filter.title())) {
            predicate.and(schedule.title.containsIgnoreCase(filter.title()));
        }

        if (filter.status() != null) {
            predicate.and(schedule.status.eq(filter.status()));
        }

        if (filter.startDate() != null) {
            predicate.and(schedule.startDateTime.goe(filter.startDate().atStartOfDay()));
        }

        if (filter.endDate() != null) {
            predicate.and(schedule.endDateTime.loe(filter.endDate().plusDays(1).atStartOfDay().minusNanos(1)));
        }

        return predicate;
    }
}

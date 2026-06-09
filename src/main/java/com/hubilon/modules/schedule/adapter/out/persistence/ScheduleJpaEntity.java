package com.hubilon.modules.schedule.adapter.out.persistence;

import com.hubilon.modules.schedule.domain.model.ScheduleStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Entity
@Table(name = "schedule")
@EntityListeners(AuditingEntityListener.class)
public class ScheduleJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("일정 ID")
    private Long id;

    @Comment("제목")
    @Column(nullable = false, length = 200)
    private String title;

    @Comment("설명")
    @Column(length = 2000)
    private String description;

    @Comment("시작 일시")
    @Column(nullable = false)
    private LocalDateTime startDateTime;

    @Comment("종료 일시")
    @Column(nullable = false)
    private LocalDateTime endDateTime;

    @Comment("장소")
    @Column(length = 300)
    private String location;

    @Comment("상태")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ScheduleStatus status;

    @Comment("생성 일시")
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Comment("수정 일시")
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;
}

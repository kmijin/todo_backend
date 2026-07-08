# Migration Notes

> 생성일: 2026-07-08
> 브랜치: `migration/2026-07-08-version-bump`
> 생성 도구: Tech Stack Updater

---

## 버전 변경 요약

| 스택 | 현재 | 목표 |
|------|------|------|
| Java | `17` | `25` |
| Spring Boot | `4.0` | `4.0.5` |

## ✅ 자동 적용 완료

### 설정 파일 (1개)
- `pom.xml`

## ⚠️ 수동 작업 필요 (2개)

### `src/main/java/com/hubilon/config/QueryDslConfig.java`

> Anthropic API 키 없음 — AI 자동 변환 미실행

**L3** — JPAQueryFactory jakarta 패키지

```
import com.querydsl.jpa.impl.JPAQueryFactory;
```

### `src/main/java/com/hubilon/modules/schedule/adapter/out/persistence/QueryDslScheduleRepositoryImpl.java`

> Anthropic API 키 없음 — AI 자동 변환 미실행

**L5** — JPAQueryFactory jakarta 패키지

```
import com.querydsl.jpa.impl.JPAQueryFactory;
```

## 📋 PR 머지 전 검토 체크리스트

### 공통

- [ ] **🔴 필수** 자동화 테스트(단위/통합) 전체 실행 후 통과 여부 확인
- [ ] **🔴 필수** 스테이징 환경 배포 후 핵심 비즈니스 기능 직접 검증
- [ ] **🟡 권장** application.yml / .env 설정값 변경·추가 항목 확인
- [ ] **🟡 권장** 의존성 충돌 여부 확인 (빌드 경고 메시지 검토)
- [ ] **🟡 권장** 테스트 코드 수정 — deprecated API 제거·변경에 따른 컴파일 오류 직접 수정

### Java 21

- [ ] **🔴 필수** finalize() 지원 종료 예정 — Cleaner / AutoCloseable 교체
- [ ] **🟡 권장** Virtual Thread 도입 시 ThreadLocal → ScopedValue 전환 검토

### Java 25

- [ ] **🔴 필수** Lombok TypeTag.UNKNOWN 오류 — Lombok 1.18.36+ 업그레이드 또는 --add-exports 플래그 추가
- [ ] **🔴 필수** QueryDSL Q 클래스 미생성 시 APT 설정 확인 — annotationProcessor 순서: Lombok → QueryDSL

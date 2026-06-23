# Migration Notes

> 생성일: 2026-06-23
> 브랜치: `migration/2026-06-23-version-bump`
> 생성 도구: Tech Stack Updater

---

## 버전 변경 요약

| 스택 | 현재 | 목표 |
|------|------|------|
| Java | `17` | `25` |
| Spring Boot | `4.0` | `4.1` |

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

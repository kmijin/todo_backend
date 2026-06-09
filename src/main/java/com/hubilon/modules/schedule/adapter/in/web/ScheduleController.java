package com.hubilon.modules.schedule.adapter.in.web;

import com.hubilon.common.page.PageResult;
import com.hubilon.common.response.ApiResponse;
import com.hubilon.modules.schedule.application.dto.ScheduleSearchQuery;
import com.hubilon.modules.schedule.domain.model.ScheduleStatus;
import com.hubilon.modules.schedule.domain.port.in.ScheduleDeleteUseCase;
import com.hubilon.modules.schedule.domain.port.in.ScheduleModifyUseCase;
import com.hubilon.modules.schedule.domain.port.in.ScheduleRegisterUseCase;
import com.hubilon.modules.schedule.domain.port.in.ScheduleSearchUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@Tag(name = "Schedule", description = "일정 관리 API")
@RestController
@RequestMapping("/api/v1/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleRegisterUseCase scheduleRegisterUseCase;
    private final ScheduleModifyUseCase scheduleModifyUseCase;
    private final ScheduleDeleteUseCase scheduleDeleteUseCase;
    private final ScheduleSearchUseCase scheduleSearchUseCase;
    private final ScheduleWebMapper scheduleWebMapper;

    @Operation(summary = "일정 등록", description = "새로운 일정을 등록합니다.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<ScheduleRegisterResponse> register(
            @RequestBody @Valid ScheduleRegisterRequest request
    ) {
        var result = scheduleRegisterUseCase.register(scheduleWebMapper.toRegisterCommand(request));
        return ApiResponse.ok(scheduleWebMapper.toRegisterResponse(result), "일정이 등록되었습니다.");
    }

    @Operation(summary = "일정 상세 조회", description = "일정 ID로 상세 정보를 조회합니다.")
    @GetMapping("/{id}")
    public ApiResponse<ScheduleDetailResponse> getDetail(
            @Parameter(description = "일정 ID") @PathVariable Long id
    ) {
        var result = scheduleSearchUseCase.searchDetail(id);
        return ApiResponse.ok(scheduleWebMapper.toDetailResponse(result));
    }

    @Operation(summary = "일정 목록 조회", description = "조건에 따라 일정 목록을 페이징으로 조회합니다.")
    @GetMapping
    public ApiResponse<PageResult<ScheduleListResponse>> getList(
            @Parameter(description = "제목 검색어") @RequestParam(required = false) String title,
            @Parameter(description = "일정 상태") @RequestParam(required = false) ScheduleStatus status,
            @Parameter(description = "시작일 (yyyy-MM-dd)") @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @Parameter(description = "종료일 (yyyy-MM-dd)") @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 크기") @RequestParam(defaultValue = "20") int size
    ) {
        ScheduleSearchQuery query = new ScheduleSearchQuery(title, status, startDate, endDate);
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        PageResult<com.hubilon.modules.schedule.application.dto.ScheduleListResult> result =
                scheduleSearchUseCase.searchList(query, pageRequest);
        PageResult<ScheduleListResponse> response = PageResult.of(
                result.content().stream().map(scheduleWebMapper::toListResponse).toList(),
                result.page(),
                result.size(),
                result.totalElements()
        );
        return ApiResponse.ok(response);
    }

    @Operation(summary = "일정 수정", description = "일정 정보를 수정합니다.")
    @PutMapping("/{id}")
    @PatchMapping("/{id}")
    public ApiResponse<ScheduleDetailResponse> modify(
            @Parameter(description = "일정 ID") @PathVariable Long id,
            @RequestBody @Valid ScheduleModifyRequest request
    ) {
        var result = scheduleModifyUseCase.modify(scheduleWebMapper.toModifyCommand(id, request));
        return ApiResponse.ok(scheduleWebMapper.toDetailResponse(result), "일정이 수정되었습니다.");
    }

    @Operation(summary = "일정 삭제", description = "일정을 삭제합니다.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @Parameter(description = "일정 ID") @PathVariable Long id
    ) {
        scheduleDeleteUseCase.delete(id);
    }
}

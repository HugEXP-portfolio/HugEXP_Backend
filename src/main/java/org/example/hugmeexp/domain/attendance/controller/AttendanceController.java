package org.example.hugmeexp.domain.attendance.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.hugmeexp.domain.attendance.dto.AttendanceCheckResponse;
import org.example.hugmeexp.domain.attendance.dto.AttendanceStatusResponse;
import org.example.hugmeexp.domain.attendance.service.AttendanceService;
import org.example.hugmeexp.global.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Attendance", description = "출석체크 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/attendance")
@Slf4j
public class AttendanceController {



    private final AttendanceService attendanceService;

    @Operation(summary = "출석 상태 조회", description = "일주일 출석 여부/연속출석/오늘 조회")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "인증 실패"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/status")
    public ResponseEntity<ApiResponse<AttendanceStatusResponse>> getAttendanceStatus(
            @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        AttendanceStatusResponse data = attendanceService.getAttendanceStatus(username);
        if (data == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.success("Attendance status is null", null));
        }
        ApiResponse<AttendanceStatusResponse> response = ApiResponse.success("Attendance status retrieved successfully", data);
        return ResponseEntity.ok(response);
    }


    @Operation(summary = "출석 체크하기", description = "오늘 출석 체크 (경험치와 구름조각 지급)")
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "출석체크 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "이미 출석했거나 잘못된 요청"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "인증 실패"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @PostMapping("/check")
    public ResponseEntity<ApiResponse<AttendanceCheckResponse>> checkAttendance(
            @AuthenticationPrincipal UserDetails userDetails){
        String username = userDetails.getUsername();
        AttendanceCheckResponse data = attendanceService.checkAttendance(username);
        ApiResponse<AttendanceCheckResponse> response = ApiResponse.success("Attendance check success", data);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "출석 날짜 전체 조회",
            description = "로그인한 사용자가 출석한 모든 날짜를 yyyy-MM-dd 문자열 리스트로 반환"
    )
    @io.swagger.v3.oas.annotations.responses.ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "401", description = "인증 실패"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/dates")
    public ResponseEntity<ApiResponse<List<String>>> getAllDates(
            @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        List<LocalDate> dates = attendanceService.getAllAttendanceDates(username);
        List<String> dateStrings = dates.stream()
                .map(LocalDate::toString)
                .toList();
        return ResponseEntity.ok(
                ApiResponse.success("all Attendance dates retrieved success", dateStrings)
        );
    }
}

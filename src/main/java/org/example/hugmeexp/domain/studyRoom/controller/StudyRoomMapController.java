package org.example.hugmeexp.domain.studyRoom.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.hugmeexp.domain.studyRoom.dto.request.StudyHallSearchRequest;
import org.example.hugmeexp.domain.studyRoom.dto.response.ReservationTimeResponse;
import org.example.hugmeexp.domain.studyRoom.dto.response.StudyHallLocationResponse;
import org.example.hugmeexp.domain.studyRoom.dto.response.StudyRoomDetailResponse;
import org.example.hugmeexp.domain.studyRoom.dto.response.TimeSlotResponse;
import org.example.hugmeexp.domain.studyRoom.service.StudyHallService;
import org.example.hugmeexp.domain.studyRoom.service.StudyRoomService;
import org.example.hugmeexp.global.common.response.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "StudyRoom", description = "스터디룸 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/studyroom")
public class StudyRoomMapController {

    private final StudyHallService studyHallService;
    private final StudyRoomService studyRoomService;

    @Operation(summary = "모든 스터디홀 위치 조회", description = "지도에 표시할 모든 스터디홀의 위치 정보를 조회합니다.")
    @GetMapping("/map/halls")
    public ResponseEntity<ApiResponse<List<StudyHallLocationResponse>>> getAllStudyHallsForMap() {
        List<StudyHallLocationResponse> studyHalls = studyHallService.getAllStudyHallsForMap();

        return ResponseEntity.ok(ApiResponse.success("모든 스터디홀 위치 정보를 조회했습니다.", studyHalls));
    }

    @Operation(summary = "현재 위치 기반 주변 스터디홀 검색",
            description = "현재 위치를 기준으로 지정된 반경 내의 스터디홀을 거리 순으로 조회합니다.")
    @PostMapping("/map/nearby")
    public ResponseEntity<ApiResponse<List<StudyHallLocationResponse>>> searchNearbyStudyHalls(
            @Valid @RequestBody StudyHallSearchRequest request) {

        log.info("Nearby study halls search requested - lat: {}, lng: {}, radius: {}km",
                request.getLatitude(), request.getLongitude(), request.getRadius());

        List<StudyHallLocationResponse> nearbyHalls = studyHallService.searchNearbyStudyHalls(request);

        return ResponseEntity.ok(ApiResponse.success(String.format("반경 %.1fkm 내 스터디홀 %d개를 찾았습니다.",
                        request.getRadius(), nearbyHalls.size()), nearbyHalls));
    }

    @Operation(summary = "특정 스터디홀 상세 정보 조회",
            description = "특정 스터디홀의 상세 정보를 조회합니다.")
    @GetMapping("/halls/{studyHallId}")
    public ResponseEntity<ApiResponse<StudyHallLocationResponse>> getStudyHallDetail(
            @Parameter(description = "스터디홀 ID", required = true) @PathVariable Long studyHallId) {

        StudyHallLocationResponse studyHall = studyHallService.getStudyHallDetail(studyHallId);

        return ResponseEntity.ok(ApiResponse.success("스터디홀 상세 정보를 조회했습니다.", studyHall));
    }

    @Operation(summary = "현재 위치로부터 특정 스터디홀까지의 거리 계산",
            description = "현재 위치를 기준으로 특정 스터디홀까지의 거리를 계산하여 반환합니다.")
    @GetMapping("/halls/{studyHallId}/distance")
    public ResponseEntity<ApiResponse<StudyHallLocationResponse>> getStudyHallWithDistance(
            @Parameter(description = "스터디홀 ID", required = true) @PathVariable Long studyHallId,
            @Parameter(description = "현재 위치 위도", required = true) @RequestParam Double latitude,
            @Parameter(description = "현재 위치 경도", required = true) @RequestParam Double longitude) {

        StudyHallLocationResponse studyHall = studyHallService.getStudyHallWithDistance(
                studyHallId, latitude, longitude);

        return ResponseEntity.ok(ApiResponse.success("스터디홀 정보와 거리를 계산했습니다.", studyHall));
    }

    @Operation(summary = "주소로 스터디홀 검색",
            description = "주소를 기준으로 스터디홀을 검색합니다.")
    @GetMapping("/search/address")
    public ResponseEntity<ApiResponse<List<StudyHallLocationResponse>>> searchStudyHallsByAddress(
            @Parameter(description = "검색할 주소", required = true) @RequestParam String address) {

        List<StudyHallLocationResponse> studyHalls = studyHallService.searchStudyHallsByAddress(address);

        return ResponseEntity.ok(ApiResponse.success(String.format("주소 '%s'로 %d개의 스터디홀을 찾았습니다.", address, studyHalls.size()), studyHalls));
    }

    @Operation(summary = "이름으로 스터디홀 검색",
            description = "이름을 기준으로 스터디홀을 검색합니다.")
    @GetMapping("/search/name")
    public ResponseEntity<ApiResponse<List<StudyHallLocationResponse>>> searchStudyHallsByName(
            @Parameter(description = "검색할 스터디홀 이름", required = true) @RequestParam String name) {

        List<StudyHallLocationResponse> studyHalls = studyHallService.searchStudyHallsByName(name);

        return ResponseEntity.ok(ApiResponse.success(String.format("이름 '%s'로 %d개의 스터디홀을 찾았습니다.", name, studyHalls.size()), studyHalls));
    }

    @Operation(summary = "특정 스터디홀의 모든 스터디룸 조회",
            description = "특정 스터디홀에 속한 모든 스터디룸 목록을 조회합니다.")
    @GetMapping("/halls/{studyHallId}/rooms")
    public ResponseEntity<ApiResponse<List<StudyRoomDetailResponse>>> getStudyRoomsInHall(
            @Parameter(description = "스터디홀 ID", required = true) @PathVariable Long studyHallId) {

        List<StudyRoomDetailResponse> studyRooms = studyRoomService.getStudyRoomsInHall(studyHallId);

        return ResponseEntity.ok(ApiResponse.success("스터디룸 목록을 조회했습니다.", studyRooms));
    }

    @Operation(summary = "특정 스터디룸 상세 정보 조회",
            description = "특정 스터디룸의 상세 정보를 조회합니다.")
    @GetMapping("/rooms/{studyRoomId}")
    public ResponseEntity<ApiResponse<StudyRoomDetailResponse>> getStudyRoomDetail(
            @Parameter(description = "스터디룸 ID", required = true) @PathVariable Long studyRoomId) {

        StudyRoomDetailResponse studyRoom = studyRoomService.getStudyRoomDetail(studyRoomId);

        return ResponseEntity.ok(ApiResponse.success("스터디룸 상세 정보를 조회했습니다.", studyRoom));
    }

    @Operation(summary = "특정 스터디룸의 예약 가능한 시간 조회",
            description = "특정 날짜의 스터디룸 예약 가능한 시간대를 조회합니다.")
    @GetMapping("/rooms/{studyRoomId}/available-times")
    public ResponseEntity<ApiResponse<List<TimeSlotResponse>>> getAvailableTimeSlots(
            @Parameter(description = "스터디룸 ID", required = true) @PathVariable Long studyRoomId,
            @Parameter(description = "조회할 날짜 (yyyy-MM-dd)", required = true)
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {

        List<TimeSlotResponse> availableSlots = studyRoomService.getAvailableTimeSlots(studyRoomId, date);

        return ResponseEntity.ok(ApiResponse.success("예약 가능한 시간대를 조회했습니다.", availableSlots));
    }

    @Operation(summary = "특정 날짜의 스터디룸 예약 현황 조회",
            description = "특정 날짜의 스터디룸 예약 현황을 조회합니다.")
    @GetMapping("/rooms/{studyRoomId}/reservations")
    public ResponseEntity<ApiResponse<List<ReservationTimeResponse>>> getReservationsByDate(
            @Parameter(description = "스터디룸 ID", required = true) @PathVariable Long studyRoomId,
            @Parameter(description = "조회할 날짜 (yyyy-MM-dd)", required = true) @RequestParam String date) {

        List<ReservationTimeResponse> reservations = studyRoomService.getReservationsByDate(studyRoomId, date);

        return ResponseEntity.ok(ApiResponse.success("예약 현황을 조회했습니다.", reservations));
    }
}
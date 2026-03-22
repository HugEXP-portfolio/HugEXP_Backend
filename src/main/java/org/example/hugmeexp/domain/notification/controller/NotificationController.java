package org.example.hugmeexp.domain.notification.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.hugmeexp.domain.notification.dto.response.NotificationResponse;
import org.example.hugmeexp.domain.notification.service.NotificationService;
import org.example.hugmeexp.domain.user.entity.User;
import org.example.hugmeexp.global.common.response.ApiResponse;
import org.example.hugmeexp.global.security.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j    // 로깅 어노테이션
@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@Tag(name = "Notifications" , description = "알림 관련 API")
public class NotificationController {

    private final NotificationService notificationService;

    // 알림 목록 조회
    @GetMapping
    @Operation(summary = "알림 목록 조회", description = "로그인한 사용자가 받은 알림 목록을 조회합니다.")
    public ResponseEntity<ApiResponse<List<NotificationResponse>>> getMyNotifications(@AuthenticationPrincipal CustomUserDetails userDetails){

        User user = userDetails.getUser();
        List<NotificationResponse> result =  notificationService.getMyNotifications(userDetails);

        ApiResponse<List<NotificationResponse>> response = ApiResponse.success("알림 목록 조회 성공", result);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 알림 읽음 처리
    @Operation(summary = "단일 알림 읽음 처리", description = "알림 ID를 통해 특정 알림을 읽음 상태로 변경합니다.")
    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<ApiResponse<Void>> markAsRead(@PathVariable Long notificationId,
                                                     @AuthenticationPrincipal CustomUserDetails userDetails){

        notificationService.markAsRead(notificationId, userDetails);

        ApiResponse<Void> response = ApiResponse.success("알림 읽음 처리 완료", null);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 전체 알림 읽음 처리
    @Operation(summary = "모든 알림 읽음 처리", description = "로그인한 사용자의 모든 알림을 읽음 상태로 변경합니다.")
    @PatchMapping("/read-all")
    public ResponseEntity<ApiResponse<Void>> markAllAsRead(@AuthenticationPrincipal CustomUserDetails userDetails){

        notificationService.markAllAsRead(userDetails);

        ApiResponse<Void> response = ApiResponse.success("모든 알림 읽음 처리 완료", null);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}

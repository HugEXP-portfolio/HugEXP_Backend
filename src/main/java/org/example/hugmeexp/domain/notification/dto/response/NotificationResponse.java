package org.example.hugmeexp.domain.notification.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hugmeexp.domain.notification.entity.Notification;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponse {

    private Long id;
    private String type;
    private String content;
    private boolean isRead;
    private LocalDateTime createdAt;
    private String category;
    private Long targetId; // 추가: 알림이 연결된 대상 ID

    public static NotificationResponse from(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .type(notification.getType().name())
                .content(notification.getContent())
                .isRead(notification.isRead())
                .createdAt(notification.getCreatedAt())
                .category(notification.getType().getCategory().name())
                .targetId(notification.getTargetId())
                .build();
    }
}

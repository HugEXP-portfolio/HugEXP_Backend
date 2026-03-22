package org.example.hugmeexp.domain.notification.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hugmeexp.domain.notification.entity.Notification;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationDeleteRequest {

    private Long notificationId;

    public static NotificationDeleteRequest from(Notification notification) {
        return NotificationDeleteRequest.builder()
                .notificationId(notification.getId())
                .build();
    }
}

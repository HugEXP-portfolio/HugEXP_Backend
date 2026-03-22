package org.example.hugmeexp.domain.qeust.dto.response;

import lombok.*;
import org.example.hugmeexp.domain.qeust.entity.UserQuest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserQuestResponse {

    private Long userQuestId;
    private String username;
    private String questName;
    private String progress;

    public static UserQuestResponse from(UserQuest userQuest) {
        String progress = userQuest.isCompleted() ? "완료됨" : (userQuest.isCompletable() ? "완료 가능" : "진행중");
        return UserQuestResponse.builder()
                .userQuestId(userQuest.getId())
                .username(userQuest.getUser().getUsername())
                .questName(userQuest.getQuest().getName())
                .progress(progress)
                .build();
    }
}

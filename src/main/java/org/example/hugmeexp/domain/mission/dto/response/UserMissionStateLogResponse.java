package org.example.hugmeexp.domain.mission.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.example.hugmeexp.domain.mission.entity.UserMissionStateLog;
import org.example.hugmeexp.domain.mission.enums.UserMissionState;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class UserMissionStateLogResponse {
    private Long id;
    private UserMissionResponse userMission;
    private UserMissionState prevState;
    private UserMissionState nextState;
    private String createdAt;
    private String modifiedAt;

    public static UserMissionStateLogResponse from(UserMissionStateLog log) {
        return UserMissionStateLogResponse.builder()
                .id(log.getId())
                .userMission(UserMissionResponse.from(log.getUserMission()))
                .prevState(log.getPrevState())
                .nextState(log.getNextState())
                .createdAt(log.getCreatedAt() != null ? log.getCreatedAt().toString() : null)
                .modifiedAt(log.getModifiedAt() != null ? log.getModifiedAt().toString() : null)
                .build();
    }
}

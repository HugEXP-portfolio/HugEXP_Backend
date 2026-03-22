package org.example.hugmeexp.domain.mission.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hugmeexp.domain.mission.entity.UserMission;
import org.example.hugmeexp.domain.mission.enums.UserMissionState;
import org.example.hugmeexp.domain.missionGroup.dto.response.UserMissionGroupResponse;
import org.example.hugmeexp.domain.user.dto.response.UserProfileResponse;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserMissionResponse {
    private Long id;
    private UserProfileResponse user;
    private MissionResponse mission;
    private UserMissionGroupResponse userMissionGroup;
    private UserMissionState progress;

    public static UserMissionResponse from(UserMission userMission) {
        return UserMissionResponse.builder()
                .id(userMission.getId())
                .user(UserProfileResponse.from(userMission.getUser()))
                .mission(MissionResponse.from(userMission.getMission()))
                .userMissionGroup(UserMissionGroupResponse.from(userMission.getUserMissionGroup()))
                .progress(userMission.getProgress())
                .build();
    }
}

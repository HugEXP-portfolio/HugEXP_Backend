package org.example.hugmeexp.domain.missionGroup.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hugmeexp.domain.missionGroup.entity.MissionGroup;
import org.example.hugmeexp.domain.user.dto.response.UserProfileResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MissionGroupResponse {
    private Long id;
    private UserProfileResponse teacher;
    private String name;

    public static MissionGroupResponse from(MissionGroup missionGroup) {
        return MissionGroupResponse.builder()
                .id(missionGroup.getId())
                .teacher(UserProfileResponse.from(missionGroup.getTeacher()))
                .name(missionGroup.getName())
                .build();
    }
}

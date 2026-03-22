package org.example.hugmeexp.domain.mission.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hugmeexp.domain.mission.entity.Mission;
import org.example.hugmeexp.domain.mission.enums.Difficulty;
import org.example.hugmeexp.domain.missionGroup.dto.response.MissionGroupResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MissionResponse {
    private Long id;

    private MissionGroupResponse missionGroup;

    private String name;

    private String description;

    private Difficulty difficulty;

    private int rewardPoint;

    private int rewardExp;

    private int order;

    private int line;

    private String tip;

    public static MissionResponse from(Mission mission) {
        return MissionResponse.builder()
                .id(mission.getId())
                .missionGroup(MissionGroupResponse.from(mission.getMissionGroup()))
                .name(mission.getName())
                .description(mission.getDescription())
                .difficulty(mission.getDifficulty())
                .rewardPoint(mission.getRewardPoint())
                .rewardExp(mission.getRewardExp())
                .order(mission.getOrder())
                .line(mission.getLine())
                .tip(mission.getTip())
                .build();
    }
}

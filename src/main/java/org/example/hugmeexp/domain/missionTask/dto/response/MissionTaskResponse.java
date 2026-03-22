package org.example.hugmeexp.domain.missionTask.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hugmeexp.domain.missionTask.entity.MissionTask;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MissionTaskResponse {
    private Long id;
    private Long mission_id;
    private String name;
    private int score; // 공수
    private String tip;

    public static MissionTaskResponse from(MissionTask missionTask) {
        return MissionTaskResponse.builder()
                .id(missionTask.getId())
                .mission_id(missionTask.getMission().getId())
                .name(missionTask.getName())
                .score(missionTask.getScore())
                .tip(missionTask.getTip())
                .build();
    }
}

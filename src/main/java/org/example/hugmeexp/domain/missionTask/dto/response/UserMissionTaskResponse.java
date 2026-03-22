package org.example.hugmeexp.domain.missionTask.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.hugmeexp.domain.missionTask.entity.UserMissionTask;
import org.example.hugmeexp.domain.missionTask.enums.TaskState;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMissionTaskResponse {
    private Long id;

    private Long userMissionId;

    private Long missionTaskId;

    private TaskState state;

    public static UserMissionTaskResponse from(UserMissionTask userMissionTask) {
        UserMissionTaskResponse response = new UserMissionTaskResponse();
        response.id = userMissionTask.getId();
        response.userMissionId = userMissionTask.getUserMission().getId();
        response.missionTaskId = userMissionTask.getMissionTask().getId();
        response.state = userMissionTask.getState();
        return response;
    }
}

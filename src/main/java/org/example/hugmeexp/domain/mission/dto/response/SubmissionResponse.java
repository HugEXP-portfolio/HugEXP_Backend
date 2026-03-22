package org.example.hugmeexp.domain.mission.dto.response;

import lombok.*;
import org.example.hugmeexp.domain.mission.entity.Submission;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SubmissionResponse {
    private Long id;

    private UserMissionResponse userMission;

    private String fileName;

    private String originalFileName;

    private String comment;

    private String feedback;

    public static SubmissionResponse from(Submission submission) {
        return SubmissionResponse.builder()
                .id(submission.getId())
                .fileName(submission.getFileName())
                .originalFileName(submission.getOriginalFileName())
                .comment(submission.getComment())
                .feedback(submission.getFeedback())
                .build();
    }
}

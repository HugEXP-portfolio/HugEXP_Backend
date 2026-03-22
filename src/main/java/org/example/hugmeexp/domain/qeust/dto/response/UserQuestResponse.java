package org.example.hugmeexp.domain.qeust.dto.response;

import lombok.*;

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
}

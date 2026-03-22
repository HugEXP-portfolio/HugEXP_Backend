package org.example.hugmeexp.domain.qeust.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.hugmeexp.domain.qeust.entity.Quest;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestResponse {

    private Long id;
    private String name;
    private String url;
    private boolean isDeleted;

    public static QuestResponse from(Quest quest) {
        QuestResponse response = new QuestResponse();
        response.id = quest.getId();
        response.name = quest.getName();
        response.url = quest.getUrl();
        response.isDeleted = quest.isDeleted();
        return response;
    }
}
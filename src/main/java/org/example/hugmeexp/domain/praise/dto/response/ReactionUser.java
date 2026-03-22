package org.example.hugmeexp.domain.praise.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hugmeexp.domain.praise.entity.PraiseEmojiReaction;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReactionUser {

    private Long id;
    private String username;
    private String name;

    public static ReactionUser from(PraiseEmojiReaction reaction){
        return ReactionUser.builder()
                .id(reaction.getId())
                .username(reaction.getReactorWriter().getUsername())
                .name(reaction.getReactorWriter().getName())
                .build();
    }
}

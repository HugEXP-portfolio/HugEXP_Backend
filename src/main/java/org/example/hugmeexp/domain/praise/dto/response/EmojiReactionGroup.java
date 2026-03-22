package org.example.hugmeexp.domain.praise.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hugmeexp.domain.praise.entity.PraiseEmojiReaction;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmojiReactionGroup {

    private String emoji;    // 이모지
    private int count;    // 이모지 개수
    private List<ReactionUser> reactedBy;    // 한 이모지에 반응한 유저리스트

    public static EmojiReactionGroup from(String emoji, List<PraiseEmojiReaction> reactions){
        List<ReactionUser> reactedBy = reactions.stream().map(ReactionUser::from).toList();

        return EmojiReactionGroup.builder()
                .emoji(emoji)
                .count(reactions.size())
                .reactedBy(reactedBy)
                .build();
    }
}

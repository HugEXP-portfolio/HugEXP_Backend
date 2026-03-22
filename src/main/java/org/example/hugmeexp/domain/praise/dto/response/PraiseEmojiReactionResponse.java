package org.example.hugmeexp.domain.praise.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hugmeexp.domain.praise.entity.PraiseEmojiReaction;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PraiseEmojiReactionResponse {

    private Long id;    // PK
    private String emoji;    // 이모지
    private List<ReactionUser> reactedBy;    // 반응을 남긴 사람
    private Long praiseId;    // 칭찬 게시물

    public static PraiseEmojiReactionResponse from(PraiseEmojiReaction savedReaction, List<PraiseEmojiReaction> sameEmojiReactions){

        List<ReactionUser> reactedBy = sameEmojiReactions.stream()
                .map(ReactionUser::from)
                .distinct()
                .toList();
        return PraiseEmojiReactionResponse.builder()
                .id(savedReaction.getId())
                .emoji(savedReaction.getEmoji())
                .reactedBy(reactedBy)
                .praiseId(savedReaction.getPraise().getId())
                .build();
    }
}

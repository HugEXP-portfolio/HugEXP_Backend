package org.example.hugmeexp.domain.praise.mapper;

import org.example.hugmeexp.domain.praise.dto.request.CommentEmojiReactionRequest;
import org.example.hugmeexp.domain.praise.dto.response.CommentEmojiReactionResponse;
import org.example.hugmeexp.domain.praise.dto.response.ReactionUser;
import org.example.hugmeexp.domain.praise.entity.PraiseComment;
import org.example.hugmeexp.domain.praise.entity.CommentEmojiReaction;
import org.example.hugmeexp.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CommentEmojiReactionMapper {

    default CommentEmojiReaction toEntity(CommentEmojiReactionRequest commentEmojiReactionRequestDTO, PraiseComment comment, User user){
        return CommentEmojiReaction.builder()
                .comment(comment)
                .reactorWriter(user)
                .emoji(commentEmojiReactionRequestDTO.getEmoji())
                .build();
    }

    default CommentEmojiReactionResponse toDTO(CommentEmojiReaction commentEmojiReaction){
        return CommentEmojiReactionResponse.builder()
                .id(commentEmojiReaction.getId())
                .commentId(commentEmojiReaction.getComment().getId())
                .reactorName(ReactionUser.builder()
                        .id(commentEmojiReaction.getReactorWriter().getId())
                        .username(commentEmojiReaction.getReactorWriter().getUsername())
                        .name(commentEmojiReaction.getReactorWriter().getName())
                        .build())
                .emoji(commentEmojiReaction.getEmoji())
                .createdAt(commentEmojiReaction.getCreatedAt())
                .build();
    }
}


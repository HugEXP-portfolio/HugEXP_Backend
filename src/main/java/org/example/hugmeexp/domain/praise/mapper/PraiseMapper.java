package org.example.hugmeexp.domain.praise.mapper;

import org.example.hugmeexp.domain.praise.dto.response.EmojiReactionGroup;
import org.example.hugmeexp.domain.praise.dto.request.PraiseRequest;
import org.example.hugmeexp.domain.praise.dto.response.PraiseResponse;
import org.example.hugmeexp.domain.praise.entity.Praise;
import org.example.hugmeexp.domain.praise.entity.PraiseReceiver;
import org.example.hugmeexp.domain.user.dto.response.UserProfileResponse;
import org.example.hugmeexp.domain.user.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PraiseMapper {

    default Praise toEntity(PraiseRequest praiseRequestDTO, User senderId){
        return Praise.builder()
                .sender(senderId)
                .content(praiseRequestDTO.getContent())
                .praiseType(praiseRequestDTO.getType())
                .build();
    }


    default PraiseResponse toDTO(Praise praise, List<PraiseReceiver> praiseReceivers, long commentCount, List<EmojiReactionGroup> emojiGroups, List<UserProfileResponse> commentPro){

        return PraiseResponse.from(praise, praiseReceivers, commentCount, emojiGroups,commentPro);
    }
}

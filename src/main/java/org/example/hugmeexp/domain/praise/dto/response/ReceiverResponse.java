package org.example.hugmeexp.domain.praise.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hugmeexp.domain.praise.entity.PraiseReceiver;
import org.example.hugmeexp.domain.user.entity.User;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceiverResponse {

    private String name;
    private String profileImage;

    public static ReceiverResponse from(PraiseReceiver receiver){

        User user = receiver.getReceiver();

        return ReceiverResponse.builder()
                .name(user.getName())
                .profileImage(user.getPublicProfileImageUrl())
                .build();
    }
}

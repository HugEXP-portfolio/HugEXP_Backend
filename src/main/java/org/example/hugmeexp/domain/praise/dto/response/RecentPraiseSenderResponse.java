package org.example.hugmeexp.domain.praise.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hugmeexp.domain.user.dto.response.UserProfileResponse;
import org.example.hugmeexp.domain.user.entity.User;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecentPraiseSenderResponse {

    private String name;
    private List<String> senderProfiles;

    public static RecentPraiseSenderResponse from(User user, List<UserProfileResponse> receiverPro){

        List<String> senderProfiles = receiverPro.stream()
                .map(UserProfileResponse::getProfileImage).toList();

        return RecentPraiseSenderResponse.builder()
                .name(user.getName())
                .senderProfiles(senderProfiles)
                .build();

    }
}

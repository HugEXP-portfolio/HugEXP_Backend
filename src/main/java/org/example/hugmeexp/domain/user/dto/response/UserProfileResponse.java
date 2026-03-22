package org.example.hugmeexp.domain.user.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.hugmeexp.domain.user.entity.User;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileResponse {
    private String profileImage;
    private String username;
    private String name;

    public UserProfileResponse(String profileImage, String username, String name) {
        this.profileImage = profileImage;
        this.username = username;
        this.name = name;
    }

    public static UserProfileResponse from(User user) {
        return new UserProfileResponse(user.getPublicProfileImageUrl(), user.getUsername(), user.getName());
    }
}

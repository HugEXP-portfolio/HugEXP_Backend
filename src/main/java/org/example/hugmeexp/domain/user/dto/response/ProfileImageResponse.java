package org.example.hugmeexp.domain.user.dto.response;

import lombok.Getter;
import org.example.hugmeexp.domain.user.entity.User;

@Getter
public class ProfileImageResponse {
    private final String profileImage;

    public ProfileImageResponse(String profileImage) {
        this.profileImage = profileImage;
    }

    public static ProfileImageResponse from(User user) {
        return new ProfileImageResponse(user.getPublicProfileImageUrl());
    }
}

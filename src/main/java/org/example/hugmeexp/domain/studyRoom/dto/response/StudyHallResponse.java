package org.example.hugmeexp.domain.studyRoom.dto.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.hugmeexp.domain.studyRoom.entity.StudyHall;

import java.time.LocalTime;

/**
 * 스터디 홀 정보 응답을 위한 DTO 입니다.
 */
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyHallResponse {
    private final Long id;
    private final String name;
    private final String description;
    private final String simpleAddress;
    private final String address;
    private final Double latitude;
    private final Double longitude;
    private final String thumbnail;
    private final LocalTime openTime;
    private final LocalTime closeTime;

    public static StudyHallResponse from(StudyHall studyHall) {
        return StudyHallResponse.builder()
                .id(studyHall.getId())
                .name(studyHall.getName())
                .description(studyHall.getDescription())
                .simpleAddress(studyHall.getSimpleAddress())
                .address(studyHall.getAddress())
                .latitude(studyHall.getLatitude())
                .longitude(studyHall.getLongitude())
                .thumbnail(studyHall.getThumbnail())
                .openTime(studyHall.getOpenTime())
                .closeTime(studyHall.getCloseTime())
                .build();
    }
}
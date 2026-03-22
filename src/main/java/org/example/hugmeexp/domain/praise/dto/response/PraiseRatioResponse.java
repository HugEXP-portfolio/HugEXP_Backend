package org.example.hugmeexp.domain.praise.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hugmeexp.domain.praise.enums.PraiseType;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PraiseRatioResponse {

    private PraiseType type;    // 칭찬 타입
    private int percentage;    // 비율

    public static PraiseRatioResponse from(PraiseType type, int percentage){
        return PraiseRatioResponse.builder()
                .type(type)
                .percentage(percentage)
                .build();
    }
}

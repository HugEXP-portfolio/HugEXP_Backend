package org.example.hugmeexp.domain.recruitment.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.example.hugmeexp.domain.recruitment.entity.TechItem;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechItemRequest implements Comparable<TechItemRequest> {
    @NotBlank(message = "한글명은 필수입니다")
    @Size(max = 100, message = "영문명은 100자를 초과할 수 없습니다")
    private String englishName;

    @Size(max = 100, message = "한글명은 100자를 초과할 수 없습니다")
    private String koreanName;

    @Size(max = 1000, message = "아이콘 URL은 1000자를 초과할 수 없습니다")
    private String iconUrl;

    public TechItem toEntity() {
        return TechItem.builder()
                .englishName(englishName)
                .koreanName(koreanName)
                .iconUrl(iconUrl)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TechItemRequest that = (TechItemRequest) o;
        return Objects.equals(englishName, that.englishName) && Objects.equals(koreanName, that.koreanName) && Objects.equals(iconUrl, that.iconUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(englishName, koreanName, iconUrl);
    }

    @Override
    public int compareTo(TechItemRequest o) {
        if (o == null) return 1; // null is considered greater
        return this.englishName.compareTo(o.englishName);
    }
}

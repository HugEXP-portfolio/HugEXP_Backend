package org.example.hugmeexp.domain.recruitment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hugmeexp.domain.recruitment.entity.TagItem;

import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TagResponse {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TagResponse tag = (TagResponse) o;
        return Objects.equals(tagName, tag.tagName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(tagName);
    }

    private Long id;
    private String tagName;

    public static TagResponse from(TagItem tagItem) {
        return TagResponse.builder()
                .id(tagItem.getId())
                .tagName(tagItem.getTagName())
                .build();
    }
}

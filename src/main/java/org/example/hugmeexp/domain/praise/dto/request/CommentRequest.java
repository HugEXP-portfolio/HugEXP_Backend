package org.example.hugmeexp.domain.praise.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequest {

    private String content;    // 댓글 내용

}

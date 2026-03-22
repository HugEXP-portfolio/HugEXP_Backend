package org.example.hugmeexp.domain.recruitment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hugmeexp.domain.recruitment.entity.Company;
import org.example.hugmeexp.domain.recruitment.entity.Recruitment;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RecruitmentCompanySearchResponse {

    private Long recruitmentId;
    private String recruitmentSourceId;
    private String title;
    private Long companyId;
    private String companySourceId;
    private String companyName;

    public static RecruitmentCompanySearchResponse from(Recruitment recruitment) {
        Company company = recruitment.getCompany();

        return RecruitmentCompanySearchResponse.builder()
                .recruitmentId(recruitment.getId())
                .recruitmentSourceId(recruitment.getRecruitmentSourceId())
                .title(recruitment.getTitle())
                .companyId(company.getId())
                .companySourceId(company.getCompanySourceId())
                .companyName(company.getCompanyName())
                .build();
    }

}

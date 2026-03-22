package org.example.hugmeexp.domain.recruitment.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RecruitmentFilterResponse {

    private List<EducationOption> educationOptions;
    private List<Integer> experienceOptions;
    private List<TechStackResponse> techStacks;
    private List<String> workLocations;
    private List<TagResponse> tags;
    private SalaryRange salaryRange;
}

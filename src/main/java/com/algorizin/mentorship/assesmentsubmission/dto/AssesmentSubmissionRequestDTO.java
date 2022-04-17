package com.algorizin.mentorship.assesmentsubmission.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssesmentSubmissionRequestDTO {
    private String filePath;

    private String assignmentId;

    private Timestamp submissionDate;
}

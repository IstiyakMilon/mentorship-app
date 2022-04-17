package com.algorizin.mentorship.assesmentsubmission.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssesmentSubmissionListDTO {
    private String assesmentOid;
    private String assesmentTitle;
    private String description;
    private String mentorName;
    private String mentorId;
    private Timestamp deadline;
    private String studentId;
    private String studentName;
    private String submissionOid;
    private String filePath;
    private Timestamp submissionDate;

}

package com.algorizin.mentorship.assesment.dto;


import lombok.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssesmentRequestDTO {
    private String title;

    private String description;

    private String mentorId;

    private Timestamp deadline;

}

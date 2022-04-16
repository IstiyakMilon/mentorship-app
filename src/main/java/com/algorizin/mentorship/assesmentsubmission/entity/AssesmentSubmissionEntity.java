package com.algorizin.mentorship.assesmentsubmission.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "assesmentsubmission")
public class AssesmentSubmissionEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "oid", updatable = false, nullable = false)
    private String oid;

    @Column(name = "filepath")
    private String filePath;

    @Column(name = "assignmentid")
    private String assignmentId;

    @Column(name = "submissiondate")
    private Timestamp submissionDate;
}

package com.algorizin.mentorship.grade.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "grade")
public class GradeEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "oid", updatable = false, nullable = false)
    private String oid;

    @Column(name = "marks")
    private double marks;

    @Column(name = "mentorremarks")
    private String mentorRemarks;

    @Column(name = "assessmentsubmissionid")
    private String assessmentSubmissionId;

    @Column(name = "createdby")
    private String createdBy;

    @Column(name = "createdon")
    private Timestamp createdOn;

    @Column(name = "updatedby")
    private String updatedBy;

    @Column(name = "updatedon")
    private Timestamp updatedOn;
}

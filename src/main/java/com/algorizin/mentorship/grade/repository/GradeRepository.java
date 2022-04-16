package com.algorizin.mentorship.grade.repository;

import com.algorizin.mentorship.grade.entity.GradeEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<GradeEntity, String> {
    @NotFound(action = NotFoundAction.IGNORE)
    List<GradeEntity> findAll();

    @NotFound(action = NotFoundAction.IGNORE)
    GradeEntity findByOid(String oid);

    @NotFound(action = NotFoundAction.IGNORE)
    GradeEntity findByAssessmentSubmissionId(String assessmentSubmissionId);
}

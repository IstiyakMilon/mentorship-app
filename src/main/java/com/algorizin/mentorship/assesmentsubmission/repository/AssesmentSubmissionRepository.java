package com.algorizin.mentorship.assesmentsubmission.repository;

import com.algorizin.mentorship.assesmentsubmission.entity.AssesmentSubmissionEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssesmentSubmissionRepository extends JpaRepository<AssesmentSubmissionEntity, String> {
    @NotFound(action = NotFoundAction.IGNORE)
    List<AssesmentSubmissionEntity> findAll();

    @NotFound(action = NotFoundAction.IGNORE)
    AssesmentSubmissionEntity findByOid(String oid);

    @NotFound(action = NotFoundAction.IGNORE)
    AssesmentSubmissionEntity findByAssignmentId(String assignmentId);
}

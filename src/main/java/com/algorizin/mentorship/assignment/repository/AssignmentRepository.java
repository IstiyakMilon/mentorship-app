package com.algorizin.mentorship.assignment.repository;

import com.algorizin.mentorship.assignment.entity.AssignmentEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<AssignmentEntity, String> {
    @NotFound(action = NotFoundAction.IGNORE)
    List<AssignmentEntity> findAll();

    @NotFound(action = NotFoundAction.IGNORE)
    AssignmentEntity findByOid(String oid);

    @NotFound(action = NotFoundAction.IGNORE)
    AssignmentEntity findByStudentId(String studentId);

    @NotFound(action = NotFoundAction.IGNORE)
    AssignmentEntity findByAssesmentId(String assesmentId);
}

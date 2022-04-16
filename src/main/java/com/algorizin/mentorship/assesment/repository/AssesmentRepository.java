package com.algorizin.mentorship.assesment.repository;

import com.algorizin.mentorship.assesment.entity.AssesmentEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssesmentRepository extends JpaRepository<AssesmentEntity, String> {
    @NotFound(action = NotFoundAction.IGNORE)
    List<AssesmentEntity> findAll();

    @NotFound(action = NotFoundAction.IGNORE)
    AssesmentEntity findByOid(String oid);

    @NotFound(action = NotFoundAction.IGNORE)
    AssesmentEntity findByMentorId(String mentorId);
}

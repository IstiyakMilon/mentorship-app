package com.algorizin.mentorship.authorization.permission.repository;

import com.algorizin.mentorship.assesmentsubmission.entity.AssesmentSubmissionEntity;
import com.algorizin.mentorship.authorization.permission.entity.PermissionEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<PermissionEntity, String> {
    @NotFound(action = NotFoundAction.IGNORE)
    List<PermissionEntity> findAll();

    @NotFound(action = NotFoundAction.IGNORE)
    PermissionEntity findByPermissionOid(String permissionOid);

    @NotFound(action = NotFoundAction.IGNORE)
    PermissionEntity findByPermissionName(String permissionName);
}

package com.algorizin.mentorship.authorization.rolepermission.repository;

import com.algorizin.mentorship.authorization.rolepermission.entity.RolePermissionEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermissionEntity, String> {
    @NotFound(action = NotFoundAction.IGNORE)
    List<RolePermissionEntity> findAll();

    @NotFound(action = NotFoundAction.IGNORE)
    RolePermissionEntity findByOid(String oid);

}

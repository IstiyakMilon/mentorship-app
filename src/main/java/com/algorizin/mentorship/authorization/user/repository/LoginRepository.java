package com.algorizin.mentorship.authorization.user.repository;

import com.algorizin.mentorship.authorization.user.entity.LoginEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, String> {
    @NotFound(action = NotFoundAction.IGNORE)
    List<LoginEntity> findAll();

    @NotFound(action = NotFoundAction.IGNORE)
    LoginEntity findByOid(String oid);

    @NotFound(action = NotFoundAction.IGNORE)
    LoginEntity findByUserId(String userId);
}

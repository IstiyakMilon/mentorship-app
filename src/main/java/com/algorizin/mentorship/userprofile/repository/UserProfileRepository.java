package com.algorizin.mentorship.userprofile.repository;

import com.algorizin.mentorship.userprofile.entity.UserProfileEntity;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfileEntity, String> {
    @NotFound(action = NotFoundAction.IGNORE)
    List<UserProfileEntity> findAll();

    @NotFound(action = NotFoundAction.IGNORE)
    UserProfileEntity findByOid(String oid);

    @NotFound(action = NotFoundAction.IGNORE)
    UserProfileEntity findByName(String name);

    @NotFound(action = NotFoundAction.IGNORE)
    UserProfileEntity findByLoginOid(String loginOid);
}

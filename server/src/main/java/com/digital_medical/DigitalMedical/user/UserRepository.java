package com.digital_medical.DigitalMedical.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface UserRepository extends JpaRepository<UserEntity, String> {

    @Query("FROM UserEntity WHERE email=:email")
    UserEntity findByEmail(@Param("email") String email);

    @Query("FROM UserEntity users INNER JOIN RoleEntity roles ON users.role = roles.id AND roles.name = 'PATIENT'")
    Collection<UserEntity> findAllPatients();

}

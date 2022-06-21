package com.digital_medical.DigitalMedical.role;

import com.digital_medical.DigitalMedical.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    @Query("FROM RoleEntity WHERE name=:name")
    RoleEntity findByName(@Param("name") String name);

}



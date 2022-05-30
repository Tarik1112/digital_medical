package com.digital_medical.DigitalMedical.doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<DoctorEntity, String> {

    @Query("FROM DoctorEntity WHERE email=:email")
    Optional<DoctorEntity> findByEmail(@Param("email") String email);
}

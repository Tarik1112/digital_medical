package com.digital_medical.DigitalMedical.appointment;

import com.digital_medical.DigitalMedical.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppointmentRepository extends JpaRepository<AppointmentEntity, String> {
    @Query("FROM AppointmentEntity")
    UserEntity findAll(@Param("id") String email);


}

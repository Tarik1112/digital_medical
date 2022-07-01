package com.digital_medical.DigitalMedical.appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;


    public Collection<AppointmentEntity> findAll(){return appointmentRepository.findAll();}

    public AppointmentEntity saveOrUpdate(AppointmentEntity appointment) {
        return appointmentRepository.save(appointment);
    }

    public Optional<AppointmentEntity> findById(String Id){return appointmentRepository.findById(Id);}
}

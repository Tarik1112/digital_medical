package com.digital_medical.DigitalMedical.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorServices {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServices(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void addNewDoctor(DoctorEntity doctor){

        Optional<DoctorEntity> doctorByEmail = doctorRepository.findByEmail(doctor.getEmail());
        if(doctorByEmail.isPresent()){
            throw new IllegalStateException("Ovaj email je zauzet");
        }

        doctorRepository.save(doctor);
        System.out.println("OK!");
    }
}

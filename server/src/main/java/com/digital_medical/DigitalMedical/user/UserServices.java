package com.digital_medical.DigitalMedical.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServices {

    private final UserRepository doctorRepository;

    @Autowired
    public UserServices(UserRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public void addNewDoctor(UserEntity doctor){

        UserEntity doctorByEmail = doctorRepository.findByEmail(doctor.getEmail());
        if(doctorByEmail != null){
            throw new IllegalStateException("Ovaj email je zauzet");
        }

        doctorRepository.save(doctor);
        System.out.println("OK!");
    }
}

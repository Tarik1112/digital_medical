package com.digital_medical.DigitalMedical.doctor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/doctor")
public class DoctorController {

    private final DoctorServices doctorServices;

    @Autowired
    public DoctorController(DoctorServices doctorServices) {
        this.doctorServices = doctorServices;
    }

    @PostMapping("/register")
    public void doctorRegister(@RequestBody DoctorEntity doctor){
        doctorServices.addNewDoctor(doctor);
    }
}

package com.digital_medical.DigitalMedical.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/doctor")
public class UserController {

    private final UserServices doctorServices;

    @Autowired
    public UserController(UserServices doctorServices) {
        this.doctorServices = doctorServices;
    }

    @PostMapping("/register")
    public void doctorRegister(@RequestBody UserEntity doctor){
        doctorServices.addNewDoctor(doctor);
    }
}

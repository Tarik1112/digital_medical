package com.digital_medical.DigitalMedical.user;

import com.digital_medical.DigitalMedical.role.RoleConstant;
import com.digital_medical.DigitalMedical.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServices {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private RoleService roleService;


    @Autowired
    public UserServices(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addNewDoctor(UserEntity user){

        UserEntity doctorByEmail = userRepository.findByEmail(user.getEmail());
        if(doctorByEmail != null){
            throw new IllegalStateException("Ovaj email je zauzet");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleService.findByName(RoleConstant.DOCTOR.toString()));
        userRepository.save(user);
        System.out.println("OK!");
    }

    public void addNewPatient(UserEntity user){

        UserEntity doctorByEmail = userRepository.findByEmail(user.getEmail());
        if(doctorByEmail != null){
            throw new IllegalStateException("Ovaj email je zauzet");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleService.findByName(RoleConstant.PATIENT.toString()));
        userRepository.save(user);
        System.out.println("OK!");
    }

    public UserEntity findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Collection<UserEntity> findAllPatients() {return userRepository.findAllPatients();}

    public Collection<UserEntity> findAll() {return userRepository.findAll();}

    public Optional<UserEntity> findById(String Id) {return userRepository.findById(Id);}
}

package com.digital_medical.DigitalMedical.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class RoleService{
    @Autowired
    private RoleRepository roleRepository;

    public Collection<RoleEntity> findAll() { return roleRepository.findAll(); }

    public RoleEntity saveOrUpdate(RoleEntity role) {
        return roleRepository.save(role);
    }


    public RoleEntity findByName(String name) {
        return roleRepository.findByName(name);
    }

}

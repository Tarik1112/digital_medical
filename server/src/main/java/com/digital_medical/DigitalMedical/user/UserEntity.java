package com.digital_medical.DigitalMedical.user;


import com.digital_medical.DigitalMedical.appointment.AppointmentEntity;
import com.digital_medical.DigitalMedical.role.RoleEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="users")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private String birthDate;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String department;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonIgnoreProperties("users")
    private RoleEntity role;

    @OneToMany(targetEntity = AppointmentEntity.class, mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<AppointmentEntity> appointments;


    public UserEntity() {
    }

    public UserEntity(String id, String password, String name, String surname, String birthDate, String gender, String email, String department) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.email = email;
        this.department = department;
    }

    public UserEntity(String password, String name, String surname, String birthDate, String gender, String email, String department) {
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.email = email;
        this.department = department;
    }
}

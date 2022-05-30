package com.digital_medical.DigitalMedical.doctor;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name="doctors")
@Data
public class DoctorEntity {
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


    public DoctorEntity() {
    }

    public DoctorEntity(String id, String password, String name, String surname, String birthDate, String gender, String email, String department) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.email = email;
        this.department = department;
    }

    public DoctorEntity(String password, String name, String surname, String birthDate, String gender, String email, String department) {
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.gender = gender;
        this.email = email;
        this.department = department;
    }
}

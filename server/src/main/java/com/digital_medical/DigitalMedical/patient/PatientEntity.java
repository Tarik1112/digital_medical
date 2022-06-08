package com.digital_medical.DigitalMedical.patient;


import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="patients")
@Data
public class PatientEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String Id;

    private String name;

    private String surname;

    private String birthDate;

    private String gender;

    private String medicalRecords;

    private String doctor;

    private String address;
}

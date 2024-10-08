package com.example.be.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private int employeeId;

    @Column(name = "employee_iden",columnDefinition = "TEXT")
    private String employeeIden;

    @Column(name = "image",columnDefinition = "TEXT")
    private String image;

    @Column(name = "birth_day",columnDefinition = "DATE")
    private Date birthday;

    private boolean gender;

    @Column(name = "fullname",columnDefinition = "VARCHAR(28)")
    private String fullname;

    @Column(name = "id_card",columnDefinition = "VARCHAR(28)")
    private String idCard;

    @Column(name = "email",columnDefinition = "VARCHAR(250)")
    private String email;

    @Column(name = "phone",columnDefinition = "VARCHAR(28)")
    private String phone;

    private String address;
}

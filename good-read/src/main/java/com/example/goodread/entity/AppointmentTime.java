package com.example.goodread.entity;



import com.example.goodread.statics.AppointmentTimes;

import javax.persistence.*;

public class AppointmentTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    AppointmentTimes appointmentTimes;
}

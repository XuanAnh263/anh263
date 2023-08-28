package com.example.goodread.statics;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppointmentTimes {
    BAY("7h"),
    BAY30("7h30"),
    TAM("8h"),
    TAM30("8h30"),
    CHIN("9h");

    String name;

}

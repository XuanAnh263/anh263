package com.example.transportation_management_js.statics;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)

public enum DriverLevel {
    HANG_A(" Hạng A"),
    HANG_B(" Hạng B"),
    HANG_C(" Hạng C"),
    HANG_D(" Hạng D"),
    HANG_E(" Hạng E"),
    HANG_F(" Hạng F");

    String name;
}

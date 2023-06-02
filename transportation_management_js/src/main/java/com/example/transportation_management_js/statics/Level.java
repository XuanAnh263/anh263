package com.example.transportation_management_js.statics;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PUBLIC)

public enum Level {
    A,
    B,
    C,
    D,
    E,
    F;

}

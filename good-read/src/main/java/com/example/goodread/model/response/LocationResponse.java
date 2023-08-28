package com.example.goodread.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocationResponse {

    String provinceCode;
    String provinceName;

    String districtCode;
    String districtName;

    String wardCode;
    String wardName;

    String street;
}

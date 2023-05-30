package com.example.transportation_management_js.model.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RouteCreateRequest {

    @NotNull(message = "Distance cannot be blank")
    @Range(min = 1, message = "Distance must be greater than 0")
    float distance;

    @NotNull(message = "Stop Point cannot be blank")
    @Range(min = 1, message = "Stop Point must be greater than 0")
    int stopPoint;
}

package com.example.demolearnjpa.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class WorkerDto {
    private Integer id;
    private String name;
    private String email;
}

//C1: Query entity -> Mapper --> Dto
//mapper co the tu viet, su dung thu vien: Object mapper, mapstruct

//C2: JPQL

//C3: Native Query

//C4: su dung Projection (interface) -- tu tim hieu


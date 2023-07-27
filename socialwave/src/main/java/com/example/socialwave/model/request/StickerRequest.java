package com.example.socialwave.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@Builder
@ToString

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StickerRequest {

    String name;
    String imageUrl;
    LocalDate createdAt;
}

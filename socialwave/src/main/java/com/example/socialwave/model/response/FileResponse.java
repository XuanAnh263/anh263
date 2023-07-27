package com.example.socialwave.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class FileResponse {
    Long id;

    String filePath;

    String fileName;

    String fileExtension;

    Float fileSize;
}

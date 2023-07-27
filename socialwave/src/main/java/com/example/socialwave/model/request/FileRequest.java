package com.example.socialwave.model.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@ToString

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FileRequest {

    @NotBlank(message = "File path is required")
            @Length(max = 255, message = "File path must not exceed 255 characters")
    String filePath;

    @NotBlank(message = "File name is required")
            @Length(max = 100, message = "File name must not exceed 255 characters")
    String fileName;

    @NotBlank(message = "File name is required")
    @Length(max = 100, message = "File name must not exceed 255 characters")
    String fileExtension;

    @NotNull(message = "File size is required")
            @DecimalMin(value = "0.0",inclusive = false, message = "File size must be greater than 0")
    Float fileSize;

}

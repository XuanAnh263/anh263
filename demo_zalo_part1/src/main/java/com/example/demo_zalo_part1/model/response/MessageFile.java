package com.example.demo_zalo_part1.model.response;

import java.time.LocalDate;

public class MessageFile {

    MessageResponse message;
    String filePath;
    String fileName;
    Float size;
    String fileExtension;
    UserResponse creator;
    LocalDate createdAt;
    LocalDate updatedAt;
}

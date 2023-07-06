package com.example.demo_zalo_part1.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Builder
@ToString
@Entity
@Table(name = "files")
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "path")
    String filePath;

    @Column(name = "name")
    String fileName;

    @Column(name = "extension")
    String fileExtension;

    @Column(name = "size")
    Float fileSize;

}

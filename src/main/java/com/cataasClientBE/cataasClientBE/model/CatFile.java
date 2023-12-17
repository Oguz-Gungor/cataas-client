package com.cataasClientBE.cataasClientBE.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class CatFile {

    @Id
    @GeneratedValue
    private Long id;

    private String fileName;

    private String filePath;

    private Long fileSize;

}

package com.cataasClientBE.cataasClientBE.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@NoArgsConstructor
@Data
@AllArgsConstructor
@SuperBuilder
public class Subfolder {

    private Map<String, Subfolder> subfolders;

    private long fileCount;

    private String fileName;

    private boolean file;

}

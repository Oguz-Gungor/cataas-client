package com.cataasClientBE.cataasClientBE.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@NoArgsConstructor
@Data
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
public class FileReport extends Subfolder {
    private Date date;
}

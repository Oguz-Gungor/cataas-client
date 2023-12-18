package com.cataasClientBE.cataasClientBE.controllers;

import com.cataasClientBE.cataasClientBE.model.dto.FileReport;
import com.cataasClientBE.cataasClientBE.model.entity.CatFile;
import com.cataasClientBE.cataasClientBE.repositories.CatImageRepository;
import com.cataasClientBE.cataasClientBE.services.util.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileService fileService;

    @GetMapping(value = "/reportJson")
    FileReport getFileReportJson() {
        return fileService.getFileReportJson();
    }

    @GetMapping(value = "/report")
    String getFileReport() {
        return fileService.getFileReport();
    }
}

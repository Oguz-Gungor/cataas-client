package com.cataasClientBE.cataasClientBE.controllers;

import com.cataasClientBE.cataasClientBE.model.CatFile;
import com.cataasClientBE.cataasClientBE.repositories.CatImageRepository;
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
    CatImageRepository fileRepository;

    @GetMapping(value = "/all")
    List<CatFile> getAll() throws URISyntaxException, IOException {

        return Streamable.of(fileRepository.findAll()).toList();
    }
}

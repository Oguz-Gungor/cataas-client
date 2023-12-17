package com.cataasClientBE.cataasClientBE.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthcheckController {

    @GetMapping("/alive")
    String alive(){
        return "server is up";
    }
}

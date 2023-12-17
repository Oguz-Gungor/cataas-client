package com.cataasClientBE.cataasClientBE.controllers;


import com.cataasClientBE.cataasClientBE.services.bussiness.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cat")
public class CatController {

    @Autowired
    CatService catService;

    @GetMapping(
            path = "/filters",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] get(@RequestParam Map<String, Optional<?>> allRequestParams, @RequestParam String fileName) throws URISyntaxException, IOException {
        return catService.getRandomCat(fileName, allRequestParams);
    }

    @GetMapping(
            path = "/{tag}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getWithTag(@PathVariable String tag, @RequestParam String fileName) throws URISyntaxException, IOException {
        return catService.getCatWithTag(tag, fileName);
    }

    @GetMapping(
            path = "/{text}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getWithText(@PathVariable String text, @RequestParam String fileName) throws URISyntaxException, IOException {
        return catService.getCatWithText(text, fileName);
    }

    @GetMapping(
            path = "/{tag}/says/{text}",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getWithTagAndText(@PathVariable String tag, @PathVariable String text, @RequestParam String fileName) throws URISyntaxException, IOException {
        return catService.getCatWithTagAndText(tag, text, fileName);
    }

    @GetMapping(
            produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getWithWidthAndHeight(@RequestParam(required = false) Optional<Integer> width, @RequestParam(required = false) Optional<Integer> height, @RequestParam String fileName) throws URISyntaxException, IOException {
        return catService.getCatWithWidthAndHeight(width, height, fileName);
    }

}

package com.cataasClientBE.cataasClientBE.services.bussiness;

import com.cataasClientBE.cataasClientBE.contractors.CataasAPIContractor;
import com.cataasClientBE.cataasClientBE.exceptions.CatNotFoundException;
import com.cataasClientBE.cataasClientBE.model.CatFile;
import com.cataasClientBE.cataasClientBE.repositories.CatImageRepository;
import com.cataasClientBE.cataasClientBE.services.util.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Optional;

@Service
public class CatService {
    @Autowired
    CataasAPIContractor cataasAPI;
    @Autowired
    FileService fileService;
    @Autowired
    CatImageRepository repository;

    @Value("${filteredDirectory:1}")
    private String filtered;
    @Value("${taggedDirectory:2}")
    private String tagDirectory;
    @Value("${textDirectory:3}")
    private String textDirectory;
    @Value("${widthHeightDirectory:4}")
    private String widthAndHeightDirectory;
    @Value("${taggedTextDirectory:5}")
    private String tagAndTextDirectory;

    public byte[] getRandomCat(String fileName, Map<String, Optional<?>> params) throws URISyntaxException, IOException, CatNotFoundException {
        byte[] rawBytes = cataasAPI.getRandomCat(params);
        saveCat(rawBytes, filtered, fileName);
        return rawBytes;
    }

    public byte[] getCatWithTag(String tag, String fileName) throws URISyntaxException, IOException, CatNotFoundException {
        byte[] rawBytes = cataasAPI.getCatWithTag(tag);
        saveCat(rawBytes, tagDirectory, fileName);
        return rawBytes;
    }

    public byte[] getCatWithText(String text, String fileName) throws URISyntaxException, IOException, CatNotFoundException {
        byte[] rawBytes = cataasAPI.getCatWithText(text);
        saveCat(rawBytes, textDirectory, fileName);
        return rawBytes;
    }

    public byte[] getCatWithWidthAndHeight(Optional<Integer> width, Optional<Integer> height, String fileName) throws URISyntaxException, IOException, CatNotFoundException {
        byte[] rawBytes = cataasAPI.getCatWithWidthAndHeight(width, height);
        saveCat(rawBytes, widthAndHeightDirectory, fileName);
        return rawBytes;
    }

    public byte[] getCatWithTagAndText(String tag, String text, String fileName) throws URISyntaxException, IOException, CatNotFoundException {
        byte[] rawBytes = cataasAPI.getCatWithTagAndText(tag, text);
        saveCat(rawBytes, tagAndTextDirectory, fileName);
        return rawBytes;
    }

    private void saveCat(byte[] rawBytes, String directoryName, String fileName) throws IOException {
        File targetFile = fileService.writeFile(rawBytes, directoryName, fileName);
        repository.save(generateCatFileEntity(rawBytes, targetFile, fileName));
    }

    private CatFile generateCatFileEntity(byte[] rawBytes, File targetFile, String fileName) {
        return CatFile.builder().filePath(targetFile.getAbsolutePath()).fileSize((long) rawBytes.length).fileName(fileName).build();
    }


}

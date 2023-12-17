package com.cataasClientBE.cataasClientBE.services.util;

import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import java.io.*;

@Service
public class FileService {

    static final String BASE_DIR = "collection/";
    static final String JPG_FILE_EXTENSION = ".jpg";

    public File writeFile(byte[] buffer, String pathString, String fileName) throws IOException {
        String fileNameBuffer = File.separator +
                fileName +
                JPG_FILE_EXTENSION;
        StringBuilder pathStringBuffer = new StringBuilder();
        pathStringBuffer.append(BASE_DIR);
        pathStringBuffer.append(pathString);
        File targetFile = new File(pathStringBuffer.toString());
        boolean dirsCreated = targetFile.mkdirs();
        targetFile = new File(pathStringBuffer.toString() + fileNameBuffer);
        try (OutputStream outStream = new FileOutputStream(targetFile)) {
            outStream.write(buffer);
        }

        return targetFile;
    }

    public void prune() {
        try {
            File targetFile = new File(BASE_DIR);
            FileSystemUtils.deleteRecursively(targetFile);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }
}

package com.cataasClientBE.cataasClientBE.services.util;

import com.cataasClientBE.cataasClientBE.model.dto.FileReport;
import com.cataasClientBE.cataasClientBE.model.dto.Subfolder;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileService {

    static final String BASE_DIR = "collection/";
    static final String JPG_FILE_EXTENSION = ".jpg";

    public File writeFile(byte[] buffer, String pathString, String fileName) throws IOException {
        String fileNameBuffer = File.separator + fileName + JPG_FILE_EXTENSION;
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

    public String getFileReport() {
        return formattedFileReport(getFileReportJson());
    }


    private String formattedFileReport(FileReport fileReport) {


        StringBuilder formattedReport = new StringBuilder();
        formattedReport.append("Total assets: ");
        formattedReport.append(fileReport.getFileCount());
        formattedReport.append("\n");
        formattedReport.append("Date of report: ");
        formattedReport.append(fileReport.getDate());
        formattedReport.append("\n\n");
        if (fileReport.getSubfolders() == null || fileReport.getSubfolders().isEmpty()) {
            formattedReport.append("There is no subfolder detail to show");
            return formattedReport.toString();
        }
        formattedReport.append("Subfolder details");
        fileReport.getSubfolders().forEach((s, subfolder) -> {
            nestedFormat(formattedReport, subfolder);
        });

        return formattedReport.toString();
    }

    private StringBuilder nestedFormat(StringBuilder formattedReport, Subfolder subfolder) {
        if (subfolder.isFile()) {
            formattedReport.append("- ");
            formattedReport.append(subfolder.getFileName());
        } else {
            if (subfolder.getSubfolders() == null)
                return formattedReport;
            formattedReport.append(subfolder.getFileName());
            formattedReport.append(" - ");
            formattedReport.append(subfolder.getFileCount());
            formattedReport.append(" files");
            formattedReport.append("\n");
            subfolder.getSubfolders().forEach((s, sub) ->
                    nestedFormat(formattedReport, sub)
            );
        }
        formattedReport.append("\n");
        return formattedReport;
    }

    public FileReport getFileReportJson() {
        AtomicLong fileCount = new AtomicLong(0);
        AtomicLong directoryFileCount = new AtomicLong(0);
        Subfolder o = readFile(new File(BASE_DIR), fileCount, directoryFileCount);
        return FileReport.builder().fileCount(fileCount.get()).subfolders(o.getSubfolders()).date(new Date()).build();
    }

    private Subfolder readFile(File rootFile, AtomicLong totalFileCount, AtomicLong directoryFileCount) {
        if (rootFile.isDirectory()) {
            AtomicLong currentDirectoryFileCount = new AtomicLong(0);
            Map<String, Subfolder> collect = Stream.of(Objects.requireNonNull(rootFile.listFiles()))
                    .map(file ->
                            new Pair<>(
                                    file.getName(),
                                    readFile(file, totalFileCount, currentDirectoryFileCount))
                    )
                    .collect(Collectors.toMap(pair -> pair.a, pair -> pair.b));
            return Subfolder.builder().fileName(rootFile.getName()).subfolders(collect).fileCount(currentDirectoryFileCount.get()).build();
        }
        if (rootFile.isFile()) {
            directoryFileCount.accumulateAndGet(1, Long::sum);
            totalFileCount.accumulateAndGet(1, Long::sum);
            return Subfolder.builder().fileName(rootFile.getName()).file(true).build();
        }
        return Subfolder.builder().build();
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

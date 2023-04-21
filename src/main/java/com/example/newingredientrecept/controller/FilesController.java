package com.example.newingredientrecept.controller;

import com.example.newingredientrecept.model.Recept;
import com.example.newingredientrecept.service.FileServiceRecept;
import com.example.newingredientrecept.service.FilesService;
import com.example.newingredientrecept.service.ReceptService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.classgraph.Resource;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FilesController {

    @Value("${path.to.data1.file}")
    private String dataFilePath2;
    @Value("${name.of.data1.file}")
    private String dataFileName2;

    @Value("${path.to.data.file}")
    private String dataFilePath;

    @Value("${name.of.data.file}")
    private String dataFileName;

    @Value("${path.to.data2.file}")
    private String dataFilePath3;

    @Value("${name.of.data2.file}")
    private String dataFileName3;

    private final FilesService filesService;
    private final FileServiceRecept fileServiceRecept;

    private final ReceptService receptService;


    public FilesController(FilesService filesService, FileServiceRecept fileServiceRecept, ReceptService receptService) {
        this.filesService = filesService;
        this.fileServiceRecept = fileServiceRecept;
        this.receptService = receptService;
    }

    @PostMapping(value = "/uploadIngedient", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFileIngredient(@RequestParam MultipartFile file) throws IOException {
        filesService.uploadFile(file);
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/uploadRecept", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFileRecept(@RequestParam MultipartFile file) throws IOException {
        fileServiceRecept.uploadFile(file);
        return ResponseEntity.ok().build();
    }


    @GetMapping(value = "/files/downloadRecept")
    public void downloadFile(HttpServletResponse response)
            throws IOException {
        Path path = Path.of(dataFilePath2, dataFileName2);

        try (
                InputStream is = Files.newInputStream(path);
                OutputStream os = response.getOutputStream()
        ) {
            response.setStatus(200);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setContentLength((int) Files.size(path));
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= recept");
            is.transferTo(os);
        }
    }

    @GetMapping(value = "/files/downloadIngredient")
    public void downloadFile2(HttpServletResponse response)
            throws IOException {
        Path path = Path.of(dataFilePath, dataFileName);

        try (
                InputStream is = Files.newInputStream(path);
                OutputStream os = response.getOutputStream()
        ) {
            response.setStatus(200);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setContentLength((int) Files.size(path));
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= ingredient");
            is.transferTo(os);
        }
    }

    @GetMapping(value = "/files/downloadReceptTXT")
    public void downloadFile3(HttpServletResponse response)
            throws IOException {
        Path path = Path.of(dataFilePath3, dataFileName3);

        try (
                InputStream is = Files.newInputStream(path);
                OutputStream os = response.getOutputStream()
        ) {
            response.setStatus(200);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setContentLength((int) Files.size(path));
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename= recept");
            is.transferTo(os);
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Object> downloadJson() throws IOException {
        List<Recept> data = receptService.getAllRecept();
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        String json = mapper.writeValueAsString(data);
        File file = File.createTempFile("data", ".json");
        try {

            InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write(json);
            bw.close();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=data.json")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(isr);
        } catch (IOException e) {
            e.printStackTrace();
             return  ResponseEntity.notFound().build();

        }
    }

    }




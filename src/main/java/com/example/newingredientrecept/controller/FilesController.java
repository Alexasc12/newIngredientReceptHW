package com.example.newingredientrecept.controller;

import com.example.newingredientrecept.service.FileServiceRecept;
import com.example.newingredientrecept.service.FilesService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/files")
public class FilesController {

    @Value( "${path.to.data1.file}")
    private String dataFilePath2;

    private final FilesService filesService;
    private final FileServiceRecept fileServiceRecept;


    public FilesController(FilesService filesService, FileServiceRecept fileServiceRecept) {
        this.filesService = filesService;
        this.fileServiceRecept = fileServiceRecept;
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


    @GetMapping(value = "/files/{name}/download")
    public void downloadFile(@PathVariable String name, HttpServletResponse response)
            throws IOException {
        Path path = Path.of(dataFilePath2 + "/" + name);

        try (
                InputStream is = Files.newInputStream(path);
                OutputStream os = response.getOutputStream();
        ) {
            response.setStatus(200);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setContentLength((int)Files.size(path));
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + name);
            is.transferTo(os);
        }
    }



    }





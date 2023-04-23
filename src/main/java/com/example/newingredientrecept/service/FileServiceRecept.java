

package com.example.newingredientrecept.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class FileServiceRecept {

    @Value("${path.to.data1.file}")
    private String dataFilePath2;

    @Value("${name.of.data1.file}")
    private String dataFileName2;




    public boolean saveToFileRecept(String json){
        try {
            cleanDataFileRecept();
            Files.writeString(Path.of(dataFilePath2,dataFileName2),json);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String readFromFileRecept(){
        try {
            return   Files.readString(Path.of(dataFilePath2, dataFileName2));

        } catch (IOException e) {
            e.printStackTrace();
            return "{}";
        }

    }

    private boolean cleanDataFileRecept() {
        try {
            Path path = Path.of(dataFilePath2, dataFileName2);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void uploadFile(MultipartFile file) throws IOException {
        Path filePath  = Path.of(dataFilePath2,file.getOriginalFilename());
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = file.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        )
        {
            bis.transferTo(bos);
        }
    }



}
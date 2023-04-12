

package com.example.newingredientrecept.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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



}
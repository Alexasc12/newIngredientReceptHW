package com.example.newingredientrecept.service;

import com.example.newingredientrecept.dto.ReceptDTO;
import com.example.newingredientrecept.model.Ingredient;
import com.example.newingredientrecept.model.Recept;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReceptService {

    final private FileServiceRecept fileServiceRecept ;


    private int idCounter = 0;

    Map<Integer,Recept> mapRecept = new HashMap<>();

    public ReceptService(FileServiceRecept fileServiceRecept) {
        this.fileServiceRecept = fileServiceRecept;
    }

    @PostConstruct
    private void init() {
        readFrommFileRecept();
    }


    public ReceptDTO postRecept( Recept recept) {
        int id = idCounter++;
        mapRecept.put(id,recept);
        saveToFileRecept();

        return ReceptDTO.from(id,recept);
    }

    public Recept  getRecept(int id) {
        Recept recept = mapRecept.get(id);
        if (recept != null) {
            return  recept;
        }
        return null;
    }

    public String getStringRecept(int namber) {
      Recept recept =  mapRecept.get(namber);
      String ingredients = recept.getListIngridient().stream()
              .map(Ingredient::getNameIngredients)
              .collect(Collectors.joining(","));
      String steps = String.join("\n", recept.getListSteps());
      StringBuilder builder = new StringBuilder();
      builder.append("Название вблюда: ").append(recept.getNameDish()).append("\n")
              .append("Время приготовления:").append(recept.getCookingTime()).append(" минут\n")
              .append("Ингредиенты: ").append(ingredients).append("\n")
              .append("Инструкция приготовления: \n").append(steps).append("\n");
      return builder.toString();


    }

    public List<Recept> getAllRecept() {
        return   mapRecept.values().stream().collect(Collectors.toList());

    }

    public Recept editRecept(int id, Recept newRecept) {
        Recept recept = mapRecept.get(id);
        if (recept != null) {
            recept.setNameDish(newRecept.getNameDish());
            recept.setListSteps(newRecept.getListSteps());
            saveToFileRecept();
            return newRecept;
        }

        return null;
    }

    public boolean deleteRecept(int id) {
        if (mapRecept.containsKey(id)) {
            mapRecept.remove(id );
            saveToFileRecept();
            return true;
        }
        return false;
    }

    public void deleteAllRecept() {
        mapRecept = new HashMap<>();
    }

    private void saveToFileRecept(){
        try {
            String json = new ObjectMapper().writeValueAsString(mapRecept);
            fileServiceRecept.saveToFileRecept(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }

    private void readFrommFileRecept(){
        String json = fileServiceRecept.readFromFileRecept();
        try {
            mapRecept = new ObjectMapper().readValue(json, new TypeReference< Map < Integer, Recept>>(){
            } );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


}
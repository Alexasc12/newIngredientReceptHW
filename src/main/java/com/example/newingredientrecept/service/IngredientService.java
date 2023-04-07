package com.example.newingredientrecept.service;

import com.example.newingredientrecept.model.Ingredient;

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
public class IngredientService {

    final private FilesService filesService;



    private int idCounter = 0;

    Map<Integer,Ingredient > mapIngridient =new  HashMap<>();

    public IngredientService(FilesService filesService) {
        this.filesService = filesService;
    }

    @PostConstruct
     private void init() {
        readFrommFile();
    }


    public Ingredient postIngredient(Ingredient ingredient) {
        int id = idCounter++;
        Ingredient ingredient1 =   mapIngridient.put(id,ingredient);
        saveToFile();
        return ingredient1 ;
    }

    public Ingredient getIngredient(int id) {
        Ingredient ingredient = mapIngridient.get(id);
        if (ingredient != null) {
                       return ingredient;
        }
        return null;
    }
//    public  Ingredient getAllIngredient() {
//        if (mapIngridient != null) {
//            for (Ingredient ingredient : mapIngridient.values()) {
//                return ingredient;
//            }
//        } return null;
        public List<Ingredient> getAllIngredient() {
          return   mapIngridient.values().stream().collect(Collectors.toList());

        }




    public Ingredient editIngredient(int id, Ingredient newIngredient) {
        Ingredient ingredient = mapIngridient.get(id);
        if (ingredient != null) {
            ingredient.setNameIngredients(newIngredient.getNameIngredients());
            ingredient.setMeasureUnit(newIngredient.getMeasureUnit());
            ingredient.setWeight(newIngredient.getWeight());
            saveToFile();
            return newIngredient;

//            if (mapIngridient.containsKey(id)) {
//                mapIngridient.put(id,newIngredient);
//                return newIngredient;
//            }
        }
        return null;
    }

    public boolean deleteIngredient(int id) {
        if (mapIngridient.containsKey(id)) {
                mapIngridient.remove(id );
                saveToFile();
                return true;
            }
        return false;
    }

    public void deleteAllIngredient() {
        mapIngridient = new HashMap<>();
    }

    private void saveToFile(){
        try {
        String json = new ObjectMapper().writeValueAsString(mapIngridient);
        filesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }

    private void readFrommFile(){
        String json = filesService.readFromFile();
        try {
           mapIngridient = new ObjectMapper().readValue(json, new TypeReference < Map < Integer, Ingredient >>(){
            } );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
    }




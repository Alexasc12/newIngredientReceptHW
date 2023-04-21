package com.example.newingredientrecept.service;

import com.example.newingredientrecept.dto.ReceptDTO;
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
      String receptStr = recept.getNameDish() +"\n" +
              recept.getCookingTime() + ": 30 минут.\n" +
              recept.getListIngridient() + " :\n" +
              "-Творог — 350 г.\n" +
              "-Куриное яйцо — 2 шт.\n" +
              "-Пшеничная мука — 6 ст.л.\n" +
              "-Сахар — 2 ст.л.\n" +
              recept.getListSteps() +":\n" +
              "-Смешайте весь творог с яйцами, сахаром и тщательно всё перемешайте.\n" +
              " Всыпьте в творог муку и тщательно перемешайте.\n" +
              "-Поставьте сковороду на средний огонь и налейте в нее подсолнечное масло.\n" +
              "-Слепите несколько небольших шариков из получившейся творожной массы и положите их на тарелку. Затем по очереди обкатывайте творожные шарики в муке и выкладывайте на сковороду.\n" +
              "-Обжаривайте сырники 1–2 минуты до появления золотистой корочки. Затем переверните их на другую сторону и также обжарьте до золотистого состояния.\n" +
              "-Повторяйте, пока творог не закончится.";
      return  receptStr;


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
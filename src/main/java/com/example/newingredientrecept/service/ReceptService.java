package com.example.newingredientrecept.service;

import com.example.newingredientrecept.dto.ReceptDTO;
import com.example.newingredientrecept.model.Ingredient;
import com.example.newingredientrecept.model.Recept;


import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReceptService {
//    1. Добавления нового рецепта.
//    В метод передается заполненный объект класса рецепта,
//    который сохраняется в карте и получает свой порядковый номер.
//     2. Получение рецепта.
//    В метод передается порядковый номер рецепта, на выходе мы получаем из карты нужный объект.

    private int idCounter = 0;

    Map<Integer,Recept> mapRecept = new HashMap<>();


    public ReceptDTO postRecept( Recept recept) {
        int id = idCounter++;
        mapRecept.put(id,recept);

        return ReceptDTO.from(id,recept);
    }

    public Recept  getRecept(int id) {
        Recept recept = mapRecept.get(id);
        if (recept != null) {
            return  recept;
        }
        return null;
    }

    public List<Recept> getAllRecept() {
        return   mapRecept.values().stream().collect(Collectors.toList());

    }

    public Recept editRecept(int id, Recept newRecept) {
        Recept recept = mapRecept.get(id);
        if (recept != null) {
            recept.setNameDish(newRecept.getNameDish());
            recept.setListSteps(newRecept.getListSteps());
            return newRecept;
        }

        return null;
    }

    public boolean deleteRecept(int id) {
        if (mapRecept.containsKey(id)) {
            mapRecept.remove(id );
            return true;
        }
        return false;
    }

    public void deleteAllRecept() {
        mapRecept = new HashMap<>();
    }

}
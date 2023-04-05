package com.example.newingredientrecept.service;

import com.example.newingredientrecept.model.Ingredient;
import com.example.newingredientrecept.model.Recept;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class IngredientService {
//    Храниться ингредиенты должны в карте в формате <идентификатор, ингредиент>;
//
//    В сервисе должны быть методы для добавления нового ингредиента и получения его по идентификатору.
//    Можно делать по аналогии с сервисом рецептов.


    private int idCounter = 0;



    Map<Integer,Ingredient > mapIngridient =new  HashMap<>();


    public Ingredient postIngredient(Ingredient ingredient) {
        int id = idCounter++;
        Ingredient ingredient1 =   mapIngridient.put(id,ingredient);
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
                return true;
            }
        return false;
    }

    public void deleteAllIngredient() {
        mapIngridient = new HashMap<>();
    }


    }




package com.example.newingredientrecept.service;

import com.example.newingredientrecept.model.Ingredient;
import com.example.newingredientrecept.model.Recept;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
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
        return  ingredient;
    }


}

package com.example.newingredientrecept.dto;


import com.example.newingredientrecept.model.Ingredient;
import com.example.newingredientrecept.model.Recept;

import java.util.List;

public class ReceptDTO {
    private final int id ;
    private final String nameDish ;
    private final  int cookingTime;

    private final List<Ingredient> listIngridient ;
    private final List<String> listSteps;

    public ReceptDTO(int id, String nameDish, int cookingTime, List<Ingredient> listIngridient, List<String> listSteps) {
        this.id = id;
        this.nameDish = nameDish;
        this.cookingTime = cookingTime;
        this.listIngridient = listIngridient;
        this.listSteps = listSteps;
    }

    public int getId() {
        return id;
    }

    public String getNameDish() {
        return nameDish;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public List<Ingredient> getListIngridient() {
        return listIngridient;
    }

    public List<String> getListSteps() {
        return listSteps;
    }

    public static ReceptDTO from(int id, Recept recept) {
        return new ReceptDTO(id, recept.getNameDish(), recept.getCookingTime(),
                recept.getListIngridient(),recept.getListSteps());
    }
}
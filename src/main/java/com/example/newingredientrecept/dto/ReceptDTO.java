package com.example.newingredientrecept.dto;


import com.example.newingredientrecept.model.Ingredient;
import com.example.newingredientrecept.model.Recept;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class ReceptDTO {
    private final int id ;
    private final String nameDish ;
    private final  int cookingTime;

    private final List<Ingredient> listIngridient ;
    private final List<String> listSteps;


    public static ReceptDTO from(int id, Recept recept) {
        return new ReceptDTO(id, recept.getNameDish(), recept.getCookingTime(),
                recept.getListIngridient(),recept.getListSteps());
    }
}
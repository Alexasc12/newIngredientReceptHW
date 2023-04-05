package com.example.newingredientrecept.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Ingredient {
//    Название в формате строки;
//    Количество ингредиентов в формате целого положительного числа;
//    Единица измерения в формате строки.

    private  String nameIngredients;
    private int weight;
    private String measureUnit;


}
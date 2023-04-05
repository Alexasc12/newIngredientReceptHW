package com.example.newingredientrecept.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Data
@AllArgsConstructor
public class Recept {
    //    Название в формате строки;
//    Время приготовления в минутах в формате целого положительного числа;
//    Ингредиенты в формате списка объектов;
//    Шаги приготовления в формате списка строк.
    private String nameDish ;
    private int cookingTime;

    private List<Ingredient> listIngridient ;
    private List<String> listSteps;



}
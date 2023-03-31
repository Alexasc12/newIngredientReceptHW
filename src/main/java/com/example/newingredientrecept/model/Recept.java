package com.example.newingredientrecept.model;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Recept {
    //    Название в формате строки;
//    Время приготовления в минутах в формате целого положительного числа;
//    Ингредиенты в формате списка объектов;
//    Шаги приготовления в формате списка строк.
    private String nameDish ;
    private int cookingTime;

    private List<Ingredient> listIngridient ;
    private List<String> listSteps;


    public Recept(String nameDish, int cookingTime, List<Ingredient> listIngridient, List<String> listSteps) {
        this.nameDish = nameDish;
        this.cookingTime = cookingTime;
        this.listIngridient = listIngridient;
        this.listSteps = listSteps;
    }

    public String getNameDish() {
        return nameDish;
    }

    public void setNameDish(String nameDish) {
        this.nameDish = nameDish;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public List<Ingredient> getListIngridient() {
        return listIngridient;
    }

    public void setListIngridient(List<Ingredient> listIngridient) {
        this.listIngridient = listIngridient;
    }

    public List<String> getListSteps() {
        return listSteps;
    }

    public void setListSteps(List<String> listSteps) {
        this.listSteps = listSteps;
    }
}
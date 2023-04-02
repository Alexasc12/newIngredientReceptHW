package com.example.newingredientrecept.model;


public class Ingredient {
//    Название в формате строки;
//    Количество ингредиентов в формате целого положительного числа;
//    Единица измерения в формате строки.

    private  String nameIngredients;
    private int weight;
    private String measureUnit;

    public Ingredient(String nameIngredients, int weight, String measureUnit) {
        this.nameIngredients = nameIngredients;
        this.weight = weight;
        this.measureUnit = measureUnit;
    }

    public String getNameIngredients() {
        return nameIngredients;
    }

    public void setNameIngredients(String nameIngredients) {
        this.nameIngredients = nameIngredients;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getMeasureUnit() {
        return measureUnit;
    }

    public void setMeasureUnit(String measureUnit) {
        this.measureUnit = measureUnit;
    }
}
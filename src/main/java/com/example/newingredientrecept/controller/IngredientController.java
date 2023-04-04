package com.example.newingredientrecept.controller;

import com.example.newingredientrecept.model.Ingredient;
import com.example.newingredientrecept.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ingredient")
public class IngredientController {

    IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    public Ingredient getIngredient(@PathVariable("id") int id) {
        return ingredientService.getIngredient(id);
    }

    @GetMapping
    public ResponseEntity<List<Ingredient>>  getAllIngredient() {
    List<Ingredient> ingredient2 =   ingredientService.getAllIngredient();
     return ResponseEntity.ok(ingredient2) ;


    }

    @PostMapping()
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.postIngredient(ingredient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient ) {
       Ingredient ingredient1 = ingredientService.editIngredient(id, ingredient);
        if (ingredient1 == null) {
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(ingredient);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable int id) {
       if (ingredientService.deleteIngredient(id)) {
           return ResponseEntity.ok().build();
       }
       return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity <Void> deleteAllIngredient() {
        ingredientService.deleteAllIngredient();
        return  ResponseEntity.ok().build();

    }




}

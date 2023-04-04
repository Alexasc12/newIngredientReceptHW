package com.example.newingredientrecept.controller;


import com.example.newingredientrecept.dto.ReceptDTO;
import com.example.newingredientrecept.model.Ingredient;
import com.example.newingredientrecept.model.Recept;
import com.example.newingredientrecept.service.ReceptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
public class ReceptController {

    ReceptService receptService;

    public ReceptController(ReceptService receptService) {
        this.receptService = receptService;
    }

    @GetMapping("/{id}")
    public Recept getRecept(@PathVariable("id") int id) {
        return receptService.getRecept(id);
    }

    @GetMapping
    public ResponseEntity<List<Recept>> getAllRecept() {
       List <Recept> recept2 =   receptService.getAllRecept();
        return ResponseEntity.ok(recept2) ;
    }

    @PostMapping()
    public ReceptDTO addRecept(@RequestBody Recept recept) {
        return receptService.postRecept(recept);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recept> editIngredient(@PathVariable int id, @RequestBody Recept recept ) {
        Recept recept1 = receptService.editRecept(id, recept);
        if (recept1 == null) {
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(recept1);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecept(@PathVariable int id) {
        if (receptService.deleteRecept(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    public ResponseEntity <Void> deleteAllRecept() {
        receptService.deleteAllRecept();
        return  ResponseEntity.ok().build();

    }



}

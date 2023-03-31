package com.example.newingredientrecept.controller;


import com.example.newingredientrecept.dto.ReceptDTO;
import com.example.newingredientrecept.model.Recept;
import com.example.newingredientrecept.service.ReceptService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public ReceptDTO addRecept(@RequestBody Recept recept) {
        return receptService.postRecept(recept);
    }




}

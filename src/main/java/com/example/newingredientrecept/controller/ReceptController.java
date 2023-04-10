package com.example.newingredientrecept.controller;


import com.example.newingredientrecept.dto.ReceptDTO;
import com.example.newingredientrecept.model.Ingredient;
import com.example.newingredientrecept.model.Recept;
import com.example.newingredientrecept.service.ReceptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipe")
@Tag(name = "Рецепты", description = "CRUD - операции и другие элементы для работы с рецептами" )
public class ReceptController {

    ReceptService receptService;

    public ReceptController(ReceptService receptService) {
        this.receptService = receptService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Поиск рецепта",
            description = "Можно искать по номеру id"
    )
    @ApiResponses(value =
            @ApiResponse(
                    responseCode = "200",
                    description = "Рецепт найден",
                    content = {
                            @Content (
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Recept.class))
                            )
                    }
            )
    )
    public Recept getRecept(@PathVariable("id") int id) {
        return receptService.getRecept(id);
    }
    @GetMapping
    @Operation(
            summary = "Поиск всех рецептов",
            description = "Можно найти все рецепты и получить их"
    )
    @ApiResponses(value =
    @ApiResponse(
            responseCode = "200",
            description = "Все рецепты найдены",
            content = {
                    @Content (
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Recept.class))
                    )
            }
    )
    )
       public ResponseEntity<List<Recept>> getAllRecept() {
       List <Recept> recept2 =   receptService.getAllRecept();
        return ResponseEntity.ok(recept2) ;
    }

    @PostMapping()
    @Operation(
            summary = "Добавление рецепта",
            description = "Можно добавить новый рецепт"
    )
    @ApiResponses(value =
    @ApiResponse(
            responseCode = "200",
            description = "Рецепт добавлен",
            content = {
                    @Content (
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ReceptDTO.class))
                    )
            }
    )
    )
    public ReceptDTO addRecept(@RequestBody Recept recept) {
        return receptService.postRecept(recept);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Обновление рецепта",
            description = "Можно обновить рецепт по номеру id"
    )
    @ApiResponses(value =
    @ApiResponse(
            responseCode = "200",
            description = "Рецепт обновлен",
            content = {
                    @Content (
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Recept.class))
                    )
            }
    )
    )
    public ResponseEntity<Recept> editIngredient(@PathVariable int id, @RequestBody Recept recept ) {
        Recept recept1 = receptService.editRecept(id, recept);
        if (recept1 == null) {
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(recept1);

    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление рецепта",
            description = "Можно удалить рецепт по номеру id"
    )
    @ApiResponses(value =
    @ApiResponse(
            responseCode = "200",
            description = "Рецепт удален",
            content = {
                    @Content (
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Recept.class))
                    )
            }
    )
    )
    public ResponseEntity<Void> deleteRecept(@PathVariable int id) {
        if (receptService.deleteRecept(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping
    @Operation(
            summary = "Удалить все рецепты",
            description = "Можно удалить все рецепты"
    )
    @ApiResponses(value =
    @ApiResponse(
            responseCode = "200",
            description = "Все рецепты удаленны",
            content = {
                    @Content (
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Recept.class))
                    )
            }
    )
    )
    public ResponseEntity <Void> deleteAllRecept() {
        receptService.deleteAllRecept();
        return  ResponseEntity.ok().build();

    }



}

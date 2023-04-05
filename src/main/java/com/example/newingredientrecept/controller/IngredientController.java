package com.example.newingredientrecept.controller;

import com.example.newingredientrecept.dto.ReceptDTO;
import com.example.newingredientrecept.model.Ingredient;
import com.example.newingredientrecept.model.Recept;
import com.example.newingredientrecept.service.IngredientService;
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
import java.util.Map;

@RestController
@RequestMapping("/ingredient")
@Tag(name = "Ингридиенты", description = "CRUD - операции и другие элементы для работы с ингридиентами" )
public class IngredientController {

    IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Поиск ингридиента",
            description = "Можно искать по номеру id"
    )
    @ApiResponses(value =
    @ApiResponse(
            responseCode = "200",
            description = "Ингридиент найден",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                    )
            }
    )
    )
    public Ingredient getIngredient(@PathVariable("id") int id) {
        return ingredientService.getIngredient(id);
    }

    @GetMapping
    @Operation(
            summary = "Поиск всех нгредиентов",
            description = "Можно найти все ингредиенты и получить их"
    )
    @ApiResponses(value =
    @ApiResponse(
            responseCode = "200",
            description = "Все ингредиенты найдены",
            content = {
                    @Content (
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                    )
            }
    )
    )
    public ResponseEntity<List<Ingredient>>  getAllIngredient() {
    List<Ingredient> ingredient2 =   ingredientService.getAllIngredient();
     return ResponseEntity.ok(ingredient2) ;
    }
    @PostMapping()
    @Operation(
            summary = "Добавление ингредиента",
            description = "Можно добавить новый ингредиент"
    )
    @ApiResponses(value =
    @ApiResponse(
            responseCode = "200",
            description = "Ингредиент добавлен",
            content = {
                    @Content (
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                    )
            }
    )
    )
    public Ingredient addIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.postIngredient(ingredient);
    }
    @PutMapping("/{id}")
    @Operation(
            summary = "Обновление ингредиента",
            description = "Можно обновить ингредиент по номеру id"
    )
    @ApiResponses(value =
    @ApiResponse(
            responseCode = "200",
            description = "Ингредиент обновлен",
            content = {
                    @Content (
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                    )
            }
    )
    )
    public ResponseEntity<Ingredient> editIngredient(@PathVariable int id, @RequestBody Ingredient ingredient ) {
       Ingredient ingredient1 = ingredientService.editIngredient(id, ingredient);
        if (ingredient1 == null) {
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(ingredient);
    }
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление ингредиента",
            description = "Можно удалить ингредиент по номеру id"
    )
    @ApiResponses(value =
    @ApiResponse(
            responseCode = "200",
            description = "Ингредиент удален",
            content = {
                    @Content (
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                    )
            }
    )
    )
    public ResponseEntity<Void> deleteIngredient(@PathVariable int id) {
       if (ingredientService.deleteIngredient(id)) {
           return ResponseEntity.ok().build();
       }
       return ResponseEntity.notFound().build();
    }
    @DeleteMapping
    @Operation(
            summary = "Удалить все ингредиенты",
            description = "Можно удалить все ингредиенты"
    )
    @ApiResponses(value =
    @ApiResponse(
            responseCode = "200",
            description = "Все ингредиенты удаленны",
            content = {
                    @Content (
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Ingredient.class))
                    )
            }
    )
    )
    public ResponseEntity <Void> deleteAllIngredient() {
        ingredientService.deleteAllIngredient();
        return  ResponseEntity.ok().build();
    }
}

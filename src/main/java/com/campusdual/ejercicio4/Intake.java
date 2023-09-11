package com.campusdual.ejercicio4;

import com.campusdual.ejemplos.alimentos.Food;

public class Intake extends Food {

    public static final Integer GRAMS_PER_PORTION = 100;
    Integer grams;

    // Constructor que recibe el nombre del alimento
    public Intake(String name) {
        super(name);
    }

    // Constructor que recibe detalles del alimento y la cantidad en gramos
    public Intake(String name, Integer carbos, Integer fats, Integer proteins, Integer grams) {
        super(name, carbos, fats, proteins);
        this.grams = grams;
    }

    // Constructor que recibe un objeto Food y la cantidad en gramos
    public Intake(Food food, Integer grams) {
        super(food.getName(), food.getCarbos(), food.getFats(), food.getProteins());
        this.setGrams(grams);
    }

    // Método para calcular las calorías basadas en la cantidad en gramos
    public Integer calculatedCalories() {
        return this.getCalories(this.grams);
    }

    // Método para calcular la cantidad de carbohidratos basada en la cantidad en gramos
    public Integer calculatedCarbos() {
        return this.getCarbos() * grams / GRAMS_PER_PORTION;
    }

    // Método para calcular la cantidad de grasas basada en la cantidad en gramos
    public Integer calculatedFats() {
        return this.getFats() * grams / GRAMS_PER_PORTION;
    }

    // Método para calcular la cantidad de proteínas basada en la cantidad en gramos
    public Integer calculatedProteins() {
        return this.getProteins() * grams / GRAMS_PER_PORTION;
    }

    // Getter para obtener la cantidad en gramos
    public Integer getGrams() {
        return grams;
    }

    // Setter para establecer la cantidad en gramos
    public void setGrams(Integer grams) {
        this.grams = grams;
    }
}
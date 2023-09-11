package com.campusdual.diet;

/*
* Escribe una clase dieta, que teniendo en cuenta una serie de alimentos, vaya sumando cantidades en gramos y calcule cuentas calorías, carbohidratos, proteinas y grasas genera la ingesta
La clase dieta tiene que tener las siguientes funcionalidades:
  -Diet(): crea una dieta sin límite de calorías
  -Diet(Integer maxCalories): crea una dieta con un máximo de calorías, en cuanto se supera ese máximo cuando se adjunta un alimento se despliega un error
  -Diet(Integer maxFats, Integer maxCarbs, Integer maxProtein): crea una dieta con un máximo de estos tres valores, si se supera alguno de ellos cuando se adjunta un alimento se indicara cual y desplegará un error
  -Diet(Boolean women, Integer age, Integer height, Integer weight): Calcula el metabolismo basal de la persona y lo asigna como máximo de calorías en la dieta
       --CALCULAR METABOLISMO BASAL
          E = edad
          A = altura en centímetros
          P = peso en kilos
            Fórmula Hombres: TMB = 10P + 6,25A – 5E + 5
            Fórmula Mujeres: TMB = 10P + 6,25A – 5E – 161
        -addFood(Food,Integer): agrega un alimento y una cantidad en gramos
        -getTotalCalories(): devuelve el total de calorías
        -getTotalCarbs(): devuelve el total de carbohidratos
        -getTotalFats(): devuelve el total de grasas
        -getTotalProtein(): devuelve el total de proteinas
*
* */

import java.util.ArrayList;
import java.util.List;

public class Diet {
    private Integer maxCalories;  // Máximo de calorías permitidas en la dieta
    private Integer maxFats;      // Máximo de grasas permitidas en la dieta
    private Integer maxCarbs;     // Máximo de carbohidratos permitidos en la dieta
    private Integer maxProtein;   // Máximo de proteínas permitidas en la dieta
    private Integer totalCalories; // Total de calorías en la dieta
    private Integer totalCarbs;   // Total de carbohidratos en la dieta
    private Integer totalFats;    // Total de grasas en la dieta
    private Integer totalProteins; // Total de proteínas en la dieta
    private List<Food> alimentos; // Lista de alimentos en la dieta

    // Constructor sin límite de calorías
    public Diet() {
        this.maxCalories = Integer.MAX_VALUE;
        this.maxFats = Integer.MAX_VALUE;
        this.maxCarbs = Integer.MAX_VALUE;
        this.maxProtein = Integer.MAX_VALUE;
        this.totalCalories = 0;
        this.totalCarbs = 0;
        this.totalFats = 0;
        this.totalProteins = 0;
        this.alimentos = new ArrayList<>();
    }

    // Constructor con límite de calorías
    public Diet(Integer maxCalories) {
        this();
        this.maxCalories = maxCalories;
    }

    // Constructor con límite de grasas, carbohidratos y proteínas
    public Diet(Integer maxFats, Integer maxCarbs, Integer maxProtein) {
        this();
        this.maxFats = maxFats;
        this.maxCarbs = maxCarbs;
        this.maxProtein = maxProtein;
    }

    // Constructor basado en datos personales
    public Diet(Boolean women, Integer age, Integer height, Integer weight) {
        this();
        if (women) {
            this.maxCalories = (int) (10 * weight + 6.25 * height - 5 * age - 161);
        } else {
            this.maxCalories = (int) (10 * weight + 6.25 * height - 5 * age + 5);
        }
    }

    // Método para gregar alimento y cantidad en gramos a la dieta
    public void addFood(com.campusdual.diet.Food food, int grams) {
        if (totalCalories + (food.getCalories(grams)) > maxCalories) {
            System.out.println("Error: Ha superado el límite de calorías.");
        } else {
            totalCalories += food.getCalories(grams);
            totalCarbs += food.getCarbos() * grams;
            totalFats += food.getFats() * grams;
            totalProteins += food.getProteins() * grams;
        }
    }

    // Total de calorias
    public Integer getTotalCalories() {
        return totalCalories;
    }

    // Total de carbohidratos
    public Integer getTotalCarbs() {
        return totalCarbs;
    }

    // Total de grasas
    public Integer getTotalFats() {
        return totalFats;
    }

    // Total de proteínas
    public Integer getTotalProteins() {
        return totalProteins;
    }
}
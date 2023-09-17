package com.campusdual.ejercicio6;

import com.campusdual.ejercicio6.Food;
import com.campusdual.ejercicio6.Intake;
import com.campusdual.ejercicio6.exceptions.*;
import com.campusdual.ejercicio6.enums.Gender;

import java.util.ArrayList;
import java.util.List;

public class Diet {
    public static final String OK = "OK";
    public static final String MAX_CALORIES_REBASE = "MAX_CALORIES_REBASE";
    public static final String MAX_CARBS_REBASE = "MAX_CARBS_REBASE";
    public static final String MAX_FATS_REBASE = "MAX_FATS_REBASE";
    public static final String MAX_PROTEINS_REBASE = "MAX_PROTEINS_REBASE";

    private String name;
    private Integer maxCalories;
    private Integer maxCarbs;
    private Integer maxFats;
    private Integer maxProteins;
    private List<Intake> intakes;
    private Gender gender;

    public Diet(String name) {
        this.intakes = new ArrayList<>();
        this.name = name;

    }

    public Diet(String name, Integer maxCalories) {
        this.maxCalories = maxCalories;
        this.intakes = new ArrayList<>();
        this.name = name;
    }

    public Diet(String name, Integer maxFats, Integer maxCarbs, Integer maxProteins) {
        this.maxCarbs = maxCarbs;
        this.maxFats = maxFats;
        this.maxProteins = maxProteins;
        this.intakes = new ArrayList<>();
        this.name = name;
    }

    public Diet(String name, Gender gender, Integer age, Integer height, Integer weight) {
        if (Gender.FEMALE == gender) {
            maxCalories = (int) ((10 * weight) + (6.25 * height)) - (5 * age) - 161;
        } else {
            maxCalories = (int) ((10 * weight) + (6.25 * height)) - (5 * age) + 5;
        }
        this.intakes = new ArrayList<>();
        this.name = name;
    }

//    public void showDietDetails(){
//
//        System.out.println("·······························");
//        System.out.println("       DATOS DE LA DIETA       ");
//        System.out.println("·······························");
//        System.out.println("Número de alimentos de la dieta: " + this.getFoodNumber());
//        System.out.println("Alimentos: " + this.getDietIntakes());
//        System.out.println("Calorías: " + this.getTotalCalories());
//        System.out.println("Carbohidratos: " + this.getTotalCarbs());
//        System.out.println("Grasas: " + this.getTotalFats());
//        System.out.println("Proteínas: " + this.getTotalProteins());
//
//    }

    public void addFood(Food food, Integer grams) throws MaxValuedReachedException, MaxCaloriesReachedException, MaxCarbsReachedException, MaxFatsReachedException, MaxProteinsReachedException {
        Intake intake = new Intake(food, grams);
        String validation = isValidIntake(intake);
        if (OK.equalsIgnoreCase(validation)) {
            intakes.add(intake);
        } else {
            if (MAX_CALORIES_REBASE.equalsIgnoreCase(validation)) {
                throw new MaxCaloriesReachedException();
            }
            if (MAX_CARBS_REBASE.equalsIgnoreCase(validation)) {
                throw new MaxCarbsReachedException();
            }
            if (MAX_FATS_REBASE.equalsIgnoreCase(validation)) {
                throw new MaxFatsReachedException();
            }
            if (MAX_PROTEINS_REBASE.equalsIgnoreCase(validation)) {
                throw new MaxProteinsReachedException();
            }
        }
    }

    private String isValidIntake(Intake intake) {
        Integer actualCaories = getTotalCalories();
        if (this.maxCalories != null && this.maxCalories < (actualCaories + intake.calculatedCalories())) {
            return MAX_CALORIES_REBASE;
        }
        Integer actualCarbs = getTotalCarbs();
        if (this.maxCarbs != null && this.maxCarbs < actualCarbs + intake.calculatedCarbos()) {
            return MAX_CARBS_REBASE;
        }
        Integer actualFats = getTotalFats();

        if (this.maxFats != null && this.maxFats < actualFats + intake.calculatedFats()) {
            return MAX_FATS_REBASE;
        }
        Integer actualProteins = getTotalProteins();
        if (this.maxProteins != null && this.maxProteins < actualProteins + intake.calculatedProteins()) {
            return MAX_PROTEINS_REBASE;
        }
        return OK;
    }

    public Integer getTotalCalories() {
        Integer totalCalories = 0;
        for (Intake intake : intakes) {
            totalCalories = totalCalories + intake.calculatedCalories();
        }
        return totalCalories;
    }

    public Integer getTotalCarbs() {
        Integer totalCarbs = 0;
        for (Intake intake : intakes) {
            totalCarbs = totalCarbs + intake.calculatedCarbos();
        }
        return totalCarbs;
    }

    public Integer getTotalFats() {
        Integer totalFats = 0;
        for (Intake intake : intakes) {
            totalFats = totalFats + intake.calculatedFats();
        }
        return totalFats;
    }

    public Integer getTotalProteins() {
        Integer totalProtein = 0;
        for (Intake intake : intakes) {
            totalProtein = totalProtein + intake.calculatedProteins();
        }
        return totalProtein;
    }

    public String getDietIntakes() {
        String result = "";
        boolean first = true;
        for (Intake intake : intakes) {
            if (first) {
                first = false;
                result = intake.getName() + ": " + intake.getGrams() + " grs.";
            } else {
                result = result + ", " + intake.getName() + ": " + intake.getGrams() + " grs.";
            }
        }
        return result;
    }

    public Integer getFoodNumber() {
        return intakes.size();
    }

    public Integer getMaxCalories() {
        return maxCalories;
    }

    public void setMaxCalories(Integer maxCalories) {
        this.maxCalories = maxCalories;
    }

    public Integer getMaxCarbs() {
        return maxCarbs;
    }

    public void setMaxCarbs(Integer maxCarbs) {
        this.maxCarbs = maxCarbs;
    }

    public Integer getMaxFats() {
        return maxFats;
    }

    public void setMaxFats(Integer maxFats) {
        this.maxFats = maxFats;
    }

    public Integer getMaxProteins() {
        return maxProteins;
    }

    public void setMaxProteins(Integer maxProteins) {
        this.maxProteins = maxProteins;
    }

    public List<Intake> getIntakes() {
        return intakes;
    }

    public void setIntakes(List<Intake> intakes) {
        this.intakes = intakes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

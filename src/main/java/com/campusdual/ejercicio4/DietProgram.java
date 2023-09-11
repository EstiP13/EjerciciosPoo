package com.campusdual.ejercicio4;

import com.campusdual.ejemplos.alimentos.Food;
import com.campusdual.ejercicio4.exceptions.MaxCaloriesReachedException;
import com.campusdual.ejercicio4.exceptions.MaxCarbsReachedException;
import com.campusdual.ejercicio4.exceptions.MaxFatsReachedException;
import com.campusdual.ejercicio4.exceptions.MaxProteinsReachedException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class DietProgram {

    private Diet diet = null;  // La dieta actualmente en uso
    private List<Food> foodList;  // Una lista de alimentos disponibles

    public DietProgram(){
        foodList = new ArrayList<>();  // Inicializar la lista de alimentos
    }

    // Método para obtener una opción válida entre min y max del usuario
    private Integer getOption(Integer min, Integer max){
        Integer option = 0;
        Boolean notValid = true;
        do {
            try {
                option = Kb.forceNextInt();  // Usa el método de entrada personalizado para obtener un entero
                notValid = option < min || option > max;  // Verifica si la opción es válida
            } catch (InputMismatchException e) {
                System.out.println("La opción debe ser un número");
                Kb.nextLine();  // Limpia el búfer de entrada en caso de una entrada no válida
            }
            if (notValid) {
                System.out.println("Opción no válida, se requiere un número entre " + min + " y " + max);
            }
        } while (notValid);
        return option;
    }

    // Método principal para mostrar el menú del programa y gestionar las opciones del usuario
    public void showMenuProgram(){
        System.out.println("########################################################");
        System.out.println("################# Programa de dietas ###################");
        System.out.println("########################################################");
        Integer option;
        do {
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Crear/reiniciar dieta");
            System.out.println("2-Mostrar información de la dieta");
            System.out.println("3-Agregar alimento al plan actual");
            System.out.println("4-Salir del programa");
            option = getOption(1, 4);  // Obtener la opción válida del usuario

            switch (option) {
                case 1:
                    createMenu();  // Crear una nueva dieta
                    break;
                case 2:
                    showDetailsMenu();  // Mostrar detalles de la dieta actual
                    break;
                case 3:
                    addFoodMenu();  // Agregar alimentos a la dieta
                    break;
                case 4:
                    System.out.println("Gracias por usar el programa, hasta pronto :)");
                    break;
            }
        } while (option != 4);  // Continuar hasta que el usuario seleccione la opción de salida
    }

    // Método para agregar alimentos a la dieta
    private void addFoodMenu() {
        if (this.diet == null) {
            System.out.println("Para agregar alimentos hace falta iniciar una dieta");
            return;
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Agregar Alimentos a la dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Agregar un nuevo alimento");
        System.out.println("2-Agregar un alimento ya existente");

        Integer option = getOption(1, 2);

        switch (option) {
            case 1:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Datos de nuevo alimento");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Nombre del alimento:");
                String name = Kb.nextLine();
                System.out.println("Carbohidratos:");
                Integer carbs = Kb.forceNextInt();
                System.out.println("Grasas:");
                Integer fats = Kb.forceNextInt();
                System.out.println("Proteínas:");
                Integer proteins = Kb.forceNextInt();
                System.out.println("Gramos:");
                Integer grams = Kb.forceNextInt();
                Food newFood = new Food(name, carbs, fats, proteins);
                validateAndAddFoodToDiet(newFood, grams);
                foodList.add(newFood);  // Agregar el nuevo alimento a la lista de alimentos
                break;
            case 2:
                if (foodList.size() == 0) {
                    System.out.println("Para agregar un alimento existente, deben existir alimentos previos");
                    return;
                }
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escoja un alimento");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer i = 1;
                for (Food food : foodList) {
                    System.out.println(i + "- " + food.getName());
                    i++;
                }
                System.out.println(i + "- Cancelar");
                Integer element = getOption(1, i);

                if (element == i) {
                    System.out.println("Cancelando alimento");
                    return;
                }

                Food storedFood = foodList.get(element - 1);
                System.out.println("Indique el número de gramos de " + storedFood.getName());
                Integer foodGrams = Kb.forceNextInt();
                validateAndAddFoodToDiet(storedFood, foodGrams);
                break;
        }
    }

    // Método para validar y agregar un alimento a la dieta
    private void validateAndAddFoodToDiet(Food food, Integer grams) {
        try {
            this.diet.addFood(food, grams);  // Intenta agregar el alimento a la dieta actual
        } catch (MaxCaloriesReachedException ecal) {
            System.out.println("Se ha alcanzado el máximo valor de calorías permitido");
        } catch (MaxCarbsReachedException ecar) {
            System.out.println("Se ha alcanzado el máximo valor de carbohidratos permitido");
        } catch (MaxFatsReachedException efat) {
            System.out.println("Se ha alcanzado el máximo valor de grasas permitido");
        } catch (MaxProteinsReachedException epro) {
            System.out.println("Se ha alcanzado el máximo valor de proteínas permitido");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Método para crear una nueva dieta
    private void createMenu() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Crear nueva dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Elija una opción:");
        System.out.println("===================================");
        System.out.println("1-Crear una dieta basada en límites nutricionales");
        System.out.println("2-Crear una dieta basada en datos personales");
        System.out.println("3-Volver al menú principal");

        Integer option = getOption(1, 3);

        switch (option) {
            case 1:
                System.out.println("Ingrese los límites nutricionales:");
                System.out.println("Máximo de calorías:");
                Integer maxCalories = Kb.forceNextInt();
                System.out.println("Máximo de carbohidratos:");
                Integer maxCarbs = Kb.forceNextInt();
                System.out.println("Máximo de grasas:");
                Integer maxFats = Kb.forceNextInt();
                System.out.println("Máximo de proteínas:");
                Integer maxProteins = Kb.forceNextInt();
                diet = new Diet(maxFats, maxCarbs, maxProteins);
                break;
            case 2:
                System.out.println("Ingrese sus datos personales:");
                System.out.println("¿Es usted mujer? (true/false):");
                Boolean isWoman = Kb.forceNextBoolean();
                System.out.println("Edad:");
                Integer age = Kb.forceNextInt();
                System.out.println("Altura (en cm):");
                Integer height = Kb.forceNextInt();
                System.out.println("Peso (en kg):");
                Integer weight = Kb.forceNextInt();
                diet = new Diet(isWoman, age, height, weight);
                break;
            case 3:
                System.out.println("Volviendo al menú principal");
                break;
        }
    }

    // Método para mostrar detalles de la dieta actual
    private void showDetailsMenu() {
        if (diet == null) {
            System.out.println("No hay dieta en uso");
            return;
        }

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Detalles de la dieta actual");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Número de alimentos en la dieta: " + diet.getFoodNumber());
        System.out.println("Calorías consumidas: " + diet.getTotalCalories());
        System.out.println("Carbohidratos consumidos: " + diet.getTotalCarbs());
        System.out.println("Grasas consumidas: " + diet.getTotalFats());
        System.out.println("Proteínas consumidas: " + diet.getTotalProteins());
        System.out.println("Alimentos en la dieta: " + diet.getDietIntakes());
    }

    public static void main(String[] args) {
        DietProgram dp = new DietProgram();
        dp.showMenuProgram();  // Iniciar el programa de la dieta
    }
}
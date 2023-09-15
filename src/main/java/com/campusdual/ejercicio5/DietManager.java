package com.campusdual.ejercicio5;

import com.campusdual.ejercicio5.model.Diet;

import java.util.ArrayList;
import java.util.List;

// Creamos la clase DietManager para gestionar las dietas
public class DietManager {

    private List<Diet> dietList;

    // Constructor
    public DietManager() {
        dietList = new ArrayList<>(); // Inicializa la lista de dietas
    }

    // Agregar una dieta a la lista
    public void addDiet(Diet diet) {
        dietList.add(diet);
    }

    // Obtener la lista de dietas
    public List<Diet> getDietList() {
        return dietList;
    }

    // Buscar una dieta por nombre
    public Diet findDietByName(String name) {
        for (Diet diet : dietList) {
            if (diet.getDietName().equalsIgnoreCase(name)) {
                return diet;
            }
        }
        return null; // Retorna null si no se encuentra la dieta
    }

    // Método para actualizar una dieta
    public void updateDiet (Diet dietToEdit) {
        // Obtenemos la lista de dietas
        List<Diet> dietList = getDietList();
        // Buscamos la dieta que se va a actualizar (por nombre)
        for (Diet diet : dietList) {
            if (diet.getDietName().equals(dietToEdit.getDietName())) {
                // Encuentra la dieta por su nombre y actualiza sus atributos
                diet.setDietName(dietToEdit.getDietName()); // Actualizar el nombre
                diet.setMaxCalories(dietToEdit.getMaxCalories()); // Actualizar las calorías máximas
                diet.setMaxCarbs(dietToEdit.getMaxCarbs()); // Actualizar los carbohidratos máximos
                diet.setMaxFats(dietToEdit.getMaxFats()); // Actualizar las grasas máximas
                diet.setMaxProteins(dietToEdit.getMaxProteins()); // Actualizar las proteínas máximas
                diet.setIntakes(dietToEdit.getIntakes()); // Actualizar la lista de ingestas

                System.out.println("Dieta actualizada exitosamente.");
                return;
            }
        }
        System.out.println("La dieta no se encontró en la lista de dietas.");
    }

    // Eliminar una dieta de la lista
    public void removeDiet(Diet diet) {
        dietList.remove(diet);
    }

    // Obtener la lista de dietas
    public List<Diet> getAllDiets() {
        return dietList;
    }

    // Método para eliminar una dieta por nombre
    public void deleteDietByName(String dietName) {
        Diet dietToRemove = findDietByName(dietName);
        if (dietToRemove != null) {
            dietList.remove(dietToRemove);
        }
    }

    // Listar todas las dietas en la lista
    public void listAllDiets() {
        System.out.println("Lista de Dietas:");
        for (Diet diet : dietList) {
            System.out.println("Nombre de la Dieta: " + diet.getDietName());
        }
    }

    // Método para verificar si una dieta con el nombre especificado ya existe
    public boolean dietExists(String dietName) {
        for (Diet diet : dietList) {
            if (diet.getDietName().equalsIgnoreCase(dietName)) {
                return true; // La dieta ya existe
            }
        }
        return false; // La dieta no existe
    }
}

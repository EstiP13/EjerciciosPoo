package com.campusdual.ejercicio5;

import com.campusdual.ejercicio5.Food;
import com.campusdual.ejercicio5.Kb;
import com.campusdual.ejercicio5.exceptions.MaxCaloriesReachedException;
import com.campusdual.ejercicio5.exceptions.MaxCarbsReachedException;
import com.campusdual.ejercicio5.exceptions.MaxFatsReachedException;
import com.campusdual.ejercicio5.exceptions.MaxProteinsReachedException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class DietProgram {

    private DietManager dietManager;  // lista de dietas en DietManager
    private PatientManager patientManager; // lista de pacientes en PatientManager
    private Diet currentDiet; // Almacena la dieta actual
    private Diet diet;  // Declarar variable diet
    private List<Food> foodList = new ArrayList<>();  // Declarar lista de alimentos


    public DietProgram() {
        dietManager = new DietManager(); // Declaración e inicialización de DietManager
        patientManager = new PatientManager(); // Declaración e inicialización de PatientManager
    }

    // Método para agregar una dieta a la lista
    public void addDietToList(Diet diet, String dietName) {
        diet.setDietName(dietName); // Establece el nombre de la dieta
        dietManager.addDiet(diet); // Agrega la dieta al DietManager
    }

    // Método para agregar un paciente a la lista
    public void addPatient(Patient patient) {
        patientManager.addPatient(patient); // Agrega el paciente a PatientManager
    }

    private Integer getOption(Integer min, Integer max) {
        Integer option = 0;
        Boolean notvalid = true;
        do {
            try {
                option = Kb.forceNextInt();
                notvalid = option < min || option > max;
            } catch (InputMismatchException e) {
                System.out.println("La opción debe ser un número");
                Kb.nextLine();
            }
            if (notvalid) {
                System.out.println("Opción no valida, se requiere un número entre " + min + " y " + max);
            }
        } while (notvalid);
        return option;
    }

    // Modificamos el Menú principal
    public void showMenuProgram() {
        System.out.println("########################################################");
        System.out.println("################# Programa de dietas ###################");
        System.out.println("########################################################");
        Integer option;
        do {
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Gestión dietas");
            System.out.println("2-Gestión de pacientes");
            System.out.println("3-Salir del programa");
            option = getOption(1, 3);
            switch (option) {
                case 1:
                    showDietMenu();
                    break;
                case 2:
                    showPatientMenu();
                    break;
                case 3:
                    System.out.println("Gracias por usar el programa, hasta pronto :)");
                    break;
                default:
                    System.out.println("Opción no válida. Pruebe de nuevo.");
                    break;
            }
        } while (option != 3);
    }

    // Creamos el Menú de Gestión de dietas
    public void showDietMenu() {
        System.out.println("########################################################");
        System.out.println("################# Gestión dietas ###################");
        System.out.println("########################################################");
        Integer option;
        do {
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Agregar nueva dieta");
            System.out.println("2-Listar dietas");
            System.out.println("3-Agregar alimento al plan actual");
            System.out.println("4-Eliminar dieta");
            System.out.println("5-Salir");
            option = getOption(1, 5);
            switch (option) {
                case 1:
                    createMenu();
                    break;
                case 2:
                    showDetailsMenu(); // Implementamos listar dietas
                    break;
                case 3:
                    addFoodMenu();
                    break;
                case 4:
                    deleteDiet(); // Implementamos eliminar dieta
                    break;
                case 5:
                    System.out.println("Gracias por usar el programa, hasta pronto :)");
                    break;
                default:
                    System.out.println("Opción no válida. Pruebe de nuevo.");
                    break;
            }
        } while (option != 5);
    }


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
                foodList.add(newFood);
                break;
            case 2:
                if (foodList.size() == 0) {
                    System.out.println("Para agregar un alimento existente, tienen que existir alimentos previos");
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
                System.out.println("indique el número de gramos de " + storedFood.getName());
                Integer foodGrams = Kb.forceNextInt();
                validateAndAddFoodToDiet(storedFood, foodGrams);
                break;
        }
    }

    // Menú para agregar una dieta a la lista
    private void addDietToProgram() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Agregar Dieta a la lista");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Nombre de la dieta:");
        String dietName = Kb.nextLine();

        Diet newDiet = null;

        // Implementar lógica para crear la dieta según seleccione usuario
        System.out.println("Escriba una opción para configurar la dieta:");
        System.out.println("===================================");
        System.out.println("1-Dieta sin límite");
        System.out.println("2-Dieta por calorías");
        System.out.println("3-Dieta por macronutrientes");
        Integer dietOption = getOption(1, 3); // Ajusta el rango de opciones

        switch (dietOption) {
            case 1:
                newDiet = new Diet();
                System.out.println("Se ha creado una dieta sin límites");
                break;
            case 2:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba número de calorías");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer calories = Kb.forceNextInt();
                newDiet = new Diet(calories);
                System.out.println("Se ha creado una dieta con " + calories + " calorías máximas");
                break;
            case 3:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los macronutrientes");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Carbohidratos:");
                Integer carbs = Kb.forceNextInt();
                System.out.println("Grasas:");
                Integer fats = Kb.forceNextInt();
                System.out.println("Proteínas:");
                Integer proteins = Kb.forceNextInt();
                newDiet = new Diet(fats, carbs, proteins);
                System.out.println("Se ha creado una dieta con Carbohidratos:" + carbs + ", Grasas:" + fats + " ,Proteínas:" + proteins);
                break;
        }

        if (newDiet != null) {
            newDiet.setDietName(dietName); // Establece el nombre de la dieta
            addDietToList(newDiet, dietName); // Agrega la dieta a la lista con nombre
            System.out.println("Se ha agregado la dieta a la lista con el nombre: " + dietName);
        }
    }

    private void validateAndAddFoodToDiet(Food food, Integer grams) {
        try {
            this.diet.addFood(food, grams);
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

    private void createMenu() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Crear/reiniciar dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Escriba un nombre para la dieta");
        String dietName = null;
        boolean dietExists;
        do {
            System.out.println("Escriba un nombre para la dieta");
            dietName = Kb.nextLine();
            dietExists = dietManager.dietExists(dietName);
            if (dietExists) {
                System.out.println("El nombre de la dieta ya existe, escriba otro.");
            }
        } while (dietExists);
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Dieta sin límite");
        System.out.println("2-Dieta por calorías");
        System.out.println("3-Dieta por macronutrientes");
        System.out.println("4-Dieta por datos personales");
        System.out.println("5-Agregar dieta a la lista"); // Nueva opción agregar una dieta
        Integer option = getOption(1, 5); // Ajustamos el rango de opciones

        switch (option) {
            case 1:
                this.diet = new Diet();
                System.out.println("Se ha creado una dieta sin límites");
                break;
            case 2:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba número de calorias");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer calories = Kb.forceNextInt();
                this.diet = new Diet(calories);
                System.out.println("Se ha creado una dieta con " + calories + " calorías máximas");
                break;
            case 3:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los macronutrientes");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Carbohidratos:");
                Integer carbs = Kb.forceNextInt();
                System.out.println("Grasas:");
                Integer fats = Kb.forceNextInt();
                System.out.println("Proteínas:");
                Integer proteins = Kb.forceNextInt();
                this.diet = new Diet(fats, carbs, proteins);
                System.out.println("Se ha creado una dieta con Carbohidratos:" + carbs + ", Grasas:" + fats + " ,Proteínas:" + proteins);
                break;
            case 4:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los datos personales del paciente");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Peso:");
                Integer weight = Kb.forceNextInt();
                System.out.println("Altura:");
                Integer height = Kb.forceNextInt();
                System.out.println("Edad:");
                Integer age = Kb.forceNextInt();
                System.out.println("Mujer u Hombre(m/h):");
                String sexCharacter = Kb.nextLine();
                this.diet = new Diet("m".equalsIgnoreCase(sexCharacter), age, height, weight);
                System.out.println("Se ha creado una dieta de " + this.diet.getMaxCalories() + " calorías máximas");
                break;
            case 5:
                addDietToProgram(); // Llama al método para agregar una dieta
                break;
        }
    }

    // Método para eliminar una dieta de la lista
    private void deleteDiet() {
        if (dietManager.getDietList().isEmpty()) {
            System.out.println("No hay dietas registradas para eliminar.");
        } else {
            System.out.println("Eliminar una dieta:");
            System.out.println("Escriba el nombre de la dieta que desea eliminar:");
            String dietName = Kb.nextLine();

            // Buscar la dieta en la lista por nombre
            Diet dietToRemove = dietManager.findDietByName(dietName);

            if (dietToRemove != null) {
                System.out.println("¿Está seguro que desea eliminar la dieta: " + dietToRemove.getDietName() + "? (S/N)");
                String confirmation = Kb.nextLine();
                if (confirmation.equalsIgnoreCase("S")) {
                    dietManager.removeDiet(dietToRemove);
                    System.out.println("Dieta eliminada exitosamente.");
                } else {
                    System.out.println("Cancelando eliminación de dieta.");
                }
            } else {
                System.out.println("No se encontró la dieta.");
            }
        }
    }

    private void showDetailsMenu() {
        if (this.diet != null) {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("Detalles de la dieta");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            if (this.diet.getMaxCalories() != null) {
                System.out.println("El número máximo de calorías es:" + this.diet.getMaxCalories());
            }
            if (this.diet.getMaxCarbs() != null || this.diet.getMaxFats() != null || this.diet.getMaxProteins() != null) {
                System.out.println("Los valores máximos de macronutrientes son: Carbohidratos:" + this.diet.getMaxCarbs() + " , Grasas:" + this.diet.getMaxFats() + " , Proteinas:" + this.diet.getMaxProteins());
            }
            System.out.println("Número de alimentos de la dieta:" + this.diet.getFoodNumber());
            System.out.println("Calorías:" + this.diet.getTotalCalories());
            System.out.println("Carbohidratos:" + this.diet.getTotalCarbs());
            System.out.println("Grasas:" + this.diet.getTotalFats());
            System.out.println("Proteínas:" + this.diet.getTotalProteins());
            System.out.println("Alimentos de la dieta:" + this.diet.getDietIntakes());
        } else {
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("La dieta no esta iniciada");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        }
    }

    // Creamos el Menú de Gestión de pacientes
    private void showPatientMenu() {
        System.out.println("########################################################");
        System.out.println("############### Gestión de Pacientes ###################");
        System.out.println("########################################################");
        Integer option;
        do {
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Añadir un paciente");
            System.out.println("2-Listar pacientes");
            System.out.println("3-Eliminar un paciente");
            System.out.println("4-Salir");
            option = getOption(1, 4);
            switch (option) {
                case 1:
                    addPatient(); // Llama al método agregar pacientes
                    break;
                case 2:
                    listPatients(); // LLama al método listar pacientes
                    break;
                case 3:
                    deletePatient(); // Llama al método eliminar pacientes
                    break;
                case 4:
                    System.out.println("Gracias por usar el programa, hasta pronto :)");
                    break;
            }

        } while (option != 4);
    }

    private void addPatient() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Añadir un paciente");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        // Solicitar los datos del paciente
        System.out.println("Nombre:");
        String patientName = Kb.nextLine();
        System.out.println("Apellidos:");
        String patientLastName = Kb.nextLine();
        System.out.println("Peso:");
        Integer patientWeight = Kb.forceNextInt();
        System.out.println("Altura:");
        Integer patientHeight = Kb.forceNextInt();
        System.out.println("Edad:");
        Integer patientAge = Kb.forceNextInt();
        System.out.println("Mujer u Hombre (m/h):");
        String patientGender = Kb.nextLine();

        // Validar el sexo ingresado y convertirlo a 'm' o 'h' si es válido
        if (!patientGender.equals("m") && !patientGender.equals("h")) {
            System.out.println("Sexo no válido. Se establecerá como 'm' por defecto.");
            patientGender = "m";
        }

        // Crear una instancia de Patient y agregarla al PatientManager
        Patient newPatient = new Patient(patientName, patientLastName, patientAge, patientHeight, patientWeight, patientGender.charAt(0));
        patientManager.addPatient(newPatient);
        System.out.println("Paciente agregado exitosamente.");
    }

    private void listPatients() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Listar pacientes");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        List<Patient> patients = patientManager.getPatientList();
        // Verifica si hay pacientes en la lista
        if (patients.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
        } else {
            int count = 1;
            for (Patient patient : patients) {
                System.out.println("Paciente #" + count + ":");
                System.out.println("Nombre: " + patient.getName());
                System.out.println("Apellidos: " + patient.getLastName());
                System.out.println("Peso: " + patient.getWeight());
                System.out.println("Altura: " + patient.getHeight());
                System.out.println("Edad: " + patient.getAge());
                System.out.println("Sexo: " + patient.getGender());
                System.out.println();
                count++;
            }
        }
    }

    private void deletePatient() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Dar de baja un paciente");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        // Verifica si hay pacientes en la lista
        if (patientManager.getPatientList().isEmpty()) {
            System.out.println("No hay pacientes registrados para eliminar.");
            return;
        }

        // Solicitar el nombre y apellidos del paciente a dar de baja
        System.out.println("Ingrese el nombre del paciente:");
        String name = Kb.nextLine();
        System.out.println("Ingrese los apellidos del paciente:");
        String lastName = Kb.nextLine();

        // Buscar al paciente en la lista por nombre y apellidos
        Patient patientToRemove = patientManager.findPatient(name, lastName);

        if (patientToRemove != null) {
            // Mostrar el nombre completo del paciente
            System.out.println("Paciente a eliminar:" + patientToRemove.getFullName());

            // Pedir confirmación
            System.out.println("¿Está seguro que desea eliminar a este paciente? (S/N)");
            String confirmation = Kb.nextLine().trim().toUpperCase();

            if (confirmation.equals("S")) {
                patientManager.removePatient(patientToRemove); // Eliminar el paciente
                System.out.println("Paciente eliminado exitosamente.");
            } else {
                System.out.println("Operación cancelada.");
            }
        } else {
            System.out.println("No se encontró al paciente.");
        }
    }

}

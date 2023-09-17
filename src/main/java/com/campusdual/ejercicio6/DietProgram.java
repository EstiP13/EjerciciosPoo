package com.campusdual.ejercicio6;

import com.campusdual.ejercicio6.Food;
import com.campusdual.ejercicio6.Kb;
import com.campusdual.ejercicio6.enums.Days;
import com.campusdual.ejercicio6.exceptions.MaxCaloriesReachedException;
import com.campusdual.ejercicio6.exceptions.MaxCarbsReachedException;
import com.campusdual.ejercicio6.exceptions.MaxFatsReachedException;
import com.campusdual.ejercicio6.exceptions.MaxProteinsReachedException;
import com.campusdual.ejercicio6.enums.Gender;


import java.util.*;
import java.io.*; // para cargar excepciones ficheros

public class DietProgram {
    private Diet diet = null;
    private Map<String, Diet> dietList;
    private List<Food> foodList;
    private User user = null;
    private List<User> userList;

    public DietProgram() {
        foodList = new ArrayList<>();
        dietList = new HashMap<>();
        userList = new ArrayList<>();
    }

    public void mainMenu() {
        System.out.println("························································");
        System.out.println("·····             PROGRAMA DE DIETAS               ·····");
        System.out.println("························································");
        Integer option;
        do {
            System.out.println("Seleccione una opción: ");
            System.out.println("1. ACCESO A DIETAS");
            System.out.println("2. ACCESO A PACIENTES");
            System.out.println("3. SALIR");

            option = Kb.getOption(1, 3);
            switch (option) {
                case 1:
                    dietManager();
                    break;
                case 2:
                    userManager();
                    break;
                case 3:
                    exitProgram();
                    break;
            }
        } while (option != 3);
    }

    private void dietManager() {
        System.out.println("·······················································");
        System.out.println("·····             GESTOR DE DIETAS                ·····");
        System.out.println("·······················································");
        Integer option;
        do {
            System.out.println("Seleccione una opción: ");
            System.out.println("1. NUEVA DIETA");
            System.out.println("2. LISTA DE DIETAS");
            System.out.println("3. ELIMINAR DIETA");
            System.out.println("4. VOLVER AL MENÚ PRINCIPAL");

            option = Kb.getOption(1, 4);

            switch (option) {
                case 1:
                    createDiet();
                    break;
                case 2:
                    updateDiet(); //muestra lista de dietas, selecciona y da opción a modificar
                    break;
                case 3:
                    deleteDiet(); //muestra lista, selecciona y borra
                    break;
                case 4:
                    mainMenu();
                    break;
            }
        } while (option != 4);
    }

    private void createDiet() {
        System.out.println("·······················································");
        System.out.println("·····                 NUEVA DIETA                 ·····");
        System.out.println("·······················································");
        System.out.println("Selecciona una opción:");
        System.out.println("1-Dieta sin límite");
        System.out.println("2-Dieta por calorías");
        System.out.println("3-Dieta por macronutrientes");
        System.out.println("4-Dieta por datos personales");
        Integer option = Kb.getOption(1, 4);
        switch (option) {
            case 1:
                System.out.println("Introduzca un nombre para la nueva dieta");
                String noLimitDietName = Kb.nextLine();
                this.diet = new Diet(noLimitDietName);

                System.out.println("La Dieta sin límite: " + " " +
                        noLimitDietName.toUpperCase() + " se ha creado correctamente");
                dietList.put(noLimitDietName, new Diet(noLimitDietName));
                break;
            case 2:
                System.out.println("Introduzca un nombre para la nueva dieta");
                String maxCalDietName = Kb.nextLine();
                System.out.println("Escriba número de calorias");
                Integer calories = Kb.forceNextInt();
                this.diet = new Diet(maxCalDietName, calories);
                System.out.println("La Dieta con " + calories + " calorías máximas " +
                        maxCalDietName.toUpperCase() + " se ha creado correctamente");
                dietList.put(maxCalDietName, new Diet(maxCalDietName, calories));
                break;
            case 3:
                System.out.println("Introduzca un nombre para la nueva dieta");
                String macroDietName = Kb.nextLine();
                System.out.println("·································");
                System.out.println("   Escriba los macronutrientes   ");
                System.out.println("·································");
                System.out.println("Carbohidratos:");
                Integer carbs = Kb.forceNextInt();
                System.out.println("Grasas:");
                Integer fats = Kb.forceNextInt();
                System.out.println("Proteínas:");
                Integer proteins = Kb.forceNextInt();
                this.diet = new Diet(macroDietName, fats, carbs, proteins);

                System.out.println("La Dieta por Macronutrientes: " + macroDietName.toUpperCase() +
                        " se ha creado correctamente. \n" +
                        "Los valores seleccionados son: \n" +
                        " · Carbohidratos:" + carbs + "\n" +
                        " · Grasas:" + fats + " \n" +
                        " · Proteínas:" + proteins);
                dietList.put(macroDietName, new Diet(macroDietName, fats, carbs, proteins));
                break;
            case 4:
                System.out.println("Introduzca un nombre para la nueva dieta");
                String personalNewDiet = Kb.nextLine();
                System.out.println("···················································");
                System.out.println("     Escriba los datos personales del paciente     ");
                System.out.println("···················································");
                System.out.println("Peso:");
                Integer weight = Kb.forceNextInt();
                System.out.println("Altura:");
                Integer height = Kb.forceNextInt();
                System.out.println("Edad:");
                Integer age = Kb.forceNextInt();
                System.out.println("Mujer u Hombre (m / h):");
                String gender = Kb.nextLine();

                this.diet = new Diet(personalNewDiet, Gender.getByString(gender), age, height, weight);
                System.out.println("La Dieta personal: " + personalNewDiet.toUpperCase() +
                        " se ha creado correctamente. " + "El valor máximo de calorías permitidas es de " +
                        this.diet.getMaxCalories() + " calorías");
                dietList.put(personalNewDiet, new Diet(personalNewDiet, Gender.getByString(gender), age, height, weight));
                break;
        }
    }

    private void updateDiet() {
        String selectedDName = getSelectedDiet();
        if (selectedDName == null) {
            System.out.println("Operación cancelada");
            return;
        }
        Diet selectedDiet = dietList.get(selectedDName);
        this.diet = selectedDiet;
        System.out.println("Ha elegido la dieta " + selectedDName.toUpperCase());
        Integer option;
        do {
            System.out.println("···············································");
            System.out.println("·············  Actualiza la dieta  ············");
            System.out.println("···············································");
            System.out.println("1. Modificar nombre de la dieta");
            System.out.println("2. Modificar calorías máximas");
            System.out.println("3. Modificar valores máximos de macronutrientes");
            System.out.println("4. Agregar alimentos");
            System.out.println("5. Salir");

            option = Kb.getOption(1, 5);

            switch (option) {
                case 1:
                    System.out.println("Intoduce un nuevo nombre para la Dieta:");
                    System.out.println("Nombre: ");
                    String newDietName = Kb.nextLine();
                    selectedDiet.setName(newDietName);
                    dietList.put(selectedDiet.getName(), selectedDiet);

                    System.out.println("Nombre dieta actualizado: " + newDietName.toUpperCase());
                    break;
                case 2:
                    System.out.println("Introduce un nº máximo de calorías:");
                    Integer newCalories = Kb.nextInt();
                    selectedDiet.setMaxCalories(newCalories);
                    dietList.put(selectedDiet.getName(), selectedDiet);

                    System.out.println("Nº de calorías actualizado: " + newCalories);
                    break;
                case 3:
                    System.out.println("Modifica la cantidad máxima de macronutrientes:");
                    System.out.println("Carbohidratos: ");
                    Integer newCarbs = Kb.forceNextInt();
                    selectedDiet.setMaxCarbs(newCarbs);
                    System.out.println("Grasas: ");
                    Integer newFats = Kb.forceNextInt();
                    selectedDiet.setMaxFats(newFats);
                    System.out.println("Proteínas: ");
                    Integer newProteins = Kb.forceNextInt();
                    selectedDiet.setMaxProteins(newProteins);
                    System.out.println("Los valores máximos de macronutrientes han sido actualizados");
                    dietList.put(selectedDiet.getName(), selectedDiet);
                    break;
                case 4:
                    System.out.println("Agrega alimentos a la nueva Dieta:");
                    addFoodMenu();
                    break;
                case 5:
                    mainMenu();
                    break;
            }

            showDietDetails();
        } while (option != 5);
    }

    //TODO revisar este método. SOCORRO
    private String getSelectedDiet() {
        if (dietList.size() == 0) {
            System.out.println("No existen dietas a seleccionar");
            return null;
        }
        System.out.println("LISTA DE DIETAS");
        Integer i = 1;
        List<String> availableDiets = new ArrayList<>();
        for (String key : dietList.keySet()) {
            availableDiets.add(key);
            System.out.println((i) + " - " + key.toUpperCase());
            i++;
        }
        System.out.println((i++) + " - Salir");
        System.out.println("······························");
        System.out.println("Seleccione Dieta de la lista: ");
        Integer selectedDiet = Kb.getOption(1, i + 1);
        if (selectedDiet == i + 1) {
            return null;
        }
        return availableDiets.get(selectedDiet - 1);
    }




    // MODIFICAR ESTE MENÚ: GUARDAR Y CARGAR FICHERO
    private void addFoodMenu() {
        if (this.diet == null) {
            System.out.println("Para agregar alimentos necesita iniciar una dieta");
            return;
        }
        Diet selectedDiet = this.diet;
        System.out.println("···································");
        System.out.println("    Agregar Alimentos a la dieta   "); // GESTOR DE ALIMENTOS ??
        System.out.println("···································");
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1- Agregar un nuevo alimento al archivo"); // SE GUARDE EN EL ARCHIVO
        System.out.println("2- Agregar un alimento ya existente");
        System.out.println("3- Listar y modificar alimentos del archivo");
        System.out.println("4- Eliminar un alimento del archivo");
        System.out.println("5- Volver al menú principal");

        Integer option = Kb.getOption(1, 5);
        switch (option) {
            case 1: // addNewFoodToFile()
                System.out.println("···································");
                System.out.println("       Datos de nuevo alimento     ");
                System.out.println("···································");
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
                foodList.add(newFood); // Agregamos el nuevo alimento a foodList.
                saveFoodToFile(foodList); // Actualizamos el fichero con el nuevo alimento.

                this.diet = selectedDiet;
                validateAndAddFoodToDiet(newFood, grams);
                break;

            case 2: // addExistingFoodToDiet();
                foodList = loadFoodFromFile(); // Cargamos la lista de alimentos desde el archivo

                if (foodList.size() == 0) {
                    System.out.println("Para agregar un alimento existente, tienen que existir alimentos previos");
                    return;
                }
                System.out.println("·······················");
                System.out.println("   Escoja un alimento  ");
                System.out.println("·······················");
                Integer i = 1;
                for (Food food : foodList) {
                    System.out.println(i + "- " + food.getName());
                    i++;
                }
                System.out.println(i + "- Cancelar");
                Integer element = Kb.getOption(1, i);
                if (element == i) {
                    System.out.println("Cancelando alimento");
                    return;
                }
                Food storedFood = foodList.get(element - 1);
                System.out.println("indique el número de gramos de " + storedFood.getName());
                Integer foodGrams = Kb.forceNextInt();

                saveFoodToFile(foodList); // Guardamos la lista actualizada con las modificaciones en el alimento

                this.diet = selectedDiet;
                validateAndAddFoodToDiet(storedFood, foodGrams);
                break;

            case 3: // listAndModifyFood();
                foodList = loadFoodFromFile(); // Cargamos la lista de alimentos desde el archivo

                for (int index = 0; index < foodList.size(); index++) {
                    System.out.println((index + 1) + "- " + foodList.get(index).getName());
                }

                System.out.println("Elija el alimento que desea modificar o 0 para volver:");
                int foodIndex = Kb.getOption(0, foodList.size()) - 1;

                if (foodIndex >= 0) {
                    Food foodToModify = foodList.get(foodIndex);

                    // OPCIONES DE MODIFICACION ALIMENTO

                    saveFoodToFile(foodList); // Guardamos los cambios en el archivo
                }

        }
        showDietDetails();
    }


    // CREAR MÉTODOS PARA GUARDAR Y CARGAR ALIMENTOS

    private void saveFoodToFile(List<Food> foods) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("foods.dat"))) {
            oos.writeObject(foods);
        } catch (IOException e) {
            System.out.println("Error al guardar alimento: " + e.getMessage());
        }
    }

    private List<Food> loadFoodFromFile() {
        List<Food> foods = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("foods.dat"))) {
            foods = (List<Food>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar alimento: " + e.getMessage());
        }
        return foods;
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

    private void showDietDetails() {
        if (this.diet != null) {
            System.out.println("········································");
            System.out.println("········  DETALLES DE LA DIETA  ········");
            System.out.println("········································");
            if (this.diet.getMaxCalories() != null) {
                System.out.println("El número máximo de calorías es:" + this.diet.getMaxCalories());
            }
            if (this.diet.getMaxCarbs() != null || this.diet.getMaxFats() != null || this.diet.getMaxProteins() != null) {
                System.out.println("Los valores máximos de macronutrientes son: Carbohidratos:" +
                        this.diet.getMaxCarbs() + " , Grasas:" + this.diet.getMaxFats() +
                        " , Proteinas:" + this.diet.getMaxProteins());
            }
            System.out.println("Número de alimentos de la dieta: " + this.diet.getFoodNumber());
            System.out.println("Calorías: " + this.diet.getTotalCalories());
            System.out.println("Carbohidratos: " + this.diet.getTotalCarbs());
            System.out.println("Grasas: " + this.diet.getTotalFats());
            System.out.println("Proteínas: " + this.diet.getTotalProteins());
            System.out.println("Alimentos de la dieta: " + "\n · " + this.diet.getDietIntakes());
        } else {
            System.out.println("········································");
            System.out.println("        La dieta no esta iniciada       ");
            System.out.println("········································");
        }
    }

    private void deleteDiet() {
        System.out.println("Elija la Dieta que desea eliminar");
        String selected = getSelectedDiet();
        if (selected == null) {
            System.out.println("Operación cancelada");
        } else {
            Diet deleted = dietList.remove(selected);
            if (deleted == null) {
                System.out.println("La Dieta seleccionada no existe");
            } else {
                System.out.println("La Dieta " + selected + " ha sido eliminada correctamente");
            }
        }
    }

    private void userManager() {
        System.out.println("·······················································");
        System.out.println("·····            GESTOR DE PACIENTES              ·····");
        System.out.println("·······················································");
        Integer option;
        do {
            System.out.println("Escriba una opción:");
            System.out.println("·······················································");
            System.out.println("1. NUEVA ALTA PACIENTE");
            System.out.println("2. LISTA DE PACIENTES");
            System.out.println("3. ACTUALIZAR DATOS DE PACIENTE");
            System.out.println("4. ASIGNAR DIETA A PACIENTE"); // COMPLETE MÉTODO. ERA ESTO??
            System.out.println("5. ELIMINAR DATOS DE PACIENTE");
            System.out.println("6. VOLVER AL MENÚ PRINCIPAL");

            option = Kb.getOption(1, 6);

            switch (option) {
                case 1:
                    createUser();
                    break;
                case 2:
                    showUsersDetails(); //userList
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    asignDiet();
                    break;
                case 5:
                    deleteUser();
                    break;
                case 6:
                    mainMenu();
                    break;
            }
        } while (option != 5);
    }

    private void createUser() {
        System.out.println("············································");
        System.out.println("···   REGISTRO DE PACIENTES: NUEVA ALTA  ···");
        System.out.println("············································");
        System.out.println("Introduzca los datos del paciente: ");
        System.out.println("Nombre: ");
        String newName = Kb.nextLine();

        System.out.println("Apellidos ");
        String newSurname = Kb.nextLine();

        System.out.println("Peso: ");
        Integer newWeight = Kb.nextInt();

        System.out.println("Altura: ");
        Integer newHeight = Kb.nextInt();

        System.out.println("Edad: ");
        Integer newAge = Kb.nextInt();

        System.out.println("Mujer u Hombre (m / h):");
        String genderChoice = Kb.nextLine();
        Gender newGender = Gender.getByString(genderChoice);

        if (newGender == null) {
            System.out.println("Entrada no válida. Introduzca una de las opciones disponibles");
            return;
        } else {
            User newUser = new User(newName, newSurname, newWeight, newHeight, newAge, newGender);
            userList.add(newUser);
            System.out.println("Paciente " + newName + " " + newSurname + " ha sido añadido correctamente");
        }
    }

    private void showUsersDetails() {
        System.out.println("············································");
        System.out.println("··········· REGISTRO DE PACIENTES ··········");
        System.out.println("············································");
        Integer i;
        if (userList.isEmpty()) {
            System.out.println("No existen datos. Cree una nueva alta de paciente");
        } else {
            for (i = 0; i < userList.size(); i++) {
                System.out.println((i + 1) + "· " + userList.get(i).getName() +
                        " " + userList.get(i).getSurname());
            }
            System.out.println("Selecciona paciente para acceder a sus datos");
            Integer selectedUser = Kb.nextInt();
            System.out.println("············································");
            System.out.println("············ DATOS DE PACIENTE ·············");
            System.out.println("············································");
            System.out.println(
                    "Nombre y Apellidos: " + userList.get(selectedUser - 1).getName() + " " + userList.get(selectedUser - 1).getSurname() + "\n" +
                            "Edad: " + userList.get(selectedUser - 1).getAge() + " años" + "\n" +
                            "Sexo: " + userList.get(selectedUser - 1).getGender() + "\n" +
                            "Peso: " + userList.get(selectedUser - 1).getWeight() + " Kg" + "\n" +
                            "Altura: " + userList.get(selectedUser - 1).getHeight() + " cm");
            System.out.println("Dietas asignadas: " + userList.get(selectedUser - 1).getUserDiet());
            System.out.println("············································");


        }
    }

    //TODO--> DESARROLLAR ASIGNAR DIETAS

    private User getSelectedUser() {

        Integer i = 1;
        if (userList.size() == 0) {
            System.out.println("No existen datos de pacientes registrados");
        } else {
            System.out.println("Lista de pacientes");
            for (User item : userList) {
                System.out.println(i + ". " + item.getName());
                i++;
            }
            System.out.println((i++) + "." + " Salir");
            System.out.println("·· Seleccione paciente ··");
            System.out.println("·························");

            Integer selectedUser = Kb.getOption(1, i);
            if (selectedUser == i + 1) {
                return null;
            } else {
                return userList.get(selectedUser - 1);
            }
        }
        return null;
    }

    //TODO--> corregir este método, si la entrada x teclado es nula y se pulsa enter, lanza error
    private void updateUser() {
        User selectedUser = getSelectedUser();
        if (selectedUser == null) {
            System.out.println("Operación cancelada");
        } else {
            System.out.println("ACTUALICE DATOS DE PACIENTE");
            System.out.println("Nuevo nombre: ");
            String newName = Kb.nextLine();
            selectedUser.setName(newName);

            System.out.println("Apellidos ");
            String newSurname = Kb.nextLine();
            selectedUser.setSurname(newSurname);

            System.out.println("Peso: ");
            Integer newWeight = Kb.nextInt();
            selectedUser.setWeight(newWeight);

            System.out.println("Altura: ");
            Integer newHeight = Kb.nextInt();
            selectedUser.setHeight(newHeight);

            System.out.println("Edad: ");
            Integer newAge = Kb.nextInt();
            selectedUser.setAge(newAge);

            System.out.println("Mujer u Hombre (m / h):");
            String genderChoice = Kb.nextLine();
            Gender newGender = Gender.getByString(genderChoice);
            selectedUser.setGender(newGender);

            System.out.println("Los datos de " + selectedUser.getName() + " " + selectedUser.getSurname() + " han sido actualizados");
        }
    }



    // ASIGNAR DIETA A UN DÍA Y A UN USUARIO EN CONCRETO
    public void asignDiet() {
        // Comprobamos si hay un usuario seleccionado
        if(user == null) {
            System.out.println("No hay un usuario seleccionado. Por favor, seleccione un usuario primero.");
            return;
        }

        // Seleccionar día
        System.out.println("Seleccione el día para asignar la dieta:");

        // Iteramos sobre todos los valores posibles de Days (lunes, martes, etc.)
        int count = 1;
        for (Days day : Days.values()) {
            System.out.println(count + ". " + day.name());
            count++;
        }
        // Método para obtener una opción del usuario. Restamos 1 porque las listas y arrays
        // se indexan desde el índice 0.
        int selectedDayIndex = Kb.getOption(1, Days.values().length) - 1;
        // Con el índice obtenido sacamos el valor de enum days
        Days selectedDay = Days.values()[selectedDayIndex];

        // Seleccionar dieta
        System.out.println("Ahora, seleccione una dieta:");

        List<Diet> allDiets = new ArrayList<>(dietList.values()); // Cambiarlo para que busque las dietas desde el archivo

        // Ahora iteramos sobre todas las dietas disponibles.
        for (int i = 0; i < allDiets.size(); i++) {
            System.out.println((i + 1) + ". " + allDiets.get(i).getName());
        }

        // Cogemos opción del usuarui y con el índice seleccionamos la dieta correspondiente
        int selectedDietIndex = Kb.getOption(1, allDiets.size()) - 1;
        Diet selectedDiet = allDiets.get(selectedDietIndex);

        // Asignar la dieta al día seleccionado y al usuario
        user.assignDietToDay(selectedDay, selectedDiet);
        System.out.println("Dieta " + selectedDiet.getName() + " asignada para el día " + selectedDay.name());
    }


//TODO--> otra opción. revisar

//        private User getSelectedUser() {
//        if (userList.isEmpty()) {
//            System.out.println("No existen pacientes a seleccionar");
//            return null;
//        }
//        System.out.println("Lista de pacientes");
//        Integer i = 1; //variable para numerar los elementos de la lista (pacientes)
//        for (User element : userList) {
//            System.out.println(i + " · " + element.getName() + " " + element.getSurname());
//            i++;
//        }
//        System.out.println((i + 1) + " · " + "Salir");
//        Integer selectedUser = Kb.getOption(1, i);
//        if (selectedUser == i + 1) {
//            return null;
//        } else {
//            return userList.get(selectedUser - 1);
//        }
//    }

    private void deleteUser() {

        System.out.println("Elija al paciente que desea eliminar");
        User selectedUser = getSelectedUser();
        if (selectedUser == null) {
            System.out.println("Operación cancelada");
        } else {

            if (userList.remove(selectedUser)) {
                System.out.println("Paciente " + selectedUser.getName() + " " + selectedUser.getSurname() + " eliminado correctamente");
            }
        }
    }

    private void exitProgram() {
        System.out.println("Muchas gracias por utilizar nuestro programa. Hasta pronto");
    }
}

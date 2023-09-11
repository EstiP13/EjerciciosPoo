package com.campusdual.diet;

/*
* --Escribe un programa que utilice la clase Dieta y despliegue un menú donde puedas añadir alimentos. El menú tendrá las siguientes opciones.
	-1. Crear/reiniciar dieta: crea o remplaza la dieta inicial
		-a. Sin limite
		-b. Por calorías
		-c. Por macronutrientes
		-d. Por datos personales
	-2. Mostrar información: muestra calorías y macronutrientes de la dieta
	-3. Agregar alimento: agrega un alimento a la dieta actual y añade ese alimento a la lista de alimentos disponible
		-a. Nuevo alimento
		-b. Alimento existente
	-4. Salir
* */

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Diet dieta = null; // Inicializamos la dieta como nula

        boolean salir = false; // Creamos variable salir

        // Mantenemos el menú del programa en ejecución hasta que el usuario elija la opción "Salir"
        while (!salir) {
            System.out.println("Menú:");
            System.out.println("1. Crear/reiniciar dieta");
            System.out.println("2. Mostrar información");
            System.out.println("3. Agregar alimento");
            System.out.println("4. Mostrar lista de alimentos disponibles");
            System.out.println("5. Editar dieta");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la línea en blanco después de nextInt()

            switch (opcion) {
                case 1:
                    System.out.println("a. Sin límite");
                    System.out.println("b. Por calorías");
                    System.out.println("c. Por macronutrientes");
                    System.out.println("d. Por datos personales");
                    System.out.print("Seleccione una opción: ");
                    char subopcion = scanner.nextLine().charAt(0);

                    switch (subopcion) {
                        case 'a':
                            dieta = new Diet();
                            break;
                        case 'b':
                            System.out.print("Ingrese el límite de calorías: ");
                            int maxCalories = scanner.nextInt();
                            scanner.nextLine(); // Consumir la línea en blanco
                            dieta = new Diet(maxCalories);
                            break;
                        case 'c':
                            System.out.print("Ingrese el límite de grasas: ");
                            int maxFats = scanner.nextInt();
                            System.out.print("Ingrese el límite de carbohidratos: ");
                            int maxCarbs = scanner.nextInt();
                            System.out.print("Ingrese el límite de proteínas: ");
                            int maxProtein = scanner.nextInt();
                            scanner.nextLine(); // Consumir la línea en blanco
                            dieta = new Diet(maxFats, maxCarbs, maxProtein);
                            break;
                        case 'd':
                            System.out.print("¿Es mujer? (true/false): ");
                            boolean isWoman = scanner.nextBoolean();
                            System.out.print("Ingrese la edad: ");
                            int age = scanner.nextInt();
                            System.out.print("Ingrese la altura en centímetros: ");
                            int height = scanner.nextInt();
                            System.out.print("Ingrese el peso en kilos: ");
                            int weight = scanner.nextInt();
                            scanner.nextLine(); // Consumir la línea en blanco
                            dieta = new Diet(isWoman, age, height, weight);
                            break;
                        default:
                            System.out.println("Opción no válida.");
                            break;
                    }
                    break;
                case 2:
                    if (dieta != null) {
                        System.out.println("Información de la dieta:");
                        System.out.println("Total de calorías: " + dieta.getTotalCalories());
                        System.out.println("Total de carbohidratos: " + dieta.getTotalCarbs() + " gramos");
                        System.out.println("Total de grasas: " + dieta.getTotalFats() + " gramos");
                        System.out.println("Total de proteínas: " + dieta.getTotalProteins() + " gramos");
                    } else {
                        System.out.println("Primero debe crear una dieta.");
                    }
                    break;
                case 3:
                    if (dieta != null) {
                        // Agregar lógica para agregar alimentos a la dieta aquí
                    } else {
                        System.out.println("Primero debe crear una dieta.");
                    }
                    break;
                case 4:
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
                case 5:
                    if (dieta != null) {
                        // Agregar lógica para editar la dieta aquí
                        System.out.println("Editar dieta:");
                        System.out.println("a. Editar límite de calorías");
                        System.out.println("b. Editar límite de grasas, carbohidratos y proteínas");
                        System.out.print("Seleccione una opción: ");
                        char editarDietaOpcion = scanner.nextLine().charAt(0);
                        switch (editarDietaOpcion) {
                            case 'a':
                                System.out.print("Ingrese el nuevo límite de calorías: ");
                                int nuevoMaxCalories = scanner.nextInt();
                                scanner.nextLine(); // Consumir la línea en blanco
                                dieta.setMaxCalories(nuevoMaxCalories);
                                break;
                            case 'b':
                                System.out.print("Ingrese el nuevo límite de grasas: ");
                                int nuevoMaxFats = scanner.nextInt();
                                System.out.print("Ingrese el nuevo límite de carbohidratos: ");
                                int nuevoMaxCarbs = scanner.nextInt();
                                System.out.print("Ingrese el nuevo límite de proteínas: ");
                                int nuevoMaxProtein = scanner.nextInt();
                                scanner.nextLine(); // Consumir la línea en blanco
                                dieta.setMaxFats(nuevoMaxFats);
                                dieta.setMaxCarbs(nuevoMaxCarbs);
                                dieta.setMaxProtein(nuevoMaxProtein);
                                break;
                            default:
                                System.out.println("Opción no válida.");
                        }
                    } else {
                        System.out.println("Primero debe crear una dieta.");
                    }
                    break;
                case 6:
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;
            }
        }
    }
}
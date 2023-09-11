package com.campusdual.ejercicio4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Kb {
    // Método para obtener un entero desde la entrada estándar
    public static Integer nextInt() {
        Scanner keyboard = new Scanner(System.in);
        String resultString = keyboard.nextLine();
        Integer result = 0;
        try {
            result = Integer.parseInt(resultString);
        } catch (Exception e) {
            throw new InputMismatchException();
        }
        return result;
    }

    // Método para obtener un entero desde la entrada estándar, con manejo de excepciones
    public static Integer forceNextInt() {
        Integer result = null;
        boolean notValid = true;
        do {
            try {
                result = nextInt();
                notValid = false;
            } catch (InputMismatchException e) {
                System.out.println("Es necesario que sea un número");
            }
        } while (notValid);
        return result;
    }

    // Método para obtener una línea de texto desde la entrada estándar
    public static String nextLine() {
        Scanner keyboard = new Scanner(System.in);
        String result = keyboard.nextLine();
        return result;
    }
}
package com.campusdual.ejercicio3;

/* Ejercicio3: Utilizando switch escribir un programa que revise un número y diga si es primo
 * o no (el número tiene que estar entre 1 y 20)
 */

import java.util.Scanner;

public class Ejercicio3 {

    public static void main(String[] args) {
        // Creamos un objeto Scanner llamado "teclado" para leer la entrada del usuario desde
        // la consola.
        Scanner teclado = new Scanner(System.in);

        // Imprimimos un mensaje solicitando al usuario que ingrese un número entre 1 y 20.
        System.out.print("Ingrese un número entre 1 y 20: ");

        // Utilizamos el objeto "teclado" para leer un número entero ingresado por el usuario y
        // lo almacenamos en la variable "numero".
        int numero = teclado.nextInt();

        // Comenzamos una estructura condicional "if" para verificar si el número está dentro
        // del rango [1, 20].
        if (numero >= 1 && numero <= 20) {

            // Iniciamos un bloque "switch" basado en el valor de "numero".
            switch (numero) {
                // Cada "case" representa un número primo en el rango [1, 20].
                case 2:
                case 3:
                case 5:
                case 7:
                case 11:
                case 13:
                case 17:
                case 19:
                    // Si "numero" coincide con uno de los casos, mostramos un mensaje indicando
                    // que es primo.
                    System.out.println(numero + " es primo");
                    break; // Salimos del "switch" para evitar que se ejecuten otros casos.
                default:
                    // Si "numero" no coincide con ninguno de los casos anteriores, mostramos un
                    // mensaje indicando que no es primo.
                    System.out.println(numero + " no es primo");
            }
        } else {
            // Si el número no está dentro del rango [1, 20], mostramos un mensaje de error.
            System.out.println("El número debe estar entre 1 y 20.");
        }

        // Cerramos el objeto Scanner para liberar recursos.
        teclado.close();
    }
}


/* Otra forma de hacerlo:

import java.util.Scanner;

public class Ejercicio3 {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.print("Ingrese un número entre 1 y 20: ");
        int numero = teclado.nextInt();

        if (numero >= 1 && numero <= 20) {
            boolean esPrimo = true;

            switch (numero) {
                case 1:
                case 0:
                    esPrimo = false;
                    break;
                case 2:
                case 3:
                case 5:
                case 7:
                case 11:
                case 13:
                case 17:
                case 19:
                    esPrimo = true;
                    break;
                default:
                    if (numero % 2 == 0) {
                        esPrimo = false;
                    } else {
                        for (int i = 3; i <= Math.sqrt(numero); i += 2) {
                            if (numero % i == 0) {
                                esPrimo = false;
                                break;
                            }
                        }
                    }
            }

            if (esPrimo) {
                System.out.println(numero + " es primo");
            } else {
                System.out.println(numero + " no es primo");
            }
        } else {
            System.out.println("El número debe estar entre 1 y 20.");
        }
    }
}

*/
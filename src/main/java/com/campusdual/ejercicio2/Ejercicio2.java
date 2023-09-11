package com.campusdual.ejercicio2;

/** Ejercicio 2: programa para poner el año y que nos diga si es bisiesto o no **/

/**
public class Ejercicio2 {

    public static void main(String[] args) {
        int ano = 2023; // Reemplazar por el año a comprobar

        // Utilizamos un if
        if ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)) {
            System.out.println(ano + " es bisiesto");
        } else {
            System.out.println(ano + " no es bisiesto");
        }
    }
}
**/


import java.util.Scanner;

public class Ejercicio2 {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        System.out.print("Ingresa un año: ");

        int ano = teclado.nextInt();

        // Con if vemos si el año es bisiesto:
        if ((ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0)) {
            System.out.println(ano + " es bisiesto");
        } else {
            System.out.println(ano + " no es bisiesto");
        }
    }
}








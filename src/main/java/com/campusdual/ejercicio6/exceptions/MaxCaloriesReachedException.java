package com.campusdual.ejercicio6.exceptions;

import com.campusdual.ejercicio5.exceptions.MaxValuedReachedException;

public class MaxCaloriesReachedException extends MaxValuedReachedException {

    public MaxCaloriesReachedException() {
        super("Max calories reached for the actual diet");
    }
}

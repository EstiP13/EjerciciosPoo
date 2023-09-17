package com.campusdual.ejercicio6.exceptions;

import com.campusdual.ejercicio5.exceptions.MaxValuedReachedException;

public class MaxFatsReachedException extends MaxValuedReachedException {

    public MaxFatsReachedException() {
        super("Max fats reached for the actual diet");
    }
}

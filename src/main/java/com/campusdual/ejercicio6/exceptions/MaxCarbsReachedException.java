package com.campusdual.ejercicio6.exceptions;

import com.campusdual.ejercicio5.exceptions.MaxValuedReachedException;

public class MaxCarbsReachedException extends MaxValuedReachedException {

    public MaxCarbsReachedException() {
        super("Max carbs reached for the actual diet");
    }
}

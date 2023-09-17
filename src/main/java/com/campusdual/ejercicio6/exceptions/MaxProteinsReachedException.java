package com.campusdual.ejercicio6.exceptions;

import com.campusdual.ejercicio5.exceptions.MaxValuedReachedException;

public class MaxProteinsReachedException extends MaxValuedReachedException {

    public MaxProteinsReachedException() {
        super("Max proteins reached for the actual diet");
    }
}

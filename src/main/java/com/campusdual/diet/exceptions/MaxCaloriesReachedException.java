package com.campusdual.diet.exceptions;

public class MaxCaloriesReachedException extends MaxValuedReachedException {

    public MaxCaloriesReachedException() {
        super("Max calories reached for the actual diet");
    }
}

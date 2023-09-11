package com.campusdual.diet;

import com.campusdual.diet.Food;

public class FoodUtility {

        public static void main(String[] args) {
            com.campusdual.diet.Food zanahoria = new Food(12,0,1);
            System.out.println("Calorias de 100g de Zanahoria:"+zanahoria.getCalories(100));
        }
}

package com.campusdual.ejercicio4;

import com.campusdual.ejemplos.alimentos.Food;
import com.campusdual.ejercicio4.exceptions.*;

import java.util.ArrayList;
import java.util.List;

/*
* Escribe una clase dieta, que teniendo en cuenta una serie de alimentos, vaya sumando cantidades en gramos y calcule cuentas calorías, carbohidratos, proteinas y grasas genera la ingesta
La clase dieta tiene que tener las siguientes funcionalidades:
	-Diet(): crea una dieta sin límite de calorías
	-Diet(Integer maxCalories): crea una dieta con un máximo de calorías, en cuanto se supera ese máximo cuando se adjunta un alimento se despliega un error
	-Diet(Integer maxFats, Integer maxCarbs, Integer maxProtein): crea una dieta con un máximo de estos tres valores, si se supera alguno de ellos cuando se adjunta un alimento se indicara que macronutriente y desplegará un error
	-Diet(Boolean women, Integer age, Integer height, Integer weight): Calcula el metabolismo basal de la persona y lo asigna como máximo de calorías en la dieta
		--CALCULAR METABOLISMO BASAL
			E = edad
			A = altura en centímetros
			P = peso en kilos

			Fórmula Hombres: TMB = 10P + 6,25A – 5E + 5
			Fórmula Mujeres: TMB = 10P + 6,25A – 5E – 161
	-addFood(Food,Integer): agrega un alimento y una cantidad en gramos
	-getTotalCalories(): devuelve el total de calorías
	-getTotalCarbs(): devuelve el total de carbohidratos
	-getTotalFats(): devuelve el total de grasas
	-getTotalProteins(): devuelve el total de proteinas
*
* */
public class Diet {
    // Constantes para identificar los tipos de errores
    public static final String OK = "OK";
    public static final String MAX_CALORIES_REBASE = "MAX_CALORIES_REBASE";
    public static final String MAX_CARBS_REBASE = "MAX_CARBS_REBASE";
    public static final String MAX_FATS_REBASE = "MAX_FATS_REBASE";
    public static final String MAX_PROTEINS_REBASE = "MAX_PROTEINS_REBASE";

    // Propiedades para almacenar los límites nutricionales y las ingestas
    private Integer maxCalories;
    private Integer maxCarbs;
    private Integer maxFats;
    private Integer maxProteins;
    private List<Intake> intakes;

    // Constructor predeterminado que inicializa la lista de ingestas
    public Diet(){
        this.intakes = new ArrayList<>();
    }

    // Constructor para crear una dieta basada en un límite de calorías
    public Diet(Integer maxCalories){
        this.maxCalories=maxCalories;
        this.intakes = new ArrayList<>();
    }

    // Constructor para crear una dieta basada en límites de grasas, carbohidratos y proteínas
    public Diet(Integer maxFats, Integer maxCarbs, Integer maxProteins){
        this.maxCarbs=maxCarbs;
        this.maxFats=maxFats;
        this.maxProteins=maxProteins;
        this.intakes = new ArrayList<>();
    }

    // Constructor para crear una dieta basada en datos personales
    public Diet(Boolean women, Integer age, Integer height, Integer weight){
        if(women){
            maxCalories = (int) ((10*weight) + (6.25*height))-(5*age)-161;
        }else{
            maxCalories = (int) ((10*weight) + (6.25*height))-(5*age)+5;
        }
        this.intakes = new ArrayList<>();
    }

    // Método para agregar un alimento a la dieta
    public void addFood(Food food, Integer grams) throws MaxValuedReachedException {
        Intake intake = new Intake(food, grams);
        // Validar la ingestión y lanzar excepciones si se superan los límites
        String validation = isValidIntake(intake);
        if(OK.equalsIgnoreCase(validation)){
            intakes.add(intake);
        }else{
            if(MAX_CALORIES_REBASE.equalsIgnoreCase(validation)){
                throw new MaxCaloriesReachedException();
            }
            if(MAX_CARBS_REBASE.equalsIgnoreCase(validation)){
                throw new MaxCarbsReachedException();
            }
            if(MAX_FATS_REBASE.equalsIgnoreCase(validation)){
                throw new MaxFatsReachedException();
            }
            if(MAX_PROTEINS_REBASE.equalsIgnoreCase(validation)){
                throw new MaxProteinsReachedException();
            }
        }
    }

    // Método privado para validar una ingestión
    private String isValidIntake(Intake intake){
        Integer actualCalories = getTotalCalories();
        if(this.maxCalories != null && this.maxCalories < (actualCalories + intake.calculatedCalories())){
            return MAX_CALORIES_REBASE;
        }
        Integer actualCarbs = getTotalCarbs();
        if(this.maxCarbs != null && this.maxCarbs < actualCarbs + intake.calculatedCarbos()){
            return MAX_CARBS_REBASE;
        }
        Integer actualFats = getTotalFats();
        if(this.maxFats != null && this.maxFats < actualFats + intake.calculatedFats()){
            return MAX_FATS_REBASE;
        }
        Integer actualProteins = getTotalProteins();
        if(this.maxProteins != null && this.maxProteins < actualProteins + intake.calculatedProteins()){
            return MAX_PROTEINS_REBASE;
        }
        return OK;
    }

    // Métodos para calcular el total de calorías, carbohidratos, grasas y proteínas en la dieta
    public Integer getTotalCalories(){
        Integer totalCalories = 0;
        for(Intake intake:intakes){
            totalCalories = totalCalories+ intake.calculatedCalories();
        }
        return totalCalories;
    }

    public Integer getTotalCarbs(){
        Integer totalCarbs = 0;
        for(Intake intake:intakes){
            totalCarbs = totalCarbs + intake.calculatedCarbos();
        }
        return totalCarbs;
    }

    public Integer getTotalFats(){
        Integer totalFats = 0;
        for(Intake intake:intakes){
            totalFats = totalFats + intake.calculatedFats();
        }
        return totalFats;
    }

    public Integer getTotalProteins(){
        Integer totalProtein = 0;
        for(Intake intake: intakes){
            totalProtein = totalProtein + intake.calculatedProteins();
        }
        return totalProtein;
    }

    // Otros métodos para obtener información sobre la dieta
    public Integer getFoodNumber(){
        return intakes.size();
    }

    public Integer getMaxCalories(){
        return maxCalories;
    }

    public void setMaxCalories(Integer maxCalories) {
        this.maxCalories = maxCalories;
    }

    public Integer getMaxCarbs() {
        return maxCarbs;
    }

    public void setMaxCarbs(Integer maxCarbs) {
        this.maxCarbs = maxCarbs;
    }

    public Integer getMaxFats() {
        return maxFats;
    }

    public void setMaxFats(Integer maxFats) {
        this.maxFats = maxFats;
    }

    public Integer getMaxProteins() {
        return maxProteins;
    }

    public void setMaxProteins(Integer maxProteins) {
        this.maxProteins = maxProteins;
    }

    public List<Intake> getIntakes() {
        return intakes;
    }

    public void setIntakes(List<Intake> intakes) {
        this.intakes = intakes;
    }

    // Método para obtener una representación de las ingestas en la dieta
    public String getDietIntakes(){
        String result = "";
        boolean first=true;
        for(Intake intake:intakes){
            if(first){
                first = false;
                result = intake.getName()+":"+intake.getGrams();
            }else{
                result = result + ", "+intake.getName()+":"+intake.getGrams();
            }
        }
        return result;
    }
}
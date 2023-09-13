package com.campusdual.ejercicio5;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


// Creamos la clase Patient para gestionar los datos de los pacientes
public class Patient {

    private String name;
    private String lastName;
    private int age;
    private int height;
    private int weight;
    private char gender; // 'm' para hombre, 'h' para mujer
    private Map<DayofWeek, Diet> weeklyDiets; // Días de la semana

    public Patient(String name, String lastName, int age, int height, int weight, char gender) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
        this.weeklyDiets = new HashMap<>();
    }

    // Getters y setters para los atributos de los pacientes

    public String getName() {
        return name;
    }

    public String getFullName() {
        return name + " " + lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public static Patient findPatient(List<Patient> patients, String name, String lastName) {
        for (Patient patient : patients) {
            if (patient.getName().equals(name) && patient.getLastName().equals(lastName)) {
                return patient;
            }
        }
        return null;  // Si no se encuentra el paciente, se devuelve null
    }

    public void assignDiet(Diet diet, DayofWeek dayOfWeek) {
        weeklyDiets.put(dayOfWeek, diet);
        System.out.println("Dieta asignada con éxito al día " + dayOfWeek.getDisplayName());
    }

}
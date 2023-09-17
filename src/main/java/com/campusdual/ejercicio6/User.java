package com.campusdual.ejercicio6;

import com.campusdual.ejercicio6.Diet;
import com.campusdual.ejercicio6.enums.Days;
import com.campusdual.ejercicio6.enums.Gender;

import java.util.List;
import java.util.Map;

public class User {
    private String name;
    private String surname;
    private Integer weight;
    private Integer height;
    private Integer age;
    private Gender gender;
    private Map<String, Diet> userDiet;
    private List<User> userList;

    public User(String name, String surname, Integer weight, Integer height, Integer age, Gender gender) {
        this.name = name;
        this.surname = surname;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
    }

    public User(Map<String, Diet> userDiet) {
        this.userDiet = userDiet;
    }



    // ASIGNAR DIETA A DÍA
    public void assignDietToDay(Days day, Diet diet) {
        userDiet.put(day.toString(), diet);
    }


    //getters y setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Map<String, Diet> getUserDiet() {
        return userDiet;
    }

    public void setUserDiet(Map<String, Diet> userDiet) {
        this.userDiet = userDiet;
    }
}

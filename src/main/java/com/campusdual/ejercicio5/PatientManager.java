package com.campusdual.ejercicio5;

import com.campusdual.ejercicio5.model.Patient;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


// Creamos la clase PatientManager para gestionar los pacientes
public class PatientManager {

    private List<Patient> patientList;

    public PatientManager() {
        patientList = new ArrayList<>();
    }

    // Método para agregar un paciente a la lista
    public void addPatient(Patient patient) {
        patientList.add(patient);
    }

    // Método para eliminar un paciente de la lista
    public void removePatient(Patient patient) {
        patientList.remove(patient);
    }

    // Método para obtener todos los pacientes en la lista
    public List<Patient> getPatientList() {
        return patientList;
    }

    // Método para buscar un paciente por nombre y apellidos
    public Patient findPatient(String name, String lastName) {
        for (Patient patient : patientList) {
            if (patient.getName().equals(name) && patient.getLastName().equals(lastName)) {
                return patient;
            }
        }
        return null; // Retorna null si el paciente no se encuentra
    }

    // Método para actualizar la información de un paciente existente
    public void updatePatient(Patient patient, String name, String lastName, int weight, int height, int age, char gender) {
        // Verifica si el paciente existe en la lista
        if (patientList.contains(patient)) {
            // Actualiza la información del paciente
            patient.setName(name);
            patient.setLastName(lastName);
            patient.setWeight(weight);
            patient.setHeight(height);
            patient.setAge(age);
            patient.setGender(gender);
        } else {
            // Si el paciente no existe
            System.out.println("El paciente no se encuentra en la lista.");
        }
    }
}



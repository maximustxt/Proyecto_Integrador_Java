package com.API.Expenses.DTO;

import com.API.Expenses.models.Expenses;

import java.util.List;

public class UsersDTO {
    private Long id;
    private String nombre;
    private String gmail;
    private int edad;

    private List<Expenses> expenses;

    public UsersDTO() {
    }

    public UsersDTO(Long id, String nombre, String gmail, int edad , List<Expenses> expenses ) {
        this.id = id;
        this.nombre = nombre;
        this.gmail = gmail;
        this.edad = edad;
        this.expenses = expenses;
    }


    public List<Expenses> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expenses> expenses) {
        this.expenses = expenses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}

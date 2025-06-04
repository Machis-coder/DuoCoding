package com.codingtrainers.duocoding.entities;

public class Subject {
    Long id;
    String name;
    String description;
    String fechaCreacion;
    String ejercicios;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(String ejercicios) {
        this.ejercicios = ejercicios;
    }
    
}

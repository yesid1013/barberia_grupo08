package com.grupo08.barberia.Entity;

public class Mensaje {
    private String saludo;
    private String nombre;

    

    public Mensaje() {
    }

    public Mensaje(String saludo, String nombre) {
        this.saludo = saludo;
        this.nombre = nombre;
    }

    public String getSaludo() {
        return saludo;
    }
    public void setSaludo(String saludo) {
        this.saludo = saludo;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}

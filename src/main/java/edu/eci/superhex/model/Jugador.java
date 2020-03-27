package edu.eci.superhex.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Jugador {
    @Id
    @Column(name = "nombre")
    private String name;

    public Jugador(){

    }

    public Jugador(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
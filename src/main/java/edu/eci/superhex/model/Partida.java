package edu.eci.superhex.model;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "partida")
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column
    private int etapa;
    @Column(name = "fechainicio")
    private Timestamp fechaInicio = null;
    @Column(name = "fechafin")
    private Timestamp fechaFin = null;
    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="ganador")
    private Jugador ganador;

    public Partida(){

    }

    public Partida(int etapa,Timestamp fechaInicio,Timestamp fechaFin,Jugador ganador){
        this.etapa = etapa;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.ganador = ganador;
    }

    public int getEtapa() {
        return etapa;
    }

    public void setEtapa(int etapa) {
        this.etapa = etapa;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Timestamp getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }
}
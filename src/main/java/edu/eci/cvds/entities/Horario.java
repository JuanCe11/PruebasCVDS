package edu.eci.cvds.entities;

import java.io.Serializable;
import java.sql.Timestamp;

public class Horario implements Serializable {

    private int id;
    private Recurso recurso;
    private Timestamp horaInicio;
    private Timestamp horaFin;


    /**
     * Constructor vacio de la clase horario
     */
    public Horario(){super();}

    /**
     * Constructor de la clase horario
     * @param id el id del horario
     * @param recurso El recurso del horario
     * @param horaInicio Hora de inicio
     * @param horaFin Hora de fin
     */
    public Horario(int id,Recurso recurso,Timestamp horaInicio, Timestamp horaFin){
        this.id=id;
        this.recurso=recurso;
        this.horaFin=horaFin;
        this.horaInicio=horaInicio;
    }

    /**
     * Getter del recurso
     * @return recurso del horario
     */
    public Recurso getRecurso() {
        return recurso;
    }

    /**
     * Setter del recurso
     * @param recurso El nuevo recurso
     */
    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    /**
     * Setter de la Hora final del horario
     * @return la hora final del horario
     */
    public Timestamp getHoraFin() {
        return horaFin;
    }

    /**
     * Setter de la hora inicial del horario
     * @return Hora inical del horario
     */
    public Timestamp getHoraInicio() {
        return horaInicio;
    }

    /**
     * Setter de la hora final del recurso
     * @param horaFin La nueva hora final
     */
    public void setHoraFin(Timestamp horaFin) {
        this.horaFin = horaFin;
    }

    /**
     * Setter de la hora inicial del recurso
     * @param horaInicio La nueva hora inicial
     */
    public void setHoraInicio(Timestamp horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * Getter del id
     * @return el id del horario
     */
    public int getId() {
        return id;
    }

    /**
     * Setter dle id
     * @param id el nuevo id
     */
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return horaInicio.toString()+" "+horaFin.toString();
    }
}

package edu.eci.cvds.entities;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

public class Reserva implements Serializable {
    private int id;
    private Timestamp fechaInicio;
    private Timestamp fechaFin;
    private Recurso recurso;
    private Usuario usuario;

    public Reserva(){super();}

    /**
     * Constructor de la clase RecursoRentado
     * @param id el id del recurso rentado
     * @param fechaInicio fecha de inicio de la renta
     * @param fechaFin fecha de fin de la renta
     * @param recurso el recurso rentado
     */
    public Reserva(int id,Timestamp fechaInicio,Timestamp fechaFin,Recurso recurso,Usuario usuario){
        this.id=id;
        this.fechaInicio=fechaInicio;
        this.fechaFin=fechaFin;
        this.recurso=recurso;
        this.usuario=usuario;
    }

    /**
     * Setter del id
     * @param id el nuevo id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter del id
     * @return el id
     */
    public int getId() {
        return id;
    }

    /**
     * Getter de la fecha para finalizar la renta
     * @return la fecha en la que finaliza la renta
     */
    public Timestamp getFechaFin() {
        return fechaFin;
    }

    /**
     * Getter de la fehca de inicio de la renta
     * @return fecha de inico de la renta
     */
    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Getter del recuros
     * @return el recurso
     */
    public Recurso getRecurso() {
        return recurso;
    }

    /**
     * Setter de la fecha final de renta
     * @param fechaFin La nueva fecha final de renta
     */
    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Setter de la fecha inicial de renta
     * @param fechaInicio Nueva fecha inicial de renta
     */
    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Setter del recurso
     * @param recurso el nuevo recurso
     */
    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaIniDate(){
        Date date=new Date(fechaInicio.getYear(),fechaInicio.getMonth(),fechaInicio.getDay());
        return date;
    }

    public Date getFechaFinDate(){
        Date date=new Date(fechaFin.getYear(),fechaFin.getMonth(),fechaFin.getDay());
        return date;
    }

    @Override
    public String toString() {
        return Integer.toString(this.id)+" "+this.recurso.toString()+" "+this.fechaInicio.toString()+" "+this.fechaFin.toString();
    }
}

package edu.eci.cvds.entities;

import java.sql.Date;

public class RecursoRentado {
    private int id;
    private Date fechaInicioRenta;
    private Date fechaFinRenta;
    private Recurso recurso;

    /**
     * Constructor de la clase RecursoRentado
     * @param id el id del recurso rentado
     * @param fechaInicioRenta fecha de inicio de la renta
     * @param fechaFinRenta fecha de fin de la renta
     * @param recurso el recurso rentado
     */
    public RecursoRentado(int id,Date fechaInicioRenta,Date fechaFinRenta,Recurso recurso){
        this.id=id;
        this.fechaInicioRenta=fechaInicioRenta;
        this.fechaFinRenta=fechaFinRenta;
        this.recurso=recurso;
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
    public Date getFechaFinRenta() {
        return fechaFinRenta;
    }

    /**
     * Getter de la fehca de inicio de la renta
     * @return fecha de inico de la renta
     */
    public Date getFechaInicioRenta() {
        return fechaInicioRenta;
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
     * @param fechaFinRenta La nueva fecha final de renta
     */
    public void setFechaFinRenta(Date fechaFinRenta) {
        this.fechaFinRenta = fechaFinRenta;
    }

    /**
     * Setter de la fecha inicial de renta
     * @param fechaInicioRenta Nueva fecha inicial de renta
     */
    public void setFechaInicioRenta(Date fechaInicioRenta) {
        this.fechaInicioRenta = fechaInicioRenta;
    }

    /**
     * Setter del recurso
     * @param recurso el nuevo recurso
     */
    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    @Override
    public String toString() {
        return Integer.toString(this.id)+" "+this.recurso.toString()+" "+this.fechaInicioRenta.toString()+" "+this.fechaFinRenta.toString();
    }
}

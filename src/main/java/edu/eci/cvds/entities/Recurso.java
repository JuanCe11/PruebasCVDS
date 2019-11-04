package edu.eci.cvds.entities;

import java.io.Serializable;

public class Recurso implements Serializable {
    private String nombre;
    private String ubicacion;
    private TipoRecurso tipoRecurso;
    private String identificadorInterno;
    private Integer capacidad;
    private boolean estado;

    /**
     * Constructor de la clase Recurso
     * @param nombre El nombre del recurso
     * @param ubicacion La ubicacion del recurso
     * @param tipoRecurso El tipo del recurso
     * @param identificadorInterno El identificador interno
     * @param capacidad La capacidad del recurso
     */
    public Recurso(String nombre,String ubicacion,TipoRecurso tipoRecurso,boolean estado,String identificadorInterno,Integer capacidad){
        this.nombre=nombre;
        this.ubicacion=ubicacion;
        this.tipoRecurso=tipoRecurso;
        this.identificadorInterno=identificadorInterno;
        this.capacidad=capacidad;
        this.estado=estado;
    }

    /**
     * Getter de la capacidad
     * @return la capacidad
     */
    public Integer getCapacidad() {
        return capacidad;
    }

    /**
     * Getter del identificado interno
     * @return el identificador interno
     */
    public String getIdentificadorInterno() {
        return identificadorInterno;
    }

    /**
     * Getter del nombre
     * @return el nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter de la ubicacion
     * @return la ubicacion
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * Getter del tipo del recurso
     * @return el tipo del recurso
     */
    public TipoRecurso getTipoRecurso() {
        return tipoRecurso;
    }

    /**
     * Setter de la capacidad
     * @param capacidad la nueva capacidad
     */
    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    /**
     * Setter del identificador interno
     * @param identificadorInterno el nuevo identificador interno
     */
    public void setIdentificadorInterno(String identificadorInterno) {
        this.identificadorInterno = identificadorInterno;
    }

    /**
     * Setter del nombre
     * @param nombre el nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Setter del tipo de recurso
     * @param tipoRecurso el tipo del recurso
     */
    public void setTipoRecurso(TipoRecurso tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    /**
     * Setter de la ubicacion
     * @param ubicacion la nueva ubicacion
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * Setter del estado
     * @param estado el nuevo estado
     */
    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    /**
     * Obtenedor del estado
     * @return el estado
     */
    public boolean isEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return this.nombre+" "+this.ubicacion+" "+this.identificadorInterno+" "+this.tipoRecurso.toString()+" "+this.capacidad.toString();
    }
}

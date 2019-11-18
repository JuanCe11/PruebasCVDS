package edu.eci.cvds.entities;
import java.io.Serializable;

public class Usuario implements Serializable{

    private String nombre;
    private String passw;
    private String tipo;
    private String id;
    private Integer carnet;


    public Usuario() { super(); }

    public Usuario(String nombre,String passw,String tipo,String id,Integer carnet) {
        this.nombre = nombre;
        this.passw=passw;
        this.tipo = tipo;
        this.id=id;
        this.carnet=carnet;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Integer getCarnet() {
        return carnet;
    }

    public void setCarnet(Integer carnet) {
        this.carnet = carnet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassw() {
        return passw;
    }

    public void setPassw(String passw) {
        this.passw = passw;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return id+" "+nombre+" "+tipo;
    }
}

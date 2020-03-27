package edu.eci.superhex.persistence;

public class SuperHexPersistenceException extends Exception{
    public static String SALA_YA_EXISTE = "Ya existe una sala con este nombre";
    public static String SALA_NO_EXISTE = "No existe una sala con este nombre";

    public SuperHexPersistenceException (String message){
        super(message);
    }
}

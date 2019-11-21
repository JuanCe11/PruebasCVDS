package edu.eci.cvds.services;

public class LibraryServicesException extends Exception {
    public static final String CAPACIDAD_NEGATIVA="La capacidad no puede ser negativa";
    public static final String RECURSO_RESERVADO_EN_HORA="El recurso ya se encuentra reservado a esa hora";
    public static  final String RESERVA_MAYOR_A_DOS_HORAS="Una reserva no puede ser mayor a dos horas";

    /**
     * Constructor de la clase LibraryServicesException
     * @param Message String el mensaje del error
     */
    public LibraryServicesException(String Message){
        super(Message);
    }
}

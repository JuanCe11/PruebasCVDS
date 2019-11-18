package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.services.LibraryServicesException;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public interface DaoReserva {
    void reservarRecurso(Recurso recurso, Usuario usuario, Timestamp fechaIni, Timestamp fechaFin) throws LibraryServicesException;

    List<Reserva> consultarReservasUsuario(String id) throws LibraryServicesException;

    List<Reserva> consultarReservas() throws LibraryServicesException;

    Reserva consultarReservaRecurso(Recurso recurso)throws  LibraryServicesException;
}

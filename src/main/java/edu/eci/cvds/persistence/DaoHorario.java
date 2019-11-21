package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.Horario;
import edu.eci.cvds.services.LibraryServicesException;

import java.util.List;

public interface DaoHorario {
    /**
     * Da una lista de todos los horarios
     * @return Lista de todos los horarios
     * @throws LibraryServicesException
     */
    List<Horario> horarios() throws LibraryServicesException;
}

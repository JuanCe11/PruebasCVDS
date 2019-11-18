package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.Horario;
import edu.eci.cvds.services.LibraryServicesException;

import java.util.List;

public interface DaoHorario {
    List<Horario> horarios() throws LibraryServicesException;
}

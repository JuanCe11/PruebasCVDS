package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.TipoRecurso;
import edu.eci.cvds.services.LibraryServicesException;

public interface DaoTipoRecurso {
    void registrarTipoRecurso(TipoRecurso tipoRecurso) throws LibraryServicesException;
}

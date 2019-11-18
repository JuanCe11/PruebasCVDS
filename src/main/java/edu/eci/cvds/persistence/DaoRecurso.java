package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.services.LibraryServicesException;

import java.util.List;

public interface DaoRecurso {
    public void registraNuevoRecurso(Recurso recurso) throws LibraryServicesException;
    public void eliminarPermanente(Recurso recurso) throws LibraryServicesException;
    public void eliminarTemporal(Recurso recurso) throws LibraryServicesException;
    public void volverAAdmitirRecurso(Recurso recurso) throws LibraryServicesException;
    public List<Recurso> consultarRecursos() throws LibraryServicesException;
    Recurso consultarRecurso(int id) throws LibraryServicesException;
    List<Recurso> consultarRecursosAdmin() throws LibraryServicesException;
}

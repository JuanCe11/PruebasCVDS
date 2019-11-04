package edu.eci.cvds.persistence;

import edu.eci.cvds.entities.Recurso;

import java.util.List;

public interface DaoRecurso {
    public void registraNuevoRecurso(Recurso recurso);
    public void eliminarPermanente(Recurso recurso);
    public void eliminarTemporal(Recurso recurso);
    public void volverAAdmitirRecurso(Recurso recurso);
    public List<Recurso> consultarRecursos();
}

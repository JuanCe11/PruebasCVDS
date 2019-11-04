package edu.eci.cvds.services;

import edu.eci.cvds.entities.Recurso;

public interface AdministratorServicesLibrary {
    public void registrarRecurso(Recurso recurso);
    public void eliminarUnRecursoPermanente(Recurso recurso);
    public void eliminarUnRecursoTemporal(Recurso recurso);
    public void volverAAdmitirElRecurso(Recurso recurso);
}

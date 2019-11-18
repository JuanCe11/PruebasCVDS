package edu.eci.cvds.services;

import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.entities.TipoRecurso;
import edu.eci.cvds.entities.Usuario;

import java.sql.Timestamp;
import java.util.List;

public interface AdministratorServicesLibrary {
    /**
     * Registra un nuevo tipo de recurso
     * @param tipoRecurso el nuevo tipo de recurso
     */
    public void registrarTipoRecurso(TipoRecurso tipoRecurso) throws LibraryServicesException;

    /**
     * Regustra un recurso
     * @param recurso el recurso a registrar
     */
    public void registrarRecurso(Recurso recurso) throws LibraryServicesException;

    /**
     * Elimina un recurso de forma permanente
     * @param recurso el recurso a eliminar
     */
    public void eliminarUnRecursoPermanente(Recurso recurso) throws LibraryServicesException;

    /**
     * Elimina un recurso de forma temporal
     * @param recurso el recurso
     */
    public void eliminarUnRecursoTemporal(Recurso recurso) throws LibraryServicesException;

    /**
     * Vuelve a admitir un recurso
     * @param recurso el recurso
     */
    public void volverAAdmitirElRecurso(Recurso recurso) throws LibraryServicesException;

    /**
     * Consulta todos los recursos esten o no disponibles
     * @return Lista de todos los recursos
     * @throws LibraryServicesException
     */
    List<Recurso> consultarRecursosAdmin() throws LibraryServicesException;

    /**
     * Permite a un administrador reservar un recurso
     * @param recurso El recurso a reservar
     * @param usuario El usuario del recurso
     * @param fechaIni La fecha de inicio
     * @param fechaFin La fecha de fin
     * @throws LibraryServicesException
     */
    void reservarRecurso(Recurso recurso, Usuario usuario, Timestamp fechaIni, Timestamp fechaFin) throws LibraryServicesException;
}

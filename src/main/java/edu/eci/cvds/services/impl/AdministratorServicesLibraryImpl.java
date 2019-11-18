package edu.eci.cvds.services.impl;

import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.entities.TipoRecurso;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.persistence.DaoRecurso;
import edu.eci.cvds.persistence.DaoReserva;
import edu.eci.cvds.persistence.DaoTipoRecurso;
import edu.eci.cvds.services.AdministratorServicesLibrary;
import edu.eci.cvds.services.LibraryServicesException;
import edu.eci.cvds.services.ServicesLibraryFactory;

import javax.inject.Inject;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class AdministratorServicesLibraryImpl implements AdministratorServicesLibrary {

    @Inject
    private DaoRecurso recursoDao;
    @Inject
    private DaoTipoRecurso tipoRecursoDao;
    @Inject
    private DaoReserva reservaDao;

    @Override
    public void registrarTipoRecurso(TipoRecurso tipoRecurso) throws LibraryServicesException {
        tipoRecursoDao.registrarTipoRecurso(tipoRecurso);
    }

    @Override
    public void registrarRecurso(Recurso recurso) throws LibraryServicesException {
        recursoDao.registraNuevoRecurso(recurso);
    }

    @Override
    public void eliminarUnRecursoPermanente(Recurso recurso) throws LibraryServicesException {
        recursoDao.eliminarPermanente(recurso);
    }

    @Override
    public void eliminarUnRecursoTemporal(Recurso recurso) throws LibraryServicesException {
        recursoDao.eliminarTemporal(recurso);
    }

    @Override
    public void volverAAdmitirElRecurso(Recurso recurso) throws LibraryServicesException {
        recursoDao.volverAAdmitirRecurso(recurso);
    }

    @Override
    public List<Recurso> consultarRecursosAdmin() throws LibraryServicesException {
        return recursoDao.consultarRecursosAdmin();
    }

    @Override
    public void reservarRecurso(Recurso recurso, Usuario usuario, Timestamp fechaIni,Timestamp fechaFin) throws LibraryServicesException {
        reservaDao.reservarRecurso(recurso,usuario,fechaIni,fechaFin);
    }

}

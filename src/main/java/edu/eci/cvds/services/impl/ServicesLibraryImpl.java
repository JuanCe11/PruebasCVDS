package edu.eci.cvds.services.impl;

import edu.eci.cvds.entities.Horario;
import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.persistence.DaoHorario;
import edu.eci.cvds.persistence.DaoReserva;
import edu.eci.cvds.services.LibraryServicesException;
import edu.eci.cvds.services.ServicesLibrary;
import edu.eci.cvds.persistence.DaoRecurso;
import javax.inject.Inject;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class ServicesLibraryImpl implements ServicesLibrary {
    @Inject
    private DaoRecurso recursoDao;
    @Inject
    private DaoReserva reservaDao;
    @Inject
    private DaoHorario horarioDao;

    @Override
    public List<Recurso> consultarRecursos() throws LibraryServicesException {
        return recursoDao.consultarRecursos();
    }

    @Override
    public Recurso consultarRecurso(int id) throws LibraryServicesException {
        return recursoDao.consultarRecurso(id);
    }

    @Override
    public void reservarRecurso(Recurso recurso, Usuario usuario, Timestamp fechaIni, Timestamp fechaFin) throws LibraryServicesException {
        reservaDao.reservarRecurso(recurso,usuario,fechaIni,fechaFin);
    }

    @Override
    public void reservaRecursorecurrente(Recurso recurso, Usuario usuario, Timestamp fechaIni, Timestamp fechaFin) throws LibraryServicesException{
        ArrayList<Timestamp> fechas=new ArrayList<>();
        fechas.add(fechaIni);
        for(Timestamp i:fechas){
            //Falta la logica
        }
    }

    @Override
    public List<Reserva> consultarReservasUsuario(String id) throws LibraryServicesException {
        return reservaDao.consultarReservasUsuario(id);
    }

    @Override
    public List<Reserva> consultarReservas() throws LibraryServicesException {
        return reservaDao.consultarReservas();
    }

    @Override
    public List<Horario> horariosRecursos() throws LibraryServicesException{
        return horarioDao.horarios();
    }

    @Override
    public List<Reserva> consultarReservaRecurso(Recurso recurso) throws LibraryServicesException {
        return reservaDao.consultarReservaRecurso(recurso);
    }

    @Override
    public void eliminarReserva(Reserva reserva) throws LibraryServicesException {
        reservaDao.eliminarReserva(reserva);
    }


}

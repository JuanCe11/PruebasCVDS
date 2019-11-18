package edu.eci.cvds.persistence.mybatisimpl;

import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.entities.Usuario;

import edu.eci.cvds.persistence.DaoReserva;
import edu.eci.cvds.persistence.mybatisimpl.mappers.ReservaMapper;
import edu.eci.cvds.services.LibraryServicesException;

import javax.inject.Inject;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class MyBatisDaoReserva implements DaoReserva {

    @Inject
    private ReservaMapper reservaMapper;

    @Override
    public void reservarRecurso(Recurso recurso, Usuario usuario, Timestamp fechaIni,Timestamp fechaFin) throws LibraryServicesException{
        try {
            reservaMapper.reservarRecurso(recurso, usuario, fechaIni, fechaFin);
        }catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new LibraryServicesException(e.getMessage());
        }
    }

    @Override
    public List<Reserva> consultarReservasUsuario(String id) throws LibraryServicesException{
        try{
            return reservaMapper.consultarReservasUsuario(id);
        }catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw  new LibraryServicesException(e.getMessage());
        }
    }

    @Override
    public List<Reserva> consultarReservas() throws LibraryServicesException {
        try{
            return reservaMapper.consultarReservas();
        }catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new LibraryServicesException(e.getMessage());
        }
    }

    @Override
    public Reserva consultarReservaRecurso(Recurso recurso) throws LibraryServicesException{
        try {
            return reservaMapper.consultarReservaRecurso(recurso);
        }catch (org.apache.ibatis.exceptions.PersistenceException e){
            throw new LibraryServicesException(e.getMessage());
        }
    }
}

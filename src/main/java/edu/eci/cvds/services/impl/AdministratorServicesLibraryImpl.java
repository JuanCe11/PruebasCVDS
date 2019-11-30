package edu.eci.cvds.services.impl;

import edu.eci.cvds.entities.*;
import edu.eci.cvds.persistence.DaoHorario;
import edu.eci.cvds.persistence.DaoRecurso;
import edu.eci.cvds.persistence.DaoReserva;
import edu.eci.cvds.persistence.DaoTipoRecurso;
import edu.eci.cvds.services.AdministratorServicesLibrary;
import edu.eci.cvds.services.LibraryServicesException;
import edu.eci.cvds.services.ServicesLibrary;
import edu.eci.cvds.services.ServicesLibraryFactory;

import javax.inject.Inject;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AdministratorServicesLibraryImpl extends ServicesLibraryImpl implements AdministratorServicesLibrary {

    @Inject
    private DaoRecurso recursoDao;
    @Inject
    private DaoTipoRecurso tipoRecursoDao;
    @Inject
    private DaoReserva reservaDao;
    @Inject
    private DaoHorario horarioDao;

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
        reservaDao.reservarRecurso(recurso,usuario,fechaIni,fechaFin,"Normal");
    }

    @Override
    public void ingresarHorario(Recurso recurso, Horario horario) throws LibraryServicesException {
        horarioDao.ingresarHorario(recurso,horario);
    }


    @Override
    public void reporteDeOcupacion() throws LibraryServicesException {
        List<Reserva> reservas=reservaDao.consultarReservas();
        ArrayList<Reserva> recurrentes=new ArrayList<>();
        ArrayList<Reserva> canceladas=new ArrayList<>();
        HashMap<Reserva,Integer> recursosMasUsados=new HashMap<>();
        HashMap<Time[],Integer> horasMasSolicitadas=new HashMap<>();
        horasMasSolicitadas.put(new Time[]{Time.valueOf("07:00:00"),Time.valueOf("08:30:00")},0);
        horasMasSolicitadas.put(new Time[]{Time.valueOf("08:30:00"),Time.valueOf("10:00:00")},0);
        horasMasSolicitadas.put(new Time[]{Time.valueOf("10:00:00"),Time.valueOf("11:30:00")},0);
        horasMasSolicitadas.put(new Time[]{Time.valueOf("11:30:00"),Time.valueOf("13:00:00")},0);
        horasMasSolicitadas.put(new Time[]{Time.valueOf("13:00:00"),Time.valueOf("14:30:00")},0);
        horasMasSolicitadas.put(new Time[]{Time.valueOf("14:30:00"),Time.valueOf("16:00:00")},0);
        horasMasSolicitadas.put(new Time[]{Time.valueOf("16:00:00"),Time.valueOf("17:30:00")},0);
        horasMasSolicitadas.put(new Time[]{Time.valueOf("17:30:00"),Time.valueOf("19:00:00")},0);
        for(Reserva i:reservas){
            if(i.getTipo().equals("Recurrente")) recurrentes.add(i);
            if(i.getEstado().equals("Cancelada")) canceladas.add(i);
            if(recursosMasUsados.containsKey(i)){
                recursosMasUsados.put(i,recursosMasUsados.get(i)+1);
            }
            else{
                recursosMasUsados.put(i,1);
            }
            Time[] arr=calculeArray(i);
            horasMasSolicitadas.put(arr,horasMasSolicitadas.get(arr)+1);
        }
    }

    private Time[] calculeArray(Reserva reserva) {
        Time horaInicioReserva=new Time(reserva.getFechaInicio().getTime());
        Time[] ans=new Time[2];
        HashMap<Time,Time> tiempos=new HashMap<>();
        tiempos.put(Time.valueOf("07:00:00"),Time.valueOf("08:30:00"));
        tiempos.put(Time.valueOf("08:30:00"),Time.valueOf("10:00:00"));
        tiempos.put(Time.valueOf("10:00:00"),Time.valueOf("11:30:00"));
        tiempos.put(Time.valueOf("11:30:00"),Time.valueOf("13:00:00"));
        tiempos.put(Time.valueOf("13:00:00"),Time.valueOf("14:30:00"));
        tiempos.put(Time.valueOf("14:30:00"),Time.valueOf("16:00:00"));
        tiempos.put(Time.valueOf("16:00:00"),Time.valueOf("17:30:00"));
        tiempos.put(Time.valueOf("17:30:00"),Time.valueOf("19:00:00"));
        Set<Time> values=tiempos.keySet();
        for(Time i:values){
            if(in(i,horaInicioReserva)){
                ans[0]=i;
                ans[1]=tiempos.get(i);
                break;
            }
        }
        return ans;
    }

    private boolean in(Time taken,Time reservaTime){
        if(taken.getHours()<=reservaTime.getHours() && taken.getHours()+1>=reservaTime.getHours()){
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws LibraryServicesException{

    }


}

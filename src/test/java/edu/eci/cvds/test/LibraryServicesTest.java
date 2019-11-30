package edu.eci.cvds.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.google.inject.Inject;
import edu.eci.cvds.entities.*;
import edu.eci.cvds.services.*;
import org.junit.Before;
import org.junit.Test;


public class LibraryServicesTest{

    @Inject
    AdministratorServicesLibrary administratorServices;
    @Inject
    ServicesLibrary servicesLibrary;
    private Recurso recurso=new Recurso("El insertado","Biblioteca G",new TipoRecurso(1,"Computador"),"Disponible",1,50);
    private Usuario usuario=new Usuario("Hola","123456","regular","regular@cvds.com",123456,"Ingenieria de Sistemas");
    private Horario horario=new Horario(1,recurso,new Time(System.currentTimeMillis()),new Time(System.currentTimeMillis()+7200000));
    private Recurso recursoIns=new Recurso("El insertado","Biblioteca G",new TipoRecurso(1,"Computador"),"Disponible",2,50);

    @Before
    public void setUp(){}

    public LibraryServicesTest(){
        administratorServices=ServicesLibraryFactory.getInstance().testAdminServicesLibrary();
        servicesLibrary=ServicesLibraryFactory.getInstance().testServicesLibrary();
    }

    @Test
    public void deberiaConsultarTodosLosRecursos() throws LibraryServicesException {
        assertTrue(servicesLibrary.consultarRecursos() instanceof List);
    }

    @Test
    public void deberiaConsultarTodosLosRecursosComoAdmin() throws LibraryServicesException{
        assertTrue(administratorServices.consultarRecursosAdmin() instanceof List);
    }


    @Test
    public void deberiaEliminareIngresarRecurso() throws LibraryServicesException {
        administratorServices.eliminarUnRecursoPermanente(recursoIns);
        int length=servicesLibrary.consultarRecursos().size();
        administratorServices.registrarRecurso(recursoIns);
        assertTrue(servicesLibrary.consultarRecursos().size()>length);
    }

    @Test
    public void deberiaAgregarUnHorarioYConsultarlo() throws LibraryServicesException{
        administratorServices.registrarRecurso(recurso);
        recurso.setIdentificadorInterno(servicesLibrary.consultarRecursos().get(0).getIdentificadorInterno());
        administratorServices.ingresarHorario(recurso,horario);
        servicesLibrary.horarioRecurso(recurso);
        assertTrue(servicesLibrary.horarioRecurso(recurso).get(0).getId()==horario.getId());
    }


    @Test
    public void deberiaConsultarUnRecurso() throws LibraryServicesException {
        assertTrue(servicesLibrary.consultarRecurso(1).getNombre().equals("El insertado"));
    }


    @Test
    public void deberiaSacarUnRecursoDeFormaTemporalyDevolverlo() throws LibraryServicesException {
        administratorServices.eliminarUnRecursoTemporal(recurso);
        assertFalse(servicesLibrary.consultarRecurso(administratorServices.consultarRecursosAdmin().get(0).getIdentificadorInterno()).getEstado().equals("Mantenimiento"));
        administratorServices.volverAAdmitirElRecurso(recurso);
        assertTrue(servicesLibrary.consultarRecurso(administratorServices.consultarRecursosAdmin().get(0).getIdentificadorInterno()).getEstado().equals("Disponible"));
    }

    @Test
    public void NoDebePermitirUnaCapacidadNegativa(){
        try {
            Recurso recurso=new Recurso("El PC","Central",new TipoRecurso(1,"Computador"),"Disponible",500,-50);
            administratorServices.registrarRecurso(recurso);
            assertTrue(false);
        }catch(LibraryServicesException e){ }
    }

    @Test
    public void debePermitirUnaCapacidadPositiva(){
        try{
            Recurso recurso=new Recurso("El PC","Central",new TipoRecurso(1,"Computador"),"Disponible",500,50);
            administratorServices.eliminarUnRecursoPermanente(recurso);
            administratorServices.registrarRecurso(recurso);
        }catch (LibraryServicesException e){
            e.printStackTrace();
            assertTrue(false);
        }
    }

    @Test
    public void debeRealizarUnaReservayConsultarlaPorSuUsuarioYSuRecurso() throws LibraryServicesException{

        Timestamp fechaIni = new Timestamp(System.currentTimeMillis()+86400000);
        Timestamp fechaFin = new Timestamp(System.currentTimeMillis() + 7200000+86400000);
        recurso.setIdentificadorInterno(administratorServices.consultarRecursosAdmin().get(0).getIdentificadorInterno());
        servicesLibrary.reservarRecurso(recurso, usuario, fechaIni, fechaFin);
        assertTrue(servicesLibrary.consultarReservasUsuario(usuario.getId()).get(0).getUsuario().getId().equals("regular@cvds.com"));
        assertTrue(servicesLibrary.consultarReservas().get(0).getRecurso().getIdentificadorInterno() == administratorServices.consultarRecursosAdmin().get(0).getIdentificadorInterno());
        assertTrue(servicesLibrary.consultarReservaRecurso(recurso) != null);
        servicesLibrary.eliminarReserva(servicesLibrary.consultarReservas().get(0));
        assertTrue(servicesLibrary.consultarReservas().get(0).getEstado().equals("Cancelada"));
        assertTrue(servicesLibrary.consultarReservasActivas().size() == 0);

    }

    @Test
    public void noDebePermitirReservasMayoresADosHoras(){
        Timestamp fechaIni=new Timestamp(System.currentTimeMillis());
        Timestamp fechaFin=new Timestamp(System.currentTimeMillis()+7200001);
        try{
            servicesLibrary.reservarRecurso(recurso,usuario,fechaIni,fechaFin);
        }catch (LibraryServicesException e){
            assertTrue(e.getMessage().equals(LibraryServicesException.RESERVA_MAYOR_A_DOS_HORAS));
        }
    }

    @Test
    public void noDebePermitirSobrePonerReservas(){
        Timestamp fechaIni=new Timestamp(System.currentTimeMillis()+86400000);
        Timestamp fechaFin=new Timestamp(System.currentTimeMillis()+7200000+86400000);
        try {
            recurso.setIdentificadorInterno(administratorServices.consultarRecursosAdmin().get(0).getIdentificadorInterno());
            servicesLibrary.reservarRecurso(recurso, usuario, fechaIni, fechaFin);
            servicesLibrary.reservarRecurso(recurso,usuario,fechaIni,fechaFin);
        }catch (LibraryServicesException e){
            assertTrue(e.getMessage().equals(LibraryServicesException.RECURSO_RESERVADO_EN_HORA) );
        }
    }

    @Test
    public void noDebePermitirUnaReservaParaUnDiaAnteriorOEstarFueraDeHora(){
        Timestamp fechaIni=new Timestamp(System.currentTimeMillis()-86400000);
        Timestamp fechaFin=new Timestamp(System.currentTimeMillis());
        try{
            servicesLibrary.reservarRecurso(recurso,usuario,fechaIni,fechaFin);
        }catch (LibraryServicesException e){
            assertTrue(e.getMessage().equals(LibraryServicesException.RESERVA_FUERA_DE_FECHA));
        }
    }

    @Test
    public void debeConsultarUnaSolaReserva() throws LibraryServicesException{
        Timestamp fechaIni=new Timestamp(System.currentTimeMillis()+86400000*2);
        Timestamp fechaFin=new Timestamp(System.currentTimeMillis()+86400000*2+7200000);
        servicesLibrary.reservarRecurso(recurso,usuario,fechaIni,fechaFin);
        List<Reserva> temp=servicesLibrary.consultarReservas();
        Reserva reserva=servicesLibrary.consultarReserva(temp.get(temp.size()-1).getId());
        assertTrue(reserva.getUsuario().getId().equals("regular@cvds.com") && reserva.getRecurso().getIdentificadorInterno()==1);
    }

    @Test
    public void pruebasSotu()throws LibraryServicesException{

    }
}
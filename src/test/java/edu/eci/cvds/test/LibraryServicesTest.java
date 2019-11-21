package edu.eci.cvds.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.google.inject.Inject;
import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.entities.TipoRecurso;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.services.*;
import org.junit.Before;
import org.junit.Test;


public class LibraryServicesTest{

    @Inject
    AdministratorServicesLibrary administratorServices;
    @Inject
    ServicesLibrary servicesLibrary;
    private Recurso recurso=new Recurso("El PC","Central",new TipoRecurso(1,"Computador"),"Disponible",500,null);
    private Usuario usuario=new Usuario("Hola","123456","regular","regular@cvds.com",123456,"Ingenieria de Sistemas");

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
        administratorServices.eliminarUnRecursoPermanente(recurso);
        int length=servicesLibrary.consultarRecursos().size();
        administratorServices.registrarRecurso(recurso);
        assertTrue(servicesLibrary.consultarRecursos().size()>length);
    }


    @Test
    public void deberiaConsultarUnRecurso() throws LibraryServicesException {
        assertTrue(servicesLibrary.consultarRecurso(administratorServices.consultarRecursosAdmin().get(0).getIdentificadorInterno()).getNombre().equals("El PC"));
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
        Timestamp fechaIni=new Timestamp(System.currentTimeMillis());
        Timestamp fechaFin=new Timestamp(System.currentTimeMillis()+7200000);
        recurso.setIdentificadorInterno(administratorServices.consultarRecursosAdmin().get(0).getIdentificadorInterno());
        servicesLibrary.reservarRecurso(recurso,usuario,fechaIni,fechaFin);
        assertTrue(servicesLibrary.consultarReservasUsuario(usuario.getId()).get(0).getUsuario().getId().equals("regular@cvds.com"));
        assertTrue(servicesLibrary.consultarReservas().get(0).getRecurso().getIdentificadorInterno()==administratorServices.consultarRecursosAdmin().get(0).getIdentificadorInterno());
        assertTrue(servicesLibrary.consultarReservaRecurso(recurso)!=null);
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
        Timestamp fechaIni=new Timestamp(System.currentTimeMillis());
        Timestamp fechaFin=new Timestamp(System.currentTimeMillis()+7200000);
        try {
            recurso.setIdentificadorInterno(administratorServices.consultarRecursosAdmin().get(0).getIdentificadorInterno());
            servicesLibrary.reservarRecurso(recurso, usuario, fechaIni, fechaFin);
            servicesLibrary.reservarRecurso(recurso,usuario,fechaIni,fechaFin);
        }catch (LibraryServicesException e){
            assertTrue(e.getMessage().equals(LibraryServicesException.RECURSO_RESERVADO_EN_HORA));
        }
    }

    @Test
    public void pruebasSotu()throws LibraryServicesException{
        
    }
}
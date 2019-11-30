package edu.eci.cvds.managedbeans;

import com.google.inject.Inject;
import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.services.AdministratorServicesLibrary;
import edu.eci.cvds.services.LibraryServicesException;
import edu.eci.cvds.services.ServicesLibrary;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@SuppressWarnings("deprecation")
@ManagedBean(name = "recursoBean")
@SessionScoped
public class RecursoBean extends BasePageBean {

    private static final transient Logger log = LoggerFactory.getLogger(UserBean.class);
    private ScheduleModel eventModel = new DefaultScheduleModel();
    private ScheduleEvent event = new DefaultScheduleEvent();

    private String[] ubicaciones = {"Bibioteca B", "Bibioteca G"};
    private String ubicacionSeleccionada;

    private String[] tipos = {"Computador", "Multimedia","Sala de estudio"};
    private String tipoSleccionado;

    private String[] estadosRecurso = {"Disponible", "No Disponible"};
    private String estadoSeleccionado;
    private int idSeleccionado;
    private String usuario;
    private int id;

    @Inject
    private ServicesLibrary servicesL;

    @Inject
    private AdministratorServicesLibrary servicesA;

    private Recurso recurso;

    public List<Recurso> getRecursosAdmin() throws LibraryServicesException {
        return servicesA.consultarRecursosAdmin();
    }

    public List<Recurso> getRecursos() throws LibraryServicesException {
        return servicesL.consultarRecursos();
    }

    public List<String> getEstados() {
        return recurso.getEstados();
    }

    public void editarEstadoRecurso() {
        if (estadoSeleccionado.equals("Disponible")){
            try{
                servicesA.volverAAdmitirElRecurso(servicesL.consultarRecurso(idSeleccionado));
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(estadoSeleccionado.equals("No Disponible")){
            try{
                servicesA.eliminarUnRecursoTemporal(servicesL.consultarRecurso(idSeleccionado));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    public void seleccionarRecurso(int idSeleccionado){
        this.idSeleccionado = idSeleccionado;
    }
    
    public void registrarRecurso(String nombre, int capacidad) throws LibraryServicesException {
        int id = servicesA.consultarRecursosAdmin().size() + 1;
        int tipo = buscarIndice();
        try {
            recurso = new Recurso(id, tipo+1, nombre, ubicacionSeleccionada, capacidad, "Disponible", tipo+1, tipos[tipo]);
            servicesA.registrarRecurso(recurso);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int buscarIndice() {
        int res = -1;
        for (int i = 0; i <tipos.length ; i++) {
            if (tipoSleccionado.equals(tipos[i])){
                res=i;
            }
        }
        return res;
    }

    public void eliminarRecurso(int id) {
        try {
            servicesA.eliminarUnRecursoPermanente(servicesL.consultarRecurso(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reloadAdmin() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/admin/pages/cambiarEstadoRecurso.xhtml");
    }

    public void reloadUser() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/regular/pages/consultarRecursos.xhtml");
    }
    
    public void reloadGuest() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/consultarRecursos.xhtml");
    }

    public void verReservas() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/regular/pages/verReservas.xhtml");
    }

    //Calendario del recurso
    public void fillDate(int id) throws LibraryServicesException {
        eventModel = new DefaultScheduleModel();
        event = new DefaultScheduleEvent();
        Recurso re = servicesL.consultarRecurso(id);
        List<Reserva> lista = servicesL.consultarReservas();
        boolean banderaRec = false;
        for (int i = 0; i < lista.size(); i++){
            if (lista.get(i).getRecurso().getIdentificadorInterno() == id){
                banderaRec = true;
            }
        }
        lista = servicesL.consultarReservaRecurso(re);
        if (banderaRec) {
            if (lista.size() > 0) {
                for (Reserva r : lista) {
                    eventModel.addEvent( new DefaultScheduleEvent(r.getRecurso().getNombre() + "  " + r.getUsuario().getNombre()+" "+r.getTipo(), r.getFechaIniDate(), r.getFechaFinDate()));
                }
            }
        }
    }

    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random() * 30)) + 1);    //set random day of month

        return date.getTime();
    }

    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar.getTime();
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);

        return calendar;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void addEvent(String tipoRecurencia, String cantidadRecurrencia) throws LibraryServicesException, IOException {
        if (tipoRecurencia == null || tipoRecurencia.equals("")){
            servicesL.reservarRecurso(servicesL.consultarRecurso(idSeleccionado),servicesL.consultarUsuario(usuario),new Timestamp(event.getStartDate().getTime()),new Timestamp(event.getEndDate().getTime()));
        }else{
            if (cantidadRecurrencia == null || cantidadRecurrencia.equals("")){
                FacesContext context = FacesContext.getCurrentInstance();
                context.addMessage(null, new FacesMessage("Error",  "No se selecciono la cantidad de la recurrencia en la reserva" ));
            }else{
                servicesL.reservaRecursorecurrente(servicesL.consultarRecurso(idSeleccionado),servicesL.consultarUsuario(usuario),new Timestamp(event.getStartDate().getTime()),new Timestamp(event.getEndDate().getTime()),tipoRecurencia,cantidadRecurrencia);
                event = new DefaultScheduleEvent();
                horariosPage(idSeleccionado);
            }
        }
    }


    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

    public void onDateSelect(SelectEvent selectEvent) {
        event = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());

        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void deleteEvent() {
        eventModel.deleteEvent(event);
    }

    public void horariosPage(int id) throws IOException, LibraryServicesException {
        idSeleccionado = id;
        fillDate(id);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/regular/pages/horarios.xhtml");
    }
    public void horariosPageGuest(int id) throws IOException, LibraryServicesException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/horarios.xhtml");
        idSeleccionado = id;
        fillDate(id);
    }

    public void setUbicacionSeleccionada(String ubicacionSeleccionada) {
        this.ubicacionSeleccionada = ubicacionSeleccionada;
    }

    public String getUbicacionSeleccionada() {
        return this.ubicacionSeleccionada;
    }

    public String[] getUbicaciones() {
        return ubicaciones;
    }

    public String getTipoSleccionado() {
        return tipoSleccionado;
    }

    public void setTipoSleccionado(String tipoSleccionado) {
        this.tipoSleccionado = tipoSleccionado;
    }

    public String[] getTipos() {
        return tipos;
    }

    public String[] getEstadosRecurso(){
        return estadosRecurso;
    }

    public String getEstadoSeleccionado() {
        return estadoSeleccionado;
    }

    public void setEstadoSeleccionado(String estadoSeleccionado) {
        this.estadoSeleccionado = estadoSeleccionado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public List<Reserva> getReservasUsuario() throws LibraryServicesException {
        return servicesL.consultarReservasUsuario(usuario);
    }

}

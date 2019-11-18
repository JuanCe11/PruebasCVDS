package edu.eci.cvds.managedbeans;

import com.google.inject.Inject;
import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.services.AdministratorServicesLibrary;
import edu.eci.cvds.services.LibraryServicesException;
import edu.eci.cvds.services.ServicesLibrary;
import java.io.IOException;
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
    }

    public void registrarRecurso(String nombre, int capacidad) throws LibraryServicesException {
        int id = servicesA.consultarRecursosAdmin().size() + 1;
        try {
            recurso = new Recurso(id, 1, nombre, "Biblioteca", capacidad, "Disponible", 1, "PC");
            servicesA.registrarRecurso(recurso);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void eliminarRecurso(int id) {
        try {
            servicesA.eliminarUnRecursoPermanente(servicesL.consultarRecurso(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reloadAdmin() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/admin/pages/cambiarEstadoRecurso.xhtml");
    }
    
    public void reloadUser() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/regular/pages/consultarRecursos.xhtml");
    }
    
    
    //Calendario del recurso
    
    public void fillDate(int id) throws LibraryServicesException{
        
        for(Recurso r : servicesL.consultarRecursos()){
            eventModel.addEvent(new DefaultScheduleEvent(r.getNombre(),getRandomDate(nextDay9Am()),getRandomDate(nextDay11Am())));
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

    private Date nextDay9Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 9);

        return t.getTime();
    }

    private Date nextDay11Am() {
        Calendar t = (Calendar) today().clone();
        t.set(Calendar.AM_PM, Calendar.AM);
        t.set(Calendar.DATE, t.get(Calendar.DATE) + 1);
        t.set(Calendar.HOUR, 11);

        return t.getTime();
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public void addEvent() {
        if (event.getId() == null) {
            eventModel.addEvent(event);
        } else {
            eventModel.updateEvent(event);
        }

        event = new DefaultScheduleEvent();
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
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/regular/pages/horarios.xhtml");
        this.id = id;
        fillDate(id);
    }
}

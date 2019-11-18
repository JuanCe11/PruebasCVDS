package edu.eci.cvds.guice;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import edu.eci.cvds.persistence.DaoHorario;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisDaoHorario;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.eci.cvds.persistence.DaoRecurso;
import edu.eci.cvds.persistence.DaoReserva;
import edu.eci.cvds.persistence.DaoTipoRecurso;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisDaoRecurso;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisDaoReserva;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisTipoRecurso;
import edu.eci.cvds.services.AdministratorServicesLibrary;
import edu.eci.cvds.services.ServicesLibrary;
import edu.eci.cvds.services.impl.AdministratorServicesLibraryImpl;
import edu.eci.cvds.services.impl.ServicesLibraryImpl;


public class GuiceContextListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.removeAttribute(Injector.class.getName());
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Injector injector = Guice.createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {

                install(JdbcHelper.MySQL);
                setEnvironmentId("development");
                setClassPathResource("mybatis-config.xml");

                // Laboratories
                bind(ServicesLibrary.class).to(ServicesLibraryImpl.class);
                bind(AdministratorServicesLibrary.class).to(AdministratorServicesLibraryImpl.class);
                bind(DaoRecurso.class).to(MyBatisDaoRecurso.class);
                bind(DaoReserva.class).to(MyBatisDaoReserva.class);
                bind(DaoTipoRecurso.class).to(MyBatisTipoRecurso.class);
                bind(DaoHorario.class).to(MyBatisDaoHorario.class);
            }
        }
        );

        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(Injector.class.getName(), injector);
    }

}

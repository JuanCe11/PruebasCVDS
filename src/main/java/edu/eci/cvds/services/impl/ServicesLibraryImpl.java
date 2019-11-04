package edu.eci.cvds.services.impl;

import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.services.ServicesLibrary;
import edu.eci.cvds.persistence.DaoRecurso;
import javax.inject.Inject;
import java.util.List;


public class ServicesLibraryImpl implements ServicesLibrary {
    @Inject
    private DaoRecurso recursoDao;

    public List<Recurso> consultarRecursos(){
        return recursoDao.consultarRecursos();
    }
}

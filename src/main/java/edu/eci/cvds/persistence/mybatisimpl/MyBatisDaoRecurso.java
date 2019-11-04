package edu.eci.cvds.persistence.mybatisimpl;

import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.persistence.DaoRecurso;
import edu.eci.cvds.persistence.mybatisimpl.mappers.RecursoMapper;

import javax.inject.Inject;
import java.util.List;

public class MyBatisDaoRecurso implements DaoRecurso {

    @Inject
    private RecursoMapper recursoMapper;

    @Override
    public void registraNuevoRecurso(Recurso recurso) {
        try{
            recursoMapper.registraNuevoRecurso(recurso);
        }catch(org.apache.ibatis.exceptions.PersistenceException e){

        }
    }

    @Override
    public void eliminarPermanente(Recurso recurso) {
        try{
            recursoMapper.eliminarRecursoPermanente(recurso);
        }catch(org.apache.ibatis.exceptions.PersistenceException e){

        }
    }

    @Override
    public void eliminarTemporal(Recurso recurso) {
        try{
            recursoMapper.eliminarRecursoTemporal(recurso);
        }catch (org.apache.ibatis.exceptions.PersistenceException e){

        }
    }

    @Override
    public void volverAAdmitirRecurso(Recurso recurso) {
        try{
            recursoMapper.volverAAdmitirRecurso(recurso);
        }catch (org.apache.ibatis.exceptions.PersistenceException e){

        }
    }

    @Override
    public List<Recurso> consultarRecursos() {
        try{
            return recursoMapper.consultarRecursos();
        }catch(org.apache.ibatis.exceptions.PersistenceException e){

        }
        return null;
    }
}

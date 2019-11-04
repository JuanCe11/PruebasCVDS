package edu.eci.cvds.persistence.mybatisimpl.mappers;


import edu.eci.cvds.entities.Recurso;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecursoMapper {
    public void registraNuevoRecurso(@Param("recurso") Recurso recurso);
    public void eliminarRecursoPermanente(@Param("recurso") Recurso recurso);
    public void eliminarRecursoTemporal(@Param("recurso") Recurso recurso);
    public void volverAAdmitirRecurso(@Param("recurso") Recurso recurso);
    public List<Recurso> consultarRecursos();
}

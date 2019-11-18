package edu.eci.cvds.persistence.mybatisimpl.mappers;

import edu.eci.cvds.entities.TipoRecurso;
import org.apache.ibatis.annotations.Param;

public interface TipoRecursoMapper {
    void registrarTipoRecurso(@Param("tipoRecurso") TipoRecurso tipoRecurso);
}

package edu.eci.cvds.persistence.mybatisimpl.mappers;


import edu.eci.cvds.entities.Horario;

import java.util.List;

public interface HorarioMapper  {
    /**
     * Consulta todos los horarios
     * @return Una lista de todos los horarios
     */
    List<Horario> horarios();
}

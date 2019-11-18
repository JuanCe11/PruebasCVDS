package edu.eci.cvds.persistence.mybatisimpl.mappers;

import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.entities.Usuario;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

public interface ReservaMapper {
    void reservarRecurso(@Param("recurso") Recurso recurso, @Param("usuario")Usuario usuario, @Param("fechaIni") Timestamp fechaIni,@Param("fechaFin") Timestamp fechaFin);

    List<Reserva> consultarReservasUsuario(@Param("id") String id);

    List<Reserva> consultarReservas();

    Reserva consultarReservaRecurso(@Param("recurso") Recurso recurso);
}

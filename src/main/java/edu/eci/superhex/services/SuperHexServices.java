package edu.eci.superhex.services;

import edu.eci.superhex.persistence.SuperHexPersistenceException;
import edu.eci.superhex.model.Partida;
import edu.eci.superhex.repo.JugadorRepo;
import edu.eci.superhex.repo.PartidaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SuperHexServices")
public class SuperHexServices {
    @Autowired
    PartidaRepo partidaRepo;

    @Autowired
    JugadorRepo jugadorRepo;

    /**
     * Añade una partida
     * @param partida la aprtida para agregar
     * @throws SuperHexPersistenceException
     */
    public void addPartida(Partida partida) throws SuperHexPersistenceException {
        partidaRepo.save(partida);
        partidaRepo.flush();
    }

    /**
     * Metodo encargado de traer todas las partidas
     * @return Partida
     * @throws SuperHexPersistenceException
     */
    public List<Partida> getAllPartidas() throws SuperHexPersistenceException {
        return partidaRepo.findAll();
    }
}

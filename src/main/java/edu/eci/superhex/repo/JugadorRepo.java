package edu.eci.superhex.repo;

import edu.eci.superhex.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JugadorRepo extends JpaRepository<Jugador,String> {
}

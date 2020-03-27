package edu.eci.superhex.persistence;

import edu.eci.superhex.model.Jugador;
import edu.eci.superhex.model.Sala;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SuperHexCache {
    private ConcurrentHashMap<String, Sala> salas = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, CopyOnWriteArrayList<Jugador>> jugadores = new ConcurrentHashMap<>();

    public boolean existeSala(String name){
        return salas.containsKey(name);
    }

    public void addSala(String name, Sala room) {
        salas.put(name,room);
    }

    public void crearJugadores(String name) {
        jugadores.put(name,new CopyOnWriteArrayList<Jugador>());
    }

    public Sala getSala(String name) {
        return salas.get(name);
    }

    public void addPlayer(String name, Jugador player) {
        jugadores.get(name).add(player);
    }

    public List<Sala> getSalas() {
        ArrayList lista = new ArrayList();
        for (ConcurrentHashMap.Entry<String, Sala> entry : salas.entrySet()) {
            lista.add(entry.getValue());
        }
        return lista;
    }

    public CopyOnWriteArrayList<Jugador> getJugadorBySala(String name){
        return jugadores.get(name);
    }
}

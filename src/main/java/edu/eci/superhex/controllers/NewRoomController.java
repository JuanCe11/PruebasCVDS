package edu.eci.superhex.controllers;

import edu.eci.superhex.model.Jugador;
import edu.eci.superhex.model.Sala;
import edu.eci.superhex.persistence.SuperHexCache;
import edu.eci.superhex.persistence.SuperHexPersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class NewRoomController {

    @Autowired
    SimpMessagingTemplate msgt;

    @Autowired
    SuperHexCache cache;

    @MessageMapping("/newroom.{name}")
    public void handleCreateRoom(Sala room,@DestinationVariable String name) throws Exception {
        System.out.println("Nueva sala recibida en el servidor!: "+room.getName());
        if(!cache.existeSala(name)){
            cache.addSala(name,room);
            cache.crearJugadores(name);
        }else{
            throw new SuperHexPersistenceException(SuperHexPersistenceException.SALA_YA_EXISTE);
        }
        msgt.convertAndSend("/topic/created."+name,cache.getSalas());
        msgt.convertAndSend("/topic/created",cache.getSalas());
    }
    @MessageMapping("/joinroom.{name}")
    public void handleJoinRoom(Jugador player,@DestinationVariable String name) throws Exception {
        System.out.println("Nuevo jugador recibido en el servidor!: "+player.getName()+" para la sala "+name);
        if(cache.existeSala(name)){
            Sala s = cache.getSala(name);
            s.addPlayer();
            cache.addPlayer(name,player);
        }else{
            throw new SuperHexPersistenceException(SuperHexPersistenceException.SALA_NO_EXISTE);
        }
        msgt.convertAndSend("/topic/created",cache.getSalas());
        msgt.convertAndSend("/topic/joined."+name,cache.getJugadorBySala(name) );
    }

    @MessageMapping("/exitroom.{name}")
    public void handleExitRoom(Jugador player,@DestinationVariable String name) throws Exception {
        System.out.println("Eliminar jugador recibido en el servidor!: "+player.getName()+" para la sala "+name);
        if(cache.existeSala(name)){
            Sala s = cache.getSala(name);
            s.removePlayer();
            cache.removePlayer(name,player);
        }else{
            throw new SuperHexPersistenceException(SuperHexPersistenceException.SALA_NO_EXISTE);
        }
        msgt.convertAndSend("/topic/created",cache.getSalas());
        msgt.convertAndSend("/topic/joined."+name,cache.getJugadorBySala(name) );
    }
}
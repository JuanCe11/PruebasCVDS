package edu.eci.superhex.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;
import edu.eci.superhex.model.Partida;
import edu.eci.superhex.persistence.SuperHexCache;
import edu.eci.superhex.persistence.SuperHexPersistenceException;
import edu.eci.superhex.services.SuperHexServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/superhex")
public class SuperHexAPIController {

    @Autowired
    @Qualifier("SuperHexServices")
    SuperHexServices shs;

    @Autowired
    SuperHexCache cache;

    @RequestMapping(path = "/partidas",method = RequestMethod.GET)
    public ResponseEntity<?> GetAllPartidas() {
        try {
            return new ResponseEntity<>(shs.getAllPartidas(), HttpStatus.ACCEPTED);
        } catch (SuperHexPersistenceException e) {
            Logger.getLogger(SuperHexAPIController.class.getName()).log(Level.SEVERE, null, e);
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/partidas",method = RequestMethod.POST)
    public ResponseEntity<?> AddNuevaPartida(@RequestBody Partida newPart) throws SuperHexPersistenceException {
        try {
            shs.addPartida(newPart);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (SuperHexPersistenceException ex) {
            Logger.getLogger(SuperHexAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);
        }
    }


    @RequestMapping(path = "/salas",method = RequestMethod.GET)
    public ResponseEntity<?> GetAllSalas() {
        return new ResponseEntity<>(cache.getSalas(), HttpStatus.ACCEPTED);
    }
}
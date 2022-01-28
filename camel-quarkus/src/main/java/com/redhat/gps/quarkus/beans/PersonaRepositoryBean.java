package com.redhat.gps.quarkus.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.redhat.gps.quarkus.model.Persona;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonaRepositoryBean {

    private static Logger logger = LoggerFactory.getLogger(PersonaRepositoryBean.class);

    HashMap<Integer,Persona> repository = new HashMap<>();


    public PersonaRepositoryBean(){
        logger.info("Creando instancia de PersonaRepository");
        Persona per1 = new Persona(1, "Carlos", "Rodriguez", "Bogota", "crodriguez@redhat.com");
        Persona per2 = new Persona(2, "Johana", "Herrera", "Medellin", "jherrera@redhat.com");

        repository.put(per1.getId(), per1);
        repository.put(per2.getId(), per2);

    }

    public List<Persona> getTodas(){
        logger.info("Consultando todas las personas ");
        return new ArrayList<>(repository.values());
    }

    public Persona getPersonaById(Integer id){
        logger.info("Consultando persona con id "+id);
        return repository.get(id);
    }

    public void insertPersona(Persona persona){
        logger.info("Almacenando persona "+persona);
        if(!repository.containsKey(persona.getId())){
            repository.put(persona.getId(), persona);
        }else{
            logger.warn("persona con id "+persona.getId()+" existe");
            throw new IllegalArgumentException("Persona con id "+persona.getId()+" ya existe");
        }
    }

    public void deletePersona(Integer id){
        logger.info("Eliminando persona con id"+id);
        if(repository.containsKey(id)){
            repository.remove(id);
        }else{
            logger.warn("persona con id "+id+" no existe");
            throw new IllegalArgumentException("Persona con id "+id+" no existe");
        }

    }
    
}

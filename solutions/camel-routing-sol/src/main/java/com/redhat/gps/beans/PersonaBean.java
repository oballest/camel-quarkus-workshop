package com.redhat.gps.beans;

import com.redhat.gps.model.Persona;

import org.apache.camel.Body;
import org.apache.camel.ExchangeProperty;
import org.apache.camel.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonaBean {
    
    private static Logger logger = LoggerFactory.getLogger(PersonaBean.class);

    public Persona edicionPersona(@Body Persona persona,@Header("CamelFileName") String fileName,@ExchangeProperty("CamelSplitIndex") String index){
        
        logger.info("Persona "+persona);
        logger.info("FileName "+fileName+" record index= "+index);

        persona.setNombre(persona.getNombre()+" "+index);

        return persona;

    }

}

package com.redhat.gps.processor;

import com.redhat.gps.model.Persona;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonaProcessor implements Processor {

    private static Logger logger = LoggerFactory.getLogger(PersonaProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {
       
        String fileName = exchange.getIn().getHeader(Exchange.FILE_NAME,String.class);
        Integer index = exchange.getProperty(Exchange.SPLIT_INDEX, Integer.class);

        Persona persona = exchange.getIn().getBody(Persona.class);

        logger.info("Persona "+persona);
        logger.info("FileName "+fileName+" record index= "+index);
        
        persona.setApellido(persona.getApellido()+" "+index);
        
    }
    
}

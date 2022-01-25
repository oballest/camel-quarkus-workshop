package com.redhat.gps.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

public class CamelRouteBuilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        
        JaxbDataFormat df = new JaxbDataFormat("com.redhat.gps.model");

        from("file:inf_files/personas-in")
        .split().tokenizeXML("persona")
        .log("split xml body ${body}")
        .unmarshal(df)
        .log("Clase Persona ${body.nombre} ${body.apellido} ciudad = ${body.ciudad} email = ${body.email}")
        .log("${body.informacionFinanciera}")
        .to("direct:process-bean");

        from("direct:error-process")
        .log("${body}");

        
    }
    
}

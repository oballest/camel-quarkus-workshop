package com.redhat.gps.camel;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

public class CamelRouteBuilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        
        JaxbDataFormat df = new JaxbDataFormat("com.redhat.gps.model");

        from("file:in_files/personas-in")
        .split().tokenizeXML("persona")
        .log("split xml body ${body}")
        .unmarshal(df)
        .log("Clase Persona ${body.nombre} ${body.apellido} ciudad = ${body.ciudad} email = ${body.email}")
        .log("${body.informacionFinanciera}")
        .to("direct:process-bean");

        from("direct:error-process")
        .errorHandler(deadLetterChannel("direct:error-handler"))
        .log("${body}")
        .choice()
            .when(simple("${body.ciudad} != 'Bogota' and ${body.ciudad} != 'Medellin'"))
                .throwException(IllegalStateException.class, "Ciudad No soportada")
            .otherwise()
                .log("Ciudad Soportada")
        .end()
        .to("direct:try-catch-process");

        from("direct:error-handler")
        .log("Manejando eror ${exception} ,${exception.message}")
        .to("log:com.redhat.gps.camel.rutadeadleter");


        from("direct:try-catch-process")
        .log("${body}");

        
    }
    
}

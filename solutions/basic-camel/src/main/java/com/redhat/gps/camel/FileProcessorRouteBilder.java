package com.redhat.gps.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class FileProcessorRouteBilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        
        from("file:in_files/basic-copy")
        .log("Cargando Archivo ${header.CamelFileName}")
        .to("file:out_files/basic-copy");

        from("file:in_files/basic-filter")
        .log("Cargando Archivo ${header.CamelFileName} ")
        .filter(header("CamelFileName").endsWith(".txt"))
            .log("Contenido del archivo ${header.CamelFileName} = ${body}")
            . to("file:out_files/basic-filter").
        end();

        from("file:in_files/change-filter")
        .log("Cargando Archivo ${header.CamelFileName} ")
        .filter(header("CamelFileName").endsWith(".txt"))
            .log("Contenido del archivo ${header.CamelFileName} = ${body}")
            .setHeader("CamelFileName").simple("Procesado-${header.CamelFileName}")
            .setBody().simple("${body}-Modificado")
            .to("file:out_files/change-filter").
        end();

        from("file:in_files/camel-msg")
        .log("Cargando Archivo ${header.CamelFileName} ")
        .setProperty("Propiedad1").constant("Propiedad Exchange")
        .setHeader("Header1").constant("Header1")
        .process(new Processor() {

            @Override
            public void process(Exchange exchange) throws Exception {
                String body = exchange.getIn().getBody(String.class);
                String header = exchange.getIn().getHeader("Header1",String.class);
                String property = exchange.getProperty("Propiedad1", String.class);
                System.out.println("body="+body+" header="+header+" propiedad="+property);


                exchange.getOut().setBody(body+" "+header);

            }
            
        })
        .log("body = ${body}")
        .log("Header = ${header.Header1}")
        .log("Property = ${exchangeProperty.Propiedad1}")
        .to("log:com.redhat.gps.camel.loggger");
        
        
    }
    
}

package com.redhat.gps.camel;

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



        
        
    }
    
}

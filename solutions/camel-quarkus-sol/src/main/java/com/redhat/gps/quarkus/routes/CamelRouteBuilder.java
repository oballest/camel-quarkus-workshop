package com.redhat.gps.quarkus.routes;

import javax.ws.rs.core.MediaType;

import com.redhat.gps.quarkus.model.Persona;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;

public class CamelRouteBuilder extends RouteBuilder{

    @Override
    public void configure() throws Exception {
       
        restConfiguration()
        .bindingMode(RestBindingMode.json)
        .apiContextPath("/api-doc");

        rest("/personas")
            .get("/all")
                .description("Metodo encargado de consultar todas las personas en base de datos")
                .produces(MediaType.APPLICATION_JSON)
                .outType(Persona.class)
                .responseMessage().code("200").message("Personas consultadas exitosamente").responseModel(Persona.class).endResponseMessage()
                .id("getPersonasAll")
                .to("direct:getPersonasAll")
            .get("/{idPersona}")
                .description("Metodo encargado de consultar una persona por su id")
                .produces(MediaType.APPLICATION_JSON)
                .outType(Persona.class)
                .param().name("idPersona").type(RestParamType.path).description("id de la persona a consultar").endParam()
                .responseMessage().code("200").message("Persona consultada").responseModel(Persona.class).endResponseMessage()
                .id("getPersonaById")
                .to("direct:getPersonaById")
            .post()
                .description("Metodo encargado de incluir a una persona en el registro de personas")
                .consumes(MediaType.APPLICATION_JSON)
                .type(Persona.class)
                .param().name("persona").type(RestParamType.body).description("Persona a crear").endParam()
                .responseMessage().code("204").message("Persona Creada exitosamente").endResponseMessage()
                .responseMessage().code("409").message("Persona ya existe en el repositorio").endResponseMessage()
                .id("createPersona")
                .to("direct:createPersona")
            .delete("/{idPersona}")
                .description("Metodo encargado de eliminar una persona del repositorio")
                .param().name("idPersona").type(RestParamType.path).description("id de la persona a eliminar").endParam()
                .responseMessage().code("204").message("Persona Eliminada exitosamente").endResponseMessage()
                .responseMessage().code("409").message("Persona no existe en el repositorio").endResponseMessage()
                .id("deletePersona")
                .to("direct:eliminarPersona");
         
        onException(IllegalArgumentException.class)
        .handled(true)
        .log("Error al editar personas ${exception.message}")
        .setHeader(Exchange.HTTP_RESPONSE_CODE).constant("409")
        .setHeader(Exchange.HTTP_RESPONSE_TEXT).simple("${exception.message}")
        .setBody().simple("${null}");


        from("direct:getPersonasAll")
        .log("Body request ${body}")
        .bean("personaRepository", "getTodas")
        .log("Response ${body}");

        from("direct:getPersonaById")
        .log("Body request ${body} Header ${header.idPersona}")
        .bean("personaRepository", "getPersonaById")
        .log("Response ${body}");

        from("direct:createPersona")
        .log("Body request ${body}")
        .bean("personaRepository", "insertPersona")
        .log("Response ${body}")
        .setBody().simple("${null}");

        from("direct:eliminarPersona")
        .log("Body request ${body} Header ${header.idPersona}")
        .bean("personaRepository", "deletePersona")
        .log("Response ${body}");

        
    }
    
}

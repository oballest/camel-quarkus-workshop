package com.redhat.gps.quarkus.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Persona {
	
	@JsonProperty
	private Integer id;
	@JsonProperty
	private String nombre;
	@JsonProperty
	private String apellido;
	@JsonProperty
	private String ciudad;
	@JsonProperty
	private String email;

	public Persona(){
	}
	
	public Persona(Integer id, String nombre, String apellido, String ciudad, String email) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.ciudad = ciudad;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Persona [apellido=" + apellido + ", ciudad=" + ciudad + ", email=" + email + ", id=" + id + ", nombre="
				+ nombre + "]";
	}

}

package com.redhat.gps.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.Link;

@CsvRecord(crlf = "UNIX",separator = ",")
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Persona {
	
	@JsonProperty
	private Integer id;
	@DataField(pos = 1)
	@JsonProperty
	private String nombre;
	@DataField(pos = 2)
	@JsonProperty
	private String apellido;
	@DataField(pos = 3)
	@JsonProperty
	private String ciudad;
	@DataField(pos = 4)
	@JsonProperty
	private String email;
	
	@Link
	private InformacionFinanciera informacionFinanciera;
	
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
	public InformacionFinanciera getInformacionFinanciera() {
		return informacionFinanciera;
	}
	public void setInformacionFinanciera(InformacionFinanciera informacionFinanciera) {
		this.informacionFinanciera = informacionFinanciera;
	}

	@Override
	public String toString() {
		return "Persona [apellido=" + apellido + ", ciudad=" + ciudad + ", email=" + email + ", id=" + id
				+ ", informacionFinanciera=" + informacionFinanciera + ", nombre=" + nombre + "]";
	}

}

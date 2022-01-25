package com.redhat.gps.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.apache.camel.dataformat.bindy.annotation.Link;

@Link
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class InformacionFinanciera {
	
	private Integer id;
	@DataField(pos = 5, pattern = "dd/MM/yyyy")
	private Date fechaReporte;
	@DataField(pos = 6, precision = 2)
	private BigDecimal ingresos;
	@DataField(pos = 7)
	private String direccion;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getFechaReporte() {
		return fechaReporte;
	}
	public void setFechaReporte(Date fechaReporte) {
		this.fechaReporte = fechaReporte;
	}
	public BigDecimal getIngresos() {
		return ingresos;
	}
	public void setIngresos(BigDecimal ingresos) {
		this.ingresos = ingresos;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public String toString() {
		return "InformacionFinaciera [direccion=" + direccion + ", fechaReporte=" + fechaReporte + ", id=" + id
				+ ", ingresos=" + ingresos + "]";
	}

}

package Clases;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="personal")
public class personal {
	
	@Id
	private String dni_personal;
	
	private String nombre;
	private String horario;
	private int telefono;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="codigo_postal")
	private sucursales sucursal;
	
	
	
	public sucursales getSucursal() {
		return sucursal;
	}
	public void setSucursal(sucursales sucursal) {
		this.sucursal = sucursal;
	}
	public String getDni_personal() {
		return dni_personal;
	}
	public void setDni_personal(String dni_personal) {
		this.dni_personal = dni_personal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	

}

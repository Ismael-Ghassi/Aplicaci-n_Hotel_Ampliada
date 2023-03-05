package Clases;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="habitaciones")
public class habitaciones {
    
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String numero_habitacion;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="dni_cliente")
	private cliente dni_cliente;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="codigo_postal")
	private sucursales sucursal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
	
	
	
	
	public cliente getDni_Cliente() {
		return dni_cliente;
	}
	public void setDni_Cliente(cliente dni_Cliente) {
		this.dni_cliente = dni_Cliente;
	}
	public sucursales getSucursal() {
		return sucursal;
	}
	public void setSucursal(sucursales sucursal) {
		this.sucursal = sucursal;
	}
	
	public String getNumero_habitacion() {
		return numero_habitacion;
	}
	public void setNumero_habitacion(String numero_habitacion) {
		this.numero_habitacion = numero_habitacion;
	}
	
	
	
}

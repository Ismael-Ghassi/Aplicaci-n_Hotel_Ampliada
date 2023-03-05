package Clases;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="mantenimiento")
public class mantenimiento {

	@Id
	private String fecha;
	private String redactar;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_num")
	private habitaciones habitacion;
	
	
	
	public habitaciones getHabitacion() {
		return habitacion;
	}
	public void setHabitacion(habitaciones habitacion) {
		this.habitacion = habitacion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getRedactar() {
		return redactar;
	}
	public void setRedactar(String redactar) {
		this.redactar = redactar;
	}
	
	
}

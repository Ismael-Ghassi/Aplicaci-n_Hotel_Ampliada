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
@Table(name="reclamaciones")
public class reclamaciones {
        
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        private int id;
	
	private String reclamacion;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="dni_cliente")
	private cliente cliente;
	
	

	public cliente getCliente() {
		return cliente;
	}

	public void setCliente(cliente cliente) {
		this.cliente = cliente;
	}

	public String getReclamacion() {
		return reclamacion;
	}

	public void setReclamacion(String reclamacion) {
		this.reclamacion = reclamacion;
	}
	
        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
	
}

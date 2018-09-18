/**
 * 
 */
package co.edu.javeriana.code;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * @author Miguel
 *
 */
public class Mensaje implements Serializable {
	private int id;
	private String tipo;
	private LocalTime hora;
	private Evento event;
	private ArrayList<Tipo> tipos;
	
	public Mensaje(int id, String tipo, LocalTime hora, Evento event) {
		super();
		this.tipo = tipo;
		this.hora = hora;
		this.event = event;
		this.tipos = new ArrayList<Tipo>();
	}

	public Mensaje() {
		super();
		tipos = new ArrayList <Tipo>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Evento getEvent() {
		return event;
	}

	public void setEvent(Evento event) {
		this.event = event;
	}

	public ArrayList<Tipo> getTipos() {
		return tipos;
	}

	public void setTipos(ArrayList<Tipo> tipos) {
		this.tipos = tipos;
	}

	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", tipo=" + tipo + ", hora=" + hora + ", event=" + event + ", tipos=" + tipos
				+ "]";
	}
}

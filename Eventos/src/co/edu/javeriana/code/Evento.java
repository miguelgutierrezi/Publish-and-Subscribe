/**
 * 
 */
package co.edu.javeriana.code;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Miguel
 *
 */
public class Evento implements Serializable {
	private LocalTime hora_publicacion;
	private ArrayList<String> tipos;
	private ArrayList<String> match;
	private int id;
	
	public Evento(LocalTime hora_publicacion, ArrayList<String> tipos, int id) {
		super();
		this.hora_publicacion = hora_publicacion;
		this.tipos = new ArrayList<String> ();
		this.match = new ArrayList<String>();
		this.id = -1;
	}
	
	public Evento() {
		// TODO Auto-generated constructor stub
		this.id = -1;
		tipos = new ArrayList <String>();
		match = new ArrayList<String>();
	}

	public LocalTime getHora_publicacion() {
		return hora_publicacion;
	}

	public void setHora_publicacion(LocalTime hora_publicacion) {
		this.hora_publicacion = hora_publicacion;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setTipos(ArrayList<String> tipos) {
		this.tipos = tipos;
	}
	
	public void addTipos(String tipo) {
		this.tipos.add(tipo);
	}

	public void setMatch(ArrayList<String> match) {
		this.match = match;
	}
	
	public void addMatch(String match) {
		this.match.add(match);
	}
	
	@Override
	public String toString() {
		return "Evento [hora_publicacion = " + hora_publicacion + ", tipos = " + tipos + ", id = " + id + "]";
	}
	
	
}

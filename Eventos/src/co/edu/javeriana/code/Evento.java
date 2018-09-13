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
	private static final long serialVersionUID = 1L;
	private LocalTime hora_publicacion;
	private String tipos;
	private ArrayList<String> match;
	private int id;
	
	public Evento(LocalTime hora_publicacion, String tipo, ArrayList<String> match) {
		super();
		this.hora_publicacion = hora_publicacion;
		this.tipos = tipo;
		this.match = match;
		this.id = -1;
	}
	
	public Evento() {
		// TODO Auto-generated constructor stub
		this.id = -1;
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
	
	public void setTipos(String tipos) {
		this.tipos = tipos;
	}
	
	public String getTipos() {
		return tipos;
	}
	
	public ArrayList<String> getMatch() {
		return match;
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

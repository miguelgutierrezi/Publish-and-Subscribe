/**
 * 
 */
package co.edu.javeriana.code;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Miguel
 *
 */
public class Tipo implements Serializable{
	private String type;
	private ArrayList<Evento> eventos;
	private ArrayList<Mensaje> mensajes;
	
	public Tipo(String type) {
		super();
		this.type = type;
		eventos = new ArrayList<Evento>();
		mensajes = new ArrayList<Mensaje>();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(ArrayList<Evento> eventos) {
		this.eventos = eventos;
	}

	public ArrayList<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(ArrayList<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	@Override
	public String toString() {
		return "Tipo [type=" + type + ", eventos=" + eventos + ", mensajes=" + mensajes + "]";
	}
}

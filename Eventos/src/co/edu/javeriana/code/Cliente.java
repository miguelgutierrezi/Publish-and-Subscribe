/**
 * 
 */
package co.edu.javeriana.code;

import java.util.ArrayList;

/**
 * @author Sebastian
 *
 */
public class Cliente {
	ArrayList<String> types;
	ArrayList<String> matches;
	ArrayList<Evento> eventos;
	/**
	 * 
	 */
	public Cliente() {
		super();
		types = new ArrayList<String>();
		matches = new ArrayList<String>();
		eventos = new ArrayList<Evento>();
	}
	
	public ArrayList<String> getTypes() {
		return types;
	}
	
	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}
	
	public ArrayList<String> getMatches() {
		return matches;
	}
	
	public void setMatches(ArrayList<String> matches) {
		this.matches = matches;
	}
	
	public ArrayList<Evento> getEventos() {
		return eventos;
	}
	
	public void setEventos(ArrayList<Evento> eventos) {
		this.eventos = eventos;
	}
	
	@Override
	public String toString() {
		return "Cliente [types=" + types + ", matches=" + matches + ", eventos=" + eventos + "]";
	}
}

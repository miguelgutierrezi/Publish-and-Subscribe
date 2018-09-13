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
	private String LocalIp;
	private ArrayList<String> types;
	private ArrayList<String> matches;
	private ArrayList<Evento> eventos;
	/**
	 * 
	 */
	public Cliente(String LocalIp) {
		super();
		this.LocalIp = LocalIp;
		types = new ArrayList<String>();
		matches = new ArrayList<String>();
		eventos = new ArrayList<Evento>();
	}
	
	public void setLocalIp (String LocalIp) {
		this.LocalIp = LocalIp;
	}
	
	public String getLocalIp () {
		return LocalIp;
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
		return "Cliente [LocalIp=" + ", types=" + types + ", matches=" + matches + ", eventos=" + eventos + "]";
	}
}

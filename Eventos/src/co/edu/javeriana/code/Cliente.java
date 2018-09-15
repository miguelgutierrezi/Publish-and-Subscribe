/**
 * 
 */
package co.edu.javeriana.code;

import java.net.InetAddress;
import java.util.ArrayList;

/**
 * @author Sebastian
 *
 */
public class Cliente {
	private InetAddress LocalIp;
	private String ubicacion;
	private String nombre;
	private int cant_hijos;
	private ArrayList<String> tipos = new ArrayList<String>();
	private ArrayList<String> matches = new ArrayList<String>();
	/**
	 * 
	 */
	public Cliente(InetAddress LocalIp, String ubicacion, String nombre, int cant_hijos) {
		super();
		this.LocalIp = LocalIp;
		this.ubicacion = ubicacion;
		this.nombre = nombre;
		this.cant_hijos = cant_hijos;
	}
	
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setCant_hijos (int cant_hijos) {
		this.cant_hijos = cant_hijos;
	}
	
	public int getCant_hijos() {
		return cant_hijos;
	}
	
	public void setLocalIp (InetAddress LocalIp) {
		this.LocalIp = LocalIp;
	}
	
	public InetAddress getLocalIp () {
		return LocalIp;
	}
	
	public void setTipos (ArrayList<String> tipos) {
		this.tipos = tipos;
	}
	
	public ArrayList<String> getTipos () {
		return tipos;
	}
	
	public void setMatches (ArrayList<String> matches) {
		this.matches = matches;
	}
	
	public ArrayList<String> getMatches () {
		return matches;
	}
	
	@Override
	public String toString() {
		return "Cliente [LocalIp=" + ", Ubicacion=" + ubicacion + ", Cant hijos=" + cant_hijos + "]";
	}
}

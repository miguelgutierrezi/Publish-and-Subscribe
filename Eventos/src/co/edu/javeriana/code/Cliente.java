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
	private int cant_hijos;
	/**
	 * 
	 */
	public Cliente(InetAddress LocalIp, String ubicacion, int cant_hijos) {
		super();
		this.LocalIp = LocalIp;
		this.ubicacion = ubicacion;
		this.cant_hijos = cant_hijos;
	}
	
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public String getUbicacion() {
		return ubicacion;
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
	
	@Override
	public String toString() {
		return "Cliente [LocalIp=" + ", Ubicacion=" + ubicacion + ", Cant hijos=" + cant_hijos + "]";
	}
}

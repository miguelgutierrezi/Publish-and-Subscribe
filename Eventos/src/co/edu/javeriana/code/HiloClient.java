/**
 * 
 */
package co.edu.javeriana.code;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;

import co.edu.javeriana.gui.ClientInterface;
import co.edu.javeriana.gui.ClientRegister;

/**
 * @author Miguel
 *
 */
public class HiloClient extends Thread{
	private Socket clientSocket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private ClientRegister cliente;
	private String ipServer = "127.0.0.1";
	
	public HiloClient(ClientRegister cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public void run() {
		try {
			ArrayList<Evento> eventos = new ArrayList<Evento>();
			ArrayList<Evento> events = new ArrayList<Evento>();
			clientSocket = new Socket(ipServer, 4980);
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
			System.out.println("Saludo");
			
			eventos = (ArrayList<Evento>) in.readObject();
			
			for (Evento e: eventos) {
				if (e.getUbicacion().equals(this.cliente.getCliente().getUbicacion()) == true) {
					events.add(e);
				}
			}
			
			this.cliente.agregarDatos(events);
			this.cliente.setEvents(events);
			
			//out.writeObject(cliente.getEvento());
			
			clientSocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

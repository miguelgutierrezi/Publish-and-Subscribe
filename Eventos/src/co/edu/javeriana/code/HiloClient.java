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
import java.util.HashSet;
import java.util.Set;

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
	private ArrayList<Evento> evs = new ArrayList<Evento>();
	
	public HiloClient(ClientRegister cliente) {
		this.cliente = cliente;
	}
	
	public HiloClient(ClientRegister cliente, ArrayList<Evento> evs) {
		this.cliente = cliente;
		this.evs = evs;
	}
	
	@Override
	public void run() {
		try {
			ArrayList<Evento> eventos = new ArrayList<Evento>();
			ArrayList<Evento> events = new ArrayList<Evento>();
			ArrayList<String> types = new ArrayList<String>();
			ArrayList<String> matches = new ArrayList<String>();
			clientSocket = new Socket(ipServer, 4980);
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
			System.out.println("Saludo");
			
			eventos = ((ArrayList<Evento>) in.readObject());
			
			for (Evento e: eventos) {
				if (e.getUbicacion().equals(this.cliente.getCliente().getUbicacion()) == true) {
					events.add(e);
				}
			}
			
			types = Utils.Obtain_Types(events);
			matches = Utils.Obtain_Matches(events);
			
			//types.add("ninguno");
			//matches.add("ninguno");
			
			this.cliente.agregarTipos(types);
			this.cliente.agregarMatches(matches);
			this.cliente.agregarDatos(events, matches, types);
			
			this.cliente.setEvents(events);
			this.cliente.setTipos(types);
			this.cliente.setMatches(matches);
			
			//out.writeObject(cliente.getEvento());
			
			clientSocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

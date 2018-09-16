/**
 * 
 */
package co.edu.javeriana.code;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.edu.javeriana.gui.ServerInterface;

/**
 * @author Sebastian
 *
 */
public class HiloServer extends Thread {
	
	private DataInputStream indata;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private DataOutputStream outdata;
	private ServerInterface server;
	private Socket clientSocket;
	public static ArrayList<HiloServer> user = new ArrayList<HiloServer>();
	private String nombre;
	private int idSession;
	
	public HiloServer (Socket aClientSocket, ServerInterface serv) throws Exception
	{
		this.clientSocket = aClientSocket;
		this.server = serv;
		user.add(this);
	}
	
	@Override
	public void run() {
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		while (true) {
			try {
				eventos = (ArrayList<Evento>) in.readObject();
				for (int i=0; i<user.size(); i++) {
					out = new ObjectOutputStream(clientSocket.getOutputStream());
					out.writeObject(eventos);
				}
				
			} catch (Exception e) {
				break;
			}
		}
	}
	
	private void envioEventos(ArrayList<Evento> events) throws Exception {
		out = new ObjectOutputStream(clientSocket.getOutputStream());
		out.writeObject(events);
	}
}

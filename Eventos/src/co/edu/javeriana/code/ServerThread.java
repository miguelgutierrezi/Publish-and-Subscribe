/**
 * 
 */
package co.edu.javeriana.code;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import co.edu.javeriana.gui.ClientRegister;
import co.edu.javeriana.gui.Server;
import co.edu.javeriana.gui.ServerInterface;

/**
 * @author Miguel
 *
 */
public class ServerThread extends Thread{
	
	private ArrayList<Evento> eventos = new ArrayList<Evento>();
	private Socket socket;
	private Server ser;
	private int serverPort = 4980;
	private ObjectInputStream din;
	private ObjectOutputStream dout;
	
	public ServerThread (Socket socket, ArrayList<Evento> eventos, Server s) {
		this.socket = socket;
		this.ser = s;
		this.serverPort = 4980;
		this.eventos = eventos;
	}
	
	@Override
	public void run() {
		Evento e;
		while (true) {
			try {
				din = new ObjectInputStream(socket.getInputStream());
				dout = new ObjectOutputStream(socket.getOutputStream());
				
				e = (Evento) din.readObject();
				
				eventos.add(e);
				
				dout.writeObject(eventos);
			} catch (Exception ex) {
				break;
			}
		}
	}
}

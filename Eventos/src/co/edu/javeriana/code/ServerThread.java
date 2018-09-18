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
import co.edu.javeriana.gui.ServerInterface;

/**
 * @author Miguel
 *
 */
public class ServerThread extends Thread{
	
	private Socket socket;
	private ServerInterface cli;
	private int serverPort = 4980;
	private ObjectInputStream din;
	private ObjectOutputStream dout;
	
	public ServerThread (Socket socket, ServerInterface c) {
		this.socket = socket;
		this.cli = c;
		this.serverPort = 4980;

		try {
			this.dout = new ObjectOutputStream(socket.getOutputStream());
			this.din = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void desconectar() {
		try {
			this.socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		try {
			
			this.cli.agregarDatos();
			dout.writeObject(this.cli.getEventos());
		} catch (IOException e) {
			e.printStackTrace();
		}
		desconectar();
	}
}

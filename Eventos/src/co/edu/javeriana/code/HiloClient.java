/**
 * 
 */
package co.edu.javeriana.code;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import co.edu.javeriana.gui.ClientInterface;

/**
 * @author Miguel
 *
 */
public class HiloClient extends Thread{
	private Socket clientSocket;
	private ObjectInputStream in;
	private ClientInterface cliente;
	private DataInputStream inData;
	
	public HiloClient(Socket clientSocket, ClientInterface cliente) {
		this.clientSocket = clientSocket;
		this.cliente = cliente;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Object object;
				ArrayList<Evento> eventos = new ArrayList<Evento>();
				in = new ObjectInputStream(clientSocket.getInputStream());
				object = in.readObject();
				eventos = (ArrayList<Evento>) object;
				cliente.agregarEventos(eventos);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

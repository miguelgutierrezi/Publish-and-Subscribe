/**
 * 
 */
package co.edu.javeriana.code;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import co.edu.javeriana.gui.ServerInterface;

/**
 * @author Sebastian
 *
 */
public class ServerConnect extends Thread {

	private Evento event;
	private int serverPort = 4980;
	private String destiny;
	private ServerInterface serv;
	private Socket clientSocket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public ServerConnect(Socket aClientSocket, ServerInterface s) throws ClassNotFoundException, IOException {

		this.clientSocket = aClientSocket;
		this.serv = s;
		this.serverPort = 4980;

		this.in = new ObjectInputStream(clientSocket.getInputStream());
		this.out = new ObjectOutputStream(clientSocket.getOutputStream());

		this.start();
	}

	public void run() {

		Socket socket = null;

		try {

			Evento e = (Evento) in.readObject();
			event = e;

			this.serv.getEventos().add(event);

			this.serv.agregarDatos();

			out.writeObject(event);

			try {
				Thread.sleep(4000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			ServerIps se = new ServerIps(event, serv, destiny);

			if (socket != null)
				try { socket.close(); } catch (IOException ex) {System.out.println("close:" + ex.getMessage());}

		} catch (IOException e3) {

			System.out.println("readline:" + e3.getMessage());

		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			if (socket != null)
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println("close:" + e.getMessage());
				}
		}
	}
}

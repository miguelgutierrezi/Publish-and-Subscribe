/**
 * 
 */
package co.edu.javeriana.code;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import co.edu.javeriana.gui.ClientRegister;
import co.edu.javeriana.gui.ServerInterface;

/**
 * @author Miguel
 *
 */
public class ServerThread extends Thread{
	
	ServerInterface cli;
	private int serverPort = 4980;
	
	public ServerThread (ServerInterface c) {
		this.cli = c;
		this.serverPort = 4980;
		this.start();
	}
	
	@Override
	public void run() {
		try {
			
			ServerSocket listener = new ServerSocket(serverPort);
			
			while (true) {
				Socket client = listener.accept();
				System.out.println("Evento obtenido");
				ServerConnect conn = new ServerConnect(client, cli);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

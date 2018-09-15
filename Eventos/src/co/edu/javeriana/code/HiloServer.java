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
	private Socket clientSocket;
	private int idSession;
	
	public HiloServer (Socket aClientSocket, int id) 
	{
		this.clientSocket = aClientSocket;
		this.idSession = id;
		try {
            outdata = new DataOutputStream(clientSocket.getOutputStream());
            indata = new DataInputStream(clientSocket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(HiloServer.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	public void desconectar() {
		try {
            clientSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(HiloServer.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	@Override
	public void run() {
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		while (true) {
			try {
				eventos = (ArrayList<Evento>) in.readObject();
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

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

import co.edu.javeriana.gui.ServerInterface;

/**
 * @author Sebastian
 *
 */
public class HiloServer extends Thread {
	DataInputStream indata;
	ObjectInputStream in;
	ObjectOutputStream out;
	DataOutputStream outdata;
	Socket clientSocket;
	
	public  HiloServer (Socket aClientSocket) 
	{
		try 
	    {
			  clientSocket = aClientSocket;
			  in = new ObjectInputStream(clientSocket.getInputStream());
			  out = new ObjectOutputStream(clientSocket.getOutputStream());
			  outdata = new DataOutputStream(clientSocket.getOutputStream());
			  this.start();
		} 
		catch(IOException e)
	    {
		      System.out.println("Connection:"+e.getMessage());
	    }
	}
	
	public void run() {
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		while (true) {
			eventos = (ArrayList<Evento>) in.readObject();
		}
	}
}

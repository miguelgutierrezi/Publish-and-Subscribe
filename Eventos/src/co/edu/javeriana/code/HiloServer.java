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
	private DataInputStream indata;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private DataOutputStream outdata;
	private Socket clientSocket;
	private ServerInterface iface;
	private int port;
	
	public  HiloServer (ServerInterface s) 
	{
		this.iface = s;
		this.port = 4980;
	    this.start();
	}
	
	public void run() {
		try {
			ServerSocket listener = new ServerSocket(this.port);
			
			while(true) {
		   		     	
		   	      Socket clientSocket = listener.accept();
		   	      ServerConnect c = new ServerConnect(clientSocket, iface);
		   	}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

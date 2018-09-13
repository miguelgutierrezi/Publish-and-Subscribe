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
public class ServerConnect extends Thread{
	
	private ObjectInputStream in;
	   private ObjectOutputStream out;
	   private Socket clientSocket;
	   private ServerInterface iface;

	   public ServerConnect (Socket aClientSocket, ServerInterface s) {
	      try {
	    	  this.iface = s;
			  clientSocket = aClientSocket;
			  in = new ObjectInputStream(clientSocket.getInputStream());
			  
			  out =new ObjectOutputStream(clientSocket.getOutputStream());
			  
			  this.start();
	      } catch(IOException e){
		      System.out.println("Connection:"+e.getMessage());
	      }
	   }

	   public void run() {
		   
	   }
}

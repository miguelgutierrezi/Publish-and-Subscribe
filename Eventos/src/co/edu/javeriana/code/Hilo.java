/**
 * 
 */
package co.edu.javeriana.code;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 * @author Sebastian
 *
 */
public class Hilo extends Thread {
	DataInputStream indata;
	ObjectInputStream in;
	ObjectOutputStream out;
	DataOutputStream outdata;
	Socket clientSocket;
	public  Hilo (Socket aClientSocket) 
	{
	    try 
	    {
			  clientSocket = aClientSocket;
			  in = new ObjectInputStream(clientSocket.getInputStream());
			  out =new ObjectOutputStream(clientSocket.getOutputStream());
			  outdata=new DataOutputStream(clientSocket.getOutputStream());
			  this.start();
		} 
		catch(IOException e)
	    {
		      System.out.println("Connection:"+e.getMessage());
	    }
	}
	
	public void run() {
		Evento eventoActual;
		while (true) {
			try {
				eventoActual = (Evento) in.readObject();
				
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}

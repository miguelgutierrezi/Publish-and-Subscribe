/**
 * 
 */
package co.edu.javeriana.gui;

import java.awt.Event;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.ArrayList;

import co.edu.javeriana.code.Evento;

/**
 * @author Sebastian
 *
 */
public class ClientI {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
			Socket clientSocket = new Socket("localhost", 4980);
			ArrayList<Evento> eventos = new ArrayList<Evento>();
			ObjectInputStream objectInput = new ObjectInputStream(clientSocket.getInputStream());
			Object object = objectInput.readObject();
			eventos = (ArrayList<Evento>) object;
			
			for (Evento e: eventos) {
				while (true) {
		    		if ((e.getHora_publicacion().equals(LocalTime.now()) == true) || (e.getHora_publicacion().isBefore(LocalTime.now()))) {
		    			System.out.println("********************");
		    			System.out.println(e.getHora_publicacion());
		    			System.out.println(e.getTipos());
		    			System.out.println(e.getMatch());
		    			break;
		    		}	
		    	}
			}
			clientSocket.close();
	}
}

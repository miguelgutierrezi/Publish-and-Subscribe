/**
 * 
 */
package co.edu.javeriana.gui;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import co.edu.javeriana.code.Evento;
import co.edu.javeriana.code.Utils;

/**
 * @author Miguel
 *
 */
public class Server {

	/**
	 * @param args
	 * @throws InterruptedException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		String File_Name;
		String s = "s";
		
		do {
			Scanner in = new Scanner(System.in);
			System.out.print("Introduzca el nombre del archivo: ");
			File_Name = in.nextLine();
			eventos.addAll(Utils.Read_File(File_Name));
			Scanner car = new Scanner(System.in);
			System.out.print("Dese añadir otro archivo (s/n): ");
			s = car.nextLine();
		} while (s.equals("s"));
		
		/*File_Name = "eventos1.txt";
		eventos.addAll(Utils.Read_File(File_Name));*/
		
		for (Evento e: eventos) {    
			while (true) {
	    		if ((e.getHora_publicacion().equals(LocalTime.now()) == true)  || (e.getHora_publicacion().isBefore(LocalTime.now()))) {
	    			System.out.println("********************************************");
	    			System.out.println(e.getHora_publicacion());
	    			System.out.println(e.getTipos());
	    			System.out.println(e.getMatch());
	    			System.out.println(e.getUbicacion());
	    			break;
	    		}	
	    	}
        }
		
		ServerSocket welcomeSocket = new ServerSocket(4980);
		while (true) {
			Socket connectionSocket = welcomeSocket.accept();
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			ObjectOutputStream objectOutput = new ObjectOutputStream(connectionSocket.getOutputStream());
			objectOutput.writeObject(eventos);
			//outToClient.writeBytes(capitalizedSentence);
		}
	}
}

/**
 * 
 */
package co.edu.javeriana.gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;

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
		String File_Name = "eventos.txt";
		
		eventos = Utils.Read_File(File_Name);
		
		for (Evento e: eventos) {    
			while (true) {
	    		if (e.getHora_publicacion().equals(LocalTime.now()) == true) {
	    			System.out.println("********************");
	    			System.out.println(e.getHora_publicacion());
	    			System.out.println(e.getTipos());
	    			System.out.println(e.getMatch());
	    			System.out.println("Ya pare, voy a enviar el mensaje");
	    			break;
	    		}
	    		
	    	}
        }
	}

}

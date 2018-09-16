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
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import co.edu.javeriana.code.Cliente;
import co.edu.javeriana.code.Evento;

/**
 * @author Sebastian
 *
 */
public class ClientI {
	
	public static Cliente agregar_Cliente () throws UnknownHostException {
		Cliente cliente;
		String ubicacion;
		String nombre;
		int cant_hijos;
		InetAddress LocalIp = InetAddress.getLocalHost();
		
		Scanner u = new Scanner(System.in);
		System.out.print("Introduzca su ubicacion: ");
		ubicacion = u.nextLine();
		
		Scanner n = new Scanner(System.in);
		System.out.print("Introduzca su nombre: ");
		nombre = n.nextLine();
		
		Scanner c = new Scanner(System.in);
		System.out.print("Introduzca su cantidad de hijos: ");
		cant_hijos = c.nextInt();
		
		cliente = new Cliente(LocalIp, ubicacion, nombre, cant_hijos);
		
		return cliente;
	}
	
	public static void print_Events (ArrayList<Evento> eventos) {
		
		for (Evento e: eventos) {
    		if ((e.getHora_publicacion().equals(LocalTime.now()) == true) || (e.getHora_publicacion().isBefore(LocalTime.now()))) {
    			System.out.println("********************************************************************");
    			System.out.println(e.getHora_publicacion());
    			System.out.println(e.getTipos());
    			System.out.println(e.getMatch());	
    		}
		}
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
			
		Cliente cliente;
		cliente = agregar_Cliente();
		Socket clientSocket = new Socket("localhost", 4980);
		ArrayList<Evento> eventos = new ArrayList<Evento>();
		ArrayList<Evento> events = new ArrayList<Evento>();
		ObjectInputStream objectInput = new ObjectInputStream(clientSocket.getInputStream());
		Object object = objectInput.readObject();
		eventos = (ArrayList<Evento>) object;
			
		for (Evento ev: eventos) {
			if (ev.getUbicacion().equals(cliente.getUbicacion()) == true) {
				events.add(ev);
			}
		}
			
		print_Events (events);
			
		clientSocket.close();
	}
}

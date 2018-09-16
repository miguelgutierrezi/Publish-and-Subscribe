/**
 * 
 */
package co.edu.javeriana.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.SwingConstants;

import co.edu.javeriana.code.Cliente;
import co.edu.javeriana.code.Evento;
import co.edu.javeriana.code.HiloClient;

/**
 * @author Miguel
 *
 */
public class ClientInterface extends JFrame {
	
	public static final int serverPort = 4980;
	public static Socket client;
	public static String host = "localhost";
	public static ObjectInputStream in;
	public static ObjectOutputStream out;
	public static DataInputStream indata;
	public static DataOutputStream outdata;
	public ArrayList<String> types = new ArrayList<String>();
	public ArrayList<String> matches = new ArrayList<String>();
	public static Cliente cliente;
	public static ArrayList<Evento> eventos = new ArrayList<Evento>();
	
	public ClientInterface(ClientRegister cli){		
		try {
			Socket clien = client;
			clien = new Socket(host, serverPort);
			indata = new DataInputStream(clien.getInputStream());
			out = new ObjectOutputStream(clien.getOutputStream()); 
			outdata = new DataOutputStream(clien.getOutputStream());
			in = new ObjectInputStream(clien.getInputStream());
			outdata.writeUTF(cliente.getNombre());
			
			HiloClient hilo = new HiloClient(clien, this);
			hilo.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		getContentPane().setLayout(null);
		
		cliente = cli.cliente;
		
		System.out.println(cliente.getNombre());
		System.out.println(cliente.getCant_hijos());
		System.out.println(cliente.getUbicacion());
		
		JLabel lblSeleccionarTiposDe = new JLabel("Feed de " + cliente.getNombre());
		lblSeleccionarTiposDe.setBounds(0, 11, 434, 32);
		lblSeleccionarTiposDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionarTiposDe.setFont(new Font("Buxton Sketch", Font.PLAIN, 25));
		getContentPane().add(lblSeleccionarTiposDe);
		
		JLabel label = new JLabel("Su ubicación actual es: " + cliente.getUbicacion());
		label.setBounds(10, 54, 257, 14);
		label.setFont(new Font("Buxton Sketch", Font.PLAIN, 18));
		getContentPane().add(label);
		
	}
	
public void agregarEventos (ArrayList<Evento> eventos) {
	for (Evento e: eventos) {
		System.out.println(e.getHora_publicacion());
		System.out.println(e.getTipos());
		System.out.println(e.getMatch());
		System.out.println(e.getUbicacion());
	}
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
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

}

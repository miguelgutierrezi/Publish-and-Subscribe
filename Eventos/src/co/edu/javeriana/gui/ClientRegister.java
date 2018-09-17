// http://www.ejbtutorial.com/distributed-systems/using-sockets-to-create-a-group-chat-system-with-a-graphical-interface
/**
 * 
 */
package co.edu.javeriana.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import co.edu.javeriana.code.Cliente;
import co.edu.javeriana.code.Evento;
import co.edu.javeriana.code.HiloClient;
import co.edu.javeriana.code.ServerThread;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;

/**
 * @author Miguel
 *
 */
public class ClientRegister extends JFrame {
	private JTextField textNombre;
	private JTextField textHijos;
	private JTextField textLoc;
	ObjectOutputStream object;
	public static Cliente cliente = new Cliente(null, null, null, 0);
	JLabel label = new JLabel("Feed de " + cliente.getNombre());
	JLabel label_1 = new JLabel("Su ubicaci\u00F3n actual es: " + cliente.getUbicacion());
	
	public ClientRegister() throws UnknownHostException, InterruptedException {
		
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 261);
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Registro", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblRegistrarCliente = new JLabel("Registrar Cliente");
		lblRegistrarCliente.setBounds(10, 0, 409, 32);
		panel.add(lblRegistrarCliente);
		lblRegistrarCliente.setFont(new Font("Buxton Sketch", Font.PLAIN, 25));
		lblRegistrarCliente.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblIngreseSuNombre = new JLabel("Ingrese su nombre:");
		lblIngreseSuNombre.setBounds(10, 70, 168, 20);
		panel.add(lblIngreseSuNombre);
		lblIngreseSuNombre.setFont(new Font("Buxton Sketch", Font.PLAIN, 15));
		
		textNombre = new JTextField();
		textNombre.setBounds(188, 70, 231, 20);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblIngreseSuCantidad = new JLabel("Ingrese su cantidad de hijos:");
		lblIngreseSuCantidad.setBounds(10, 99, 163, 20);
		panel.add(lblIngreseSuCantidad);
		lblIngreseSuCantidad.setFont(new Font("Buxton Sketch", Font.PLAIN, 15));
		
		textHijos = new JTextField();
		textHijos.setBounds(188, 99, 44, 20);
		panel.add(textHijos);
		textHijos.setColumns(10);
		
		JLabel lblIngreseSuUbicacin = new JLabel("Ingrese su ubicaci\u00F3n");
		lblIngreseSuUbicacin.setBounds(10, 138, 163, 20);
		panel.add(lblIngreseSuUbicacin);
		lblIngreseSuUbicacin.setFont(new Font("Buxton Sketch", Font.PLAIN, 15));
		
		textLoc = new JTextField();
		textLoc.setBounds(188, 130, 231, 20);
		panel.add(textLoc);
		textLoc.setColumns(10);
		
		JButton btnNewButton = new JButton("Guardar Datos");
		btnNewButton.setBounds(137, 167, 137, 39);
		panel.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				InetAddress localIp = null;
				String nombre, ubicacion;
				int cant_hijos;
				try {
					localIp = InetAddress.getLocalHost();
					System.out.println(localIp);
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				
				nombre = textNombre.getText();
				ubicacion = textLoc.getText();
				cant_hijos = Integer.parseInt(textHijos.getText());
				
				cliente = new Cliente(localIp, ubicacion, nombre, cant_hijos);
				cliente.setLocalIp(localIp);
				
				 label.setText("Feed de " + cliente.getNombre());
				 
				 label_1.setText("Su ubicaci\u00F3n actual es: " + cliente.getUbicacion());
			}
		});
		btnNewButton.setFont(new Font("Buxton Sketch", Font.PLAIN, 20));
		
		JLabel lblSuIpActual = new JLabel("Su IP actual es:");
		lblSuIpActual.setFont(new Font("Buxton Sketch", Font.PLAIN, 15));
		lblSuIpActual.setBounds(10, 39, 168, 20);
		panel.add(lblSuIpActual);
		
		JLabel lblIP = new JLabel("");
		lblIP.setFont(new Font("Buxton Sketch", Font.PLAIN, 15));
		lblIP.setText(InetAddress.getLocalHost().getHostAddress());
		lblIP.setBounds(188, 39, 168, 20);
		panel.add(lblIP);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Feed", null, panel_1, null);
		panel_1.setLayout(null);
		
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Buxton Sketch", Font.PLAIN, 25));
		label.setBounds(10, 11, 409, 32);
		panel_1.add(label);
		
		label_1.setFont(new Font("Buxton Sketch", Font.PLAIN, 18));
		label_1.setBounds(10, 47, 257, 14);
		panel_1.add(label_1);
		
		try {
			Socket clientSocket = new Socket ("localhost", 4980);
			object = new ObjectOutputStream(clientSocket.getOutputStream());
		} catch (Exception e) {e.printStackTrace();}
		
		
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
	
	public static Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
                try {
                    ClientRegister frame = new ClientRegister();
                    frame.setTitle("Feed");
                    frame.pack();
                    frame.setBounds(0, 0, 450, 300);
                    frame.setVisible(true);
                    HiloClient thread = new HiloClient(frame);
                    thread.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}

}


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

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

/**
 * @author Miguel
 *
 */
public class ClientRegister extends JFrame {
	private JTextField textNombre;
	private JTextField textHijos;
	private JTextField textLoc;
	public ClientRegister() {
		getContentPane().setLayout(null);
		
		JLabel lblRegistrarCliente = new JLabel("Registrar Cliente");
		lblRegistrarCliente.setFont(new Font("Buxton Sketch", Font.PLAIN, 25));
		lblRegistrarCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarCliente.setBounds(0, 11, 434, 32);
		getContentPane().add(lblRegistrarCliente);
		
		JLabel lblIngreseSuNombre = new JLabel("Ingrese su nombre:");
		lblIngreseSuNombre.setFont(new Font("Buxton Sketch", Font.PLAIN, 15));
		lblIngreseSuNombre.setBounds(10, 68, 168, 20);
		getContentPane().add(lblIngreseSuNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(188, 65, 236, 20);
		getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblIngreseSuCantidad = new JLabel("Ingrese su cantidad de hijos:");
		lblIngreseSuCantidad.setFont(new Font("Buxton Sketch", Font.PLAIN, 15));
		lblIngreseSuCantidad.setBounds(10, 105, 163, 20);
		getContentPane().add(lblIngreseSuCantidad);
		
		textHijos = new JTextField();
		textHijos.setBounds(188, 105, 44, 20);
		getContentPane().add(textHijos);
		textHijos.setColumns(10);
		
		JLabel lblIngreseSuUbicacin = new JLabel("Ingrese su ubicaci\u00F3n");
		lblIngreseSuUbicacin.setFont(new Font("Buxton Sketch", Font.PLAIN, 15));
		lblIngreseSuUbicacin.setBounds(10, 147, 163, 20);
		getContentPane().add(lblIngreseSuUbicacin);
		
		textLoc = new JTextField();
		textLoc.setColumns(10);
		textLoc.setBounds(188, 147, 236, 20);
		getContentPane().add(textLoc);
		
		JButton btnNewButton = new JButton("Continuar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cliente cliente;
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
				
				System.out.println(cliente.getNombre());
				System.out.println(cliente.getCant_hijos());
				System.out.println(cliente.getUbicacion());
				
				ClientInterface cli = new ClientInterface();
				cli.setBounds(30, 0, 450, 300);
				cli.setTitle("Client Feed");
				cli.setVisible(true);
				
				Component component = (Component) arg0.getSource();
    			JFrame frame = (JFrame) SwingUtilities.getRoot(component);
    			
    			frame.dispose();
			}
		});
		btnNewButton.setFont(new Font("Buxton Sketch", Font.PLAIN, 20));
		btnNewButton.setBounds(159, 191, 117, 39);
		getContentPane().add(btnNewButton);
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
                    frame.setTitle("Registro de Cliente");
                    frame.pack();
                    frame.setBounds(0, 0, 450, 300);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}

}


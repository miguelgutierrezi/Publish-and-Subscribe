/**
 * 
 */
package co.edu.javeriana.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import co.edu.javeriana.code.Cliente;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

/**
 * @author Sebastian
 *
 */
public class LanzarCliente extends JFrame {
	private JTextField textNombre;
	private JTextField textLoc;
	private JTextField textHijos;
	private Cliente cliente;
	
	public LanzarCliente() {
		getContentPane().setLayout(null);
		
		JLabel lblRegistrarCliente = new JLabel("Registrar cliente");
		lblRegistrarCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRegistrarCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarCliente.setBounds(10, 11, 414, 33);
		getContentPane().add(lblRegistrarCliente);
		
		JLabel lblIngreseSuNombre = new JLabel("Ingrese su nombre:");
		lblIngreseSuNombre.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblIngreseSuNombre.setBounds(10, 72, 168, 20);
		getContentPane().add(lblIngreseSuNombre);
		
		JLabel lblIngreseCantidadDe = new JLabel("Ingrese cantidad de hijos:");
		lblIngreseCantidadDe.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblIngreseCantidadDe.setBounds(10, 115, 168, 20);
		getContentPane().add(lblIngreseCantidadDe);
		
		JLabel lblIngreseSuUbicacin = new JLabel("Ingrese su ubicaci\u00F3n: ");
		lblIngreseSuUbicacin.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblIngreseSuUbicacin.setBounds(10, 157, 168, 20);
		getContentPane().add(lblIngreseSuUbicacin);
		
		textNombre = new JTextField();
		textNombre.setBounds(219, 73, 205, 20);
		getContentPane().add(textNombre);
		textNombre.setColumns(10);
		
		textLoc = new JTextField();
		textLoc.setColumns(10);
		textLoc.setBounds(219, 158, 205, 20);
		getContentPane().add(textLoc);
		
		textHijos = new JTextField();
		textHijos.setColumns(10);
		textHijos.setBounds(219, 116, 50, 20);
		getContentPane().add(textHijos);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InetAddress localIp = null;
				String nombre, ubicacion;
				int cant_hijos;
				try {
					localIp = InetAddress.getLocalHost();
					System.out.println(localIp);
				} catch (UnknownHostException ex) {
					ex.printStackTrace();
				}
				
				nombre = textNombre.getText();
				ubicacion = textLoc.getText();
				cant_hijos = Integer.parseInt(textHijos.getText());
				
				cliente = new Cliente(localIp, ubicacion, nombre, cant_hijos);
				cliente.setLocalIp(localIp);
				 
				Component component = (Component) e.getSource();
	    		JFrame frame = (JFrame) SwingUtilities.getRoot(component);
	    		
	    		ClientRegister reg;
				try {
					reg = new ClientRegister((LanzarCliente) frame);
					reg.setTitle("Feed de " + cliente.getNombre());
	                reg.pack();
	                reg.setBounds(0, 0, 450, 300);
	                reg.setVisible(true);
				} catch (UnknownHostException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				frame.dispose();
			}
		});
		btnRegistrarse.setFont(new Font("Dialog", Font.PLAIN, 17));
		btnRegistrarse.setBounds(142, 196, 137, 39);
		getContentPane().add(btnRegistrarse);
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LanzarCliente frame = new LanzarCliente();
		frame.pack();
		frame.setTitle("Registrar cliente");
		frame.setBounds(0, 0, 450, 300);
		frame.setVisible(true);
	}
}

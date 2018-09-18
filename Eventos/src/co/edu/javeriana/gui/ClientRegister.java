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
import co.edu.javeriana.code.Utils;

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
import javax.swing.JTable;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;

import java.awt.CardLayout;

/**
 * @author Miguel
 *
 */
public class ClientRegister extends JFrame {
	ObjectOutputStream object;
	public static Cliente cliente;
	private static ArrayList<Evento> eventos = new ArrayList<Evento>();
	Evento evento;
	private JTable table_1;
	private JTextField textEventType;
	private JTextField textEventChars;
	
	public ClientRegister(LanzarCliente lan) throws UnknownHostException, InterruptedException {
		
		cliente = lan.getCliente();
		
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("Feed de " + cliente.getNombre());
		JLabel label_1 = new JLabel("Su ubicaci\u00F3n actual es: " + cliente.getUbicacion());
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 261);
		getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Datos", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblRegistrarCliente = new JLabel("Datos de " + cliente.getNombre());
		lblRegistrarCliente.setBounds(10, 0, 409, 32);
		panel.add(lblRegistrarCliente);
		lblRegistrarCliente.setFont(new Font("Buxton Sketch", Font.PLAIN, 25));
		lblRegistrarCliente.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblIngreseSuNombre = new JLabel("Nombre:");
		lblIngreseSuNombre.setBounds(10, 70, 168, 20);
		panel.add(lblIngreseSuNombre);
		lblIngreseSuNombre.setFont(new Font("Buxton Sketch", Font.PLAIN, 15));
		
		JLabel lblIngreseSuCantidad = new JLabel("Cantidad de hijos:");
		lblIngreseSuCantidad.setBounds(10, 99, 163, 20);
		panel.add(lblIngreseSuCantidad);
		lblIngreseSuCantidad.setFont(new Font("Buxton Sketch", Font.PLAIN, 15));
		
		JLabel lblIngreseSuUbicacin = new JLabel("Ubicaci\u00F3n: ");
		lblIngreseSuUbicacin.setBounds(10, 130, 163, 20);
		panel.add(lblIngreseSuUbicacin);
		lblIngreseSuUbicacin.setFont(new Font("Buxton Sketch", Font.PLAIN, 15));
		
		JLabel lblSuIpActual = new JLabel("Su IP actual es:");
		lblSuIpActual.setFont(new Font("Buxton Sketch", Font.PLAIN, 15));
		lblSuIpActual.setBounds(10, 39, 168, 20);
		panel.add(lblSuIpActual);
		
		JLabel lblIP = new JLabel(InetAddress.getLocalHost().getHostAddress());
		lblIP.setFont(new Font("Buxton Sketch", Font.PLAIN, 15));
		lblIP.setText(InetAddress.getLocalHost().getHostAddress());
		lblIP.setBounds(188, 39, 168, 20);
		panel.add(lblIP);
		
		JLabel labelName = new JLabel(cliente.getNombre());
		labelName.setFont(new Font("Dialog", Font.PLAIN, 15));
		labelName.setBounds(188, 70, 168, 20);
		panel.add(labelName);
		
		JLabel labelChilds = new JLabel(String.valueOf(cliente.getCant_hijos()));
		labelChilds.setFont(new Font("Dialog", Font.PLAIN, 15));
		labelChilds.setBounds(193, 99, 163, 20);
		panel.add(labelChilds);
		
		JLabel labelUbi = new JLabel(cliente.getUbicacion());
		labelUbi.setFont(new Font("Dialog", Font.PLAIN, 15));
		labelUbi.setBounds(193, 130, 163, 20);
		panel.add(labelUbi);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Feed", null, panel_1, null);
		label.setBounds(10, 6, 409, 33);
		
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Buxton Sketch", Font.PLAIN, 25));
		panel_1.setLayout(null);
		label_1.setBounds(10, 44, 218, 24);
		
		label_1.setFont(new Font("Dialog", Font.PLAIN, 13));
		panel_1.add(label_1);
		panel_1.add(label);
		
		table_1 = new JTable();
		table_1.setBounds(10, 79, 409, 112);
		panel_1.add(table_1);
		
		JButton btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Component component = (Component) e.getSource();
	    		JFrame frame = (JFrame) SwingUtilities.getRoot(component);
				
				HiloClient thread = new HiloClient((ClientRegister) frame, eventos);
				thread.start();
			}
		});
		btnActualizar.setBounds(160, 202, 89, 23);
		panel_1.add(btnActualizar);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("New tab", null, panel_2, null);
		panel_2.setLayout(null);
		
		JLabel label_2 = new JLabel("Publicar evento");
		label_2.setBounds(10, 5, 409, 33);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Dialog", Font.PLAIN, 25));
		panel_2.add(label_2);
		
		JLabel label_3 = new JLabel("La hora de publicaci\u00F3n es:");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 13));
		label_3.setBounds(10, 49, 218, 18);
		panel_2.add(label_3);
		
		JLabel label_4 = new JLabel("23:33:51.065");
		label_4.setFont(new Font("Dialog", Font.PLAIN, 13));
		label_4.setBounds(238, 49, 181, 18);
		panel_2.add(label_4);
		
		JLabel label_5 = new JLabel("Ingrese el tipo de evento");
		label_5.setFont(new Font("Dialog", Font.PLAIN, 13));
		label_5.setBounds(10, 78, 218, 18);
		panel_2.add(label_5);
		
		JLabel label_6 = new JLabel("Ingrese las caracter\u00EDsticas del evento");
		label_6.setFont(new Font("Dialog", Font.PLAIN, 13));
		label_6.setBounds(10, 107, 234, 18);
		panel_2.add(label_6);
		
		JLabel lblUbicacinDelEvento = new JLabel("Ubicaci\u00F3n del evento");
		lblUbicacinDelEvento.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblUbicacinDelEvento.setBounds(10, 136, 218, 18);
		panel_2.add(lblUbicacinDelEvento);
		
		JLabel lblLoc = new JLabel(cliente.getUbicacion());
		lblLoc.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblLoc.setBounds(238, 136, 140, 18);
		panel_2.add(lblLoc);
		
		JButton button = new JButton("Guardar evento");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				LocalTime hora = LocalTime.now();
				String tipo = textEventType.getText();
				String ubicacion = lblLoc.getText();
				
				String match = textEventChars.getText();
				
				ArrayList<String> matches = new ArrayList<String>(); 
				matches = Utils.Read_Matches(match);
				
				evento = new Evento(hora, tipo, matches, ubicacion);
				
				eventos.add(evento);
				
				//agregarDatos(eventos);
				
				/*Component component = (Component) e.getSource();
	    		JFrame frame = (JFrame) SwingUtilities.getRoot(component);
				
				HiloClient thread = new HiloClient((ClientRegister) frame);
				thread.start();*/
			}
		});
		button.setFont(new Font("Dialog", Font.PLAIN, 14));
		button.setBounds(157, 165, 140, 44);
		panel_2.add(button);
		
		textEventType = new JTextField();
		textEventType.setColumns(10);
		textEventType.setBounds(238, 78, 86, 20);
		panel_2.add(textEventType);
		
		textEventChars = new JTextField();
		textEventChars.setColumns(10);
		textEventChars.setBounds(238, 107, 86, 20);
		panel_2.add(textEventChars);
		
		try {
			Socket clientSocket = new Socket ("127.0.0.1", 4980);
			object = new ObjectOutputStream(clientSocket.getOutputStream());
			HiloClient thread = new HiloClient(this);
			thread.start();
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
	
	public Evento getEvento() {
		return evento;
	}

	public void agregarDatos(ArrayList<Evento> eventos) {

		boolean ex = true;
		DefaultTableModel tableModel = new DefaultTableModel();
		String[] columnNames = {"Hora", "Tipo", "Datos", "Ubicacion"};
		tableModel.setColumnIdentifiers(columnNames);
		Object[] fila = new Object[tableModel.getColumnCount()];

		for (int i = 0; i < eventos.size(); i++) {
			
			while(ex) {
				if ((eventos.get(i).getHora_publicacion().equals(LocalTime.now()) == true) || (eventos.get(i).getHora_publicacion().isBefore(LocalTime.now()))) {
					fila[0] = eventos.get(i).getHora_publicacion();
					fila[1] = eventos.get(i).getTipos();
					fila[2] = eventos.get(i).getMatch();
					fila[3] = eventos.get(i).getUbicacion();
					tableModel.addRow(fila);
					ex = false;
				}
			}
			ex = true;
		}

		table_1.setModel(tableModel);
	}
	
	public ArrayList<Evento> getEvents() {
		return eventos;
	}
	
	public static void setEvents (ArrayList<Evento> events) {
		eventos = events;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
                /*try {
                    ClientRegister frame = new ClientRegister();
                    frame.setTitle("Feed");
                    frame.pack();
                    frame.setBounds(0, 0, 450, 300);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }
        });
	}
}


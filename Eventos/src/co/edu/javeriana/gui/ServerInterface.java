/**
 * 
 */
package co.edu.javeriana.gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;

import co.edu.javeriana.code.Evento;
import co.edu.javeriana.code.HiloServer;
import co.edu.javeriana.code.ServerIps;
import co.edu.javeriana.code.ServerThread;
import co.edu.javeriana.code.Utils;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.TextArea;
import java.awt.TextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileSystemView;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JTabbedPane;

public class ServerInterface extends javax.swing.JFrame {
	private JTable table_1;
	private static ArrayList<Evento> eventos = new ArrayList<Evento>();
	private static ServerSocket server;
	private final static int PORT = 4980;
	private JTextField textEventType;
	private JTextField textEventChars;
	private JTextField textEventLoc;
	
	public ServerInterface() {
		
		//ServerThread s = new ServerThread(this);
		
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 288);
		getContentPane().add(tabbedPane);
		JPanel panel = new JPanel();
		tabbedPane.addTab("Feed", null, panel, null);
		panel.setLayout(null);
		
		JButton btnSeleccionarArchivo = new JButton("Seleccionar archivo");
		btnSeleccionarArchivo.setFont(new Font("Buxton Sketch", Font.PLAIN, 13));
		btnSeleccionarArchivo.setBounds(55, 204, 144, 29);
		btnSeleccionarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser file = new JFileChooser();
				int returnValue = file.showOpenDialog(null);
				
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = file.getSelectedFile();
					System.out.println(selectedFile.getAbsolutePath());
					try {
						eventos.addAll(Utils.Read_File(selectedFile.getName()));
					} catch (IOException | InterruptedException e) {
						e.printStackTrace();
					}
					
					Collections.sort(eventos, (e1, e2) -> e1.getHora_publicacion().compareTo(e2.getHora_publicacion()));
					agregarDatos();
				}
				
			}
		});
		
		table_1 = new JTable();
		table_1.setBounds(0, 48, 429, 145);
		panel.add(table_1);
		table_1.setFont(new Font("Buxton Sketch", Font.PLAIN, 11));
		table_1.setSurrendersFocusOnKeystroke(true);
		table_1.setColumnSelectionAllowed(true);
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Hora", "Tipo", "Datos", "Ubicacion"
			}
		));
		panel.add(btnSeleccionarArchivo);
		
		JLabel lblServidor = new JLabel("Servidor");
		lblServidor.setHorizontalAlignment(SwingConstants.CENTER);
		lblServidor.setFont(new Font("Buxton Sketch", Font.PLAIN, 25));
		lblServidor.setBounds(11, 11, 413, 26);
		panel.add(lblServidor);
		
		JButton btnObtenerRespaldo = new JButton("Obtener respaldo");
		btnObtenerRespaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnObtenerRespaldo.setFont(new Font("Buxton Sketch", Font.PLAIN, 13));
		btnObtenerRespaldo.setBounds(228, 204, 133, 29);
		panel.add(btnObtenerRespaldo);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Publicar evento", null, panel_1, null);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("Publicar evento");
		label.setBounds(10, 5, 409, 32);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Buxton Sketch", Font.PLAIN, 25));
		panel_1.add(label);
		
		textEventType = new JTextField();
		textEventType.setBounds(230, 87, 86, 20);
		textEventType.setColumns(10);
		panel_1.add(textEventType);
		
		JLabel lblTipo = new JLabel("Ingrese el tipo de evento");
		lblTipo.setBounds(10, 89, 120, 18);
		lblTipo.setFont(new Font("Buxton Sketch", Font.PLAIN, 14));
		panel_1.add(lblTipo);
		
		JLabel lblLoc = new JLabel("Ingrese la ubicaci\u00F3n del evento");
		lblLoc.setBounds(10, 146, 147, 18);
		lblLoc.setFont(new Font("Buxton Sketch", Font.PLAIN, 14));
		panel_1.add(lblLoc);
		
		textEventChars = new JTextField();
		textEventChars.setBounds(230, 116, 86, 20);
		textEventChars.setColumns(10);
		panel_1.add(textEventChars);
		
		JLabel lblChar = new JLabel("Ingrese las caracter\u00EDsticas del evento");
		lblChar.setBounds(10, 118, 181, 18);
		lblChar.setFont(new Font("Buxton Sketch", Font.PLAIN, 14));
		panel_1.add(lblChar);
		
		textEventLoc = new JTextField();
		textEventLoc.setBounds(230, 144, 86, 20);
		textEventLoc.setColumns(10);
		panel_1.add(textEventLoc);
		
		JButton button = new JButton("Guardar evento");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalTime hora = LocalTime.now();
				String tipo = textEventType.getText();
				String ubicacion = textEventLoc.getText();
				
				String match = textEventChars.getText();
				
				ArrayList<String> matches = new ArrayList<String>(); 
				matches = Utils.Read_Matches(match);
				
				System.out.println(matches);
				
				Evento evento = new Evento(hora, tipo, matches, ubicacion);
				
				System.out.println(evento.getHora_publicacion());
    			System.out.println(evento.getTipos());
    			System.out.println(evento.getMatch());
    			System.out.println(evento.getUbicacion());
    			
    			eventos.add(evento);
    			
    			agregarDatos();
			}
		});
		button.setBounds(155, 190, 123, 44);
		button.setFont(new Font("Buxton Sketch", Font.PLAIN, 17));
		panel_1.add(button);
		
		JLabel lblHora = new JLabel("La hora de publicaci\u00F3n es:");
		lblHora.setFont(new Font("Buxton Sketch", Font.PLAIN, 14));
		lblHora.setBounds(10, 48, 181, 18);
		panel_1.add(lblHora);
		
		JLabel lblHour = new JLabel("");
		lblHour.setFont(new Font("Buxton Sketch", Font.PLAIN, 14));
		lblHour.setBounds(230, 48, 181, 18);
		lblHour.setText(LocalTime.now().toString());
		panel_1.add(lblHour);
		
		//ServerIps se = new ServerIps(e, this, "127.0.0.1" );
	}
	
	public void agregarDatos() {

		boolean ex = true;
		DefaultTableModel tableModel = new DefaultTableModel();
		String[] columnNames = {"Hora", "Tipo", "Datos", "Ubicacion"};
		tableModel.setColumnIdentifiers(columnNames);
		Object[] fila = new Object[tableModel.getColumnCount()];

		for (int i = 0; i < eventos.size(); i++) {
			
			while(ex) {
				if ((eventos.get(i).getHora_publicacion().equals(LocalTime.now()) == true)  || (eventos.get(i).getHora_publicacion().isBefore(LocalTime.now()))) {
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
	
	public ArrayList<Evento> getEventos() {
		return eventos;
	}
	
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
                ServerSocket ss;
                System.out.println("Inicializando servidor");
				try {
                    ServerInterface frame = new ServerInterface();
            		frame.setTitle("Servidor");
                    frame.pack();
                    frame.setBounds(0, 0, 450, 320);
                    frame.setVisible(true);
                    ss = new ServerSocket(4980);
                    while (true) {
                    	Socket socket;
                    	socket = ss.accept();
                    	System.out.println("Nueva conexión entrante: " + socket);
                    	((ServerThread) new ServerThread(socket, frame)).start();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
	}
}

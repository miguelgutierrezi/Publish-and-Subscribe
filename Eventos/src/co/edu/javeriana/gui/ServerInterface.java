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

public class ServerInterface extends javax.swing.JFrame {
	private JTable table_1;
	private static ArrayList<Evento> eventos = new ArrayList<Evento>();
	private static ServerSocket server;
	private final static int PORT = 4980;
	
	public ServerInterface() {
		try {
			this.setTitle("Servidor");
            this.pack();
            this.setBounds(0, 0, 450, 300);
			this.setVisible(true);
			server = new ServerSocket(PORT);
			System.out.println("Servidor conectado al puerto: " + PORT);
            //while (true) {
            	Socket client = server.accept();
    			HiloServer hilo = new HiloServer(client, this);
    			System.out.println("Entra hilo");
    			hilo.start();
            //}
            
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JPanel panel = new JPanel();
		
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnSeleccionarArchivo = new JButton("Seleccionar archivo");
		btnSeleccionarArchivo.setFont(new Font("Buxton Sketch", Font.PLAIN, 13));
		btnSeleccionarArchivo.setBounds(11, 201, 144, 29);
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
		table_1.setFont(new Font("Buxton Sketch", Font.PLAIN, 11));
		table_1.setSurrendersFocusOnKeystroke(true);
		table_1.setColumnSelectionAllowed(true);
		table_1.setBounds(11, 48, 413, 142);
		table_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		table_1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Hora", "Tipo", "Datos", "Ubicacion"
			}
		));
		panel.add(table_1);
		panel.add(btnSeleccionarArchivo);
		
		JLabel lblServidor = new JLabel("Servidor");
		lblServidor.setHorizontalAlignment(SwingConstants.CENTER);
		lblServidor.setFont(new Font("Buxton Sketch", Font.PLAIN, 25));
		lblServidor.setBounds(11, 11, 413, 26);
		panel.add(lblServidor);
		
		JButton btnPublicarEvento = new JButton("Publicar evento");
		btnPublicarEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {		
				PublishEvent pub = new PublishEvent();
				pub.setBounds(30, 0, 450, 300);
				pub.setVisible(true);
			}
		});
		btnPublicarEvento.setFont(new Font("Buxton Sketch", Font.PLAIN, 13));
		btnPublicarEvento.setBounds(165, 201, 116, 29);
		panel.add(btnPublicarEvento);
		
		JButton btnObtenerRespaldo = new JButton("Obtener respaldo");
		btnObtenerRespaldo.setFont(new Font("Buxton Sketch", Font.PLAIN, 13));
		btnObtenerRespaldo.setBounds(291, 201, 133, 29);
		panel.add(btnObtenerRespaldo);
	}
	
	private void agregarDatos() {

		DefaultTableModel tableModel = new DefaultTableModel();
		String[] columnNames = {"Hora", "Tipo", "Datos", "Ubicacion"};
		tableModel.setColumnIdentifiers(columnNames);
		Object[] fila = new Object[tableModel.getColumnCount()];

		for (int i = 0; i < eventos.size(); i++) {
			
			while(true) {
				if ((eventos.get(i).getHora_publicacion().equals(LocalTime.now()) == true)  || (eventos.get(i).getHora_publicacion().isBefore(LocalTime.now()))) {
					fila[0] = eventos.get(i).getHora_publicacion();
					fila[1] = eventos.get(i).getTipos();
					fila[2] = eventos.get(i).getMatch();
					fila[3] = eventos.get(i).getUbicacion();
					tableModel.addRow(fila);
					break;
				}
			}
		}

		table_1.setModel(tableModel);
	}
	
	public static void main(String[] args) throws IOException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
                try {
                    ServerInterface frame = new ServerInterface();
            		frame.setTitle("Servidor");
                    frame.pack();
                    frame.setBounds(0, 0, 450, 300);
                    frame.setVisible(true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
	}
}

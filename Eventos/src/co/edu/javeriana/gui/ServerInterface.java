/**
 * 
 */
package co.edu.javeriana.gui;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import co.edu.javeriana.code.Evento;
import co.edu.javeriana.code.Utils;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import java.awt.TextArea;
import java.awt.TextField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileSystemView;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class ServerInterface extends JFrame {
	private JTable table_1;
	private ArrayList<Evento> eventos = new ArrayList<Evento>();
	
	public ServerInterface() {
		JPanel panel = new JPanel();
		
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnSeleccionarArchivo = new JButton("Seleccionar archivo");
		btnSeleccionarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser file = new JFileChooser();
				int returnValue = file.showOpenDialog(null);
				
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = file.getSelectedFile();
					System.out.println(selectedFile.getAbsolutePath());
					try {
						eventos = Utils.Read_File(selectedFile.getName());
					} catch (IOException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					agregarDatos();
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
				}
				
			}
		});
		btnSeleccionarArchivo.setBounds(22, 212, 146, 23);
		panel.add(btnSeleccionarArchivo);
		
		JTextPane txtpnServidor = new JTextPane();
		txtpnServidor.setBackground(SystemColor.menu);
		txtpnServidor.setText(" Servidor");
		txtpnServidor.setFont(new Font("Tahoma", Font.PLAIN, 25));
		txtpnServidor.setBounds(166, 11, 105, 36);
		panel.add(txtpnServidor);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		table_1.setBounds(403, 78, -374, 84);
		panel.add(table_1);
	}
	
	private void agregarDatos() {

		DefaultTableModel modelo = (DefaultTableModel) table_1.getModel();

		String datos[] = new String[4];// ARRAY DE 4

		// LE PASO AL ARRAY LOS DATOS DEL ARRAYLIST

		for (Evento e: eventos) {

			datos[0] = e.getHora_publicacion().toString();
			datos[1] = e.getTipos();
			for (String s: e.getMatch()) {
				datos[2] += s;
			}
			datos[3] = e.getUbicacion();
			modelo.addRow(datos);
		}
		TableColumn colum1 = null;
		colum1 = table_1.getColumnModel().getColumn(0);
		colum1.setPreferredWidth(60);
		TableColumn colum2 = null;
		colum2 = table_1.getColumnModel().getColumn(1);
		colum2.setPreferredWidth(5);
		TableColumn colum3 = null;
		colum3 = table_1.getColumnModel().getColumn(2);
		colum3.setPreferredWidth(40);
		colum3.setPreferredWidth(10);
		TableColumn colum4 = null;
		colum4 = table_1.getColumnModel().getColumn(3);
		colum4.setPreferredWidth(10);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ServerInterface frame = new ServerInterface();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
	}
}

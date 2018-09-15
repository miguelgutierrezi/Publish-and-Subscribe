/**
 * 
 */
package co.edu.javeriana.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.SwingConstants;

import co.edu.javeriana.code.Evento;
import co.edu.javeriana.code.Utils;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

/**
 * @author Miguel
 *
 */
public class PublishEvent extends JFrame {
	private JTextField textEventType;
	private JTextField textEventLoc;
	private JTextField textEventChars;
	public PublishEvent(/*ServerInterface ser*/) {
		getContentPane().setLayout(null);
		
		JLabel lblPublicarEvento = new JLabel("Publicar evento");
		lblPublicarEvento.setBounds(0, 15, 434, 32);
		lblPublicarEvento.setHorizontalAlignment(SwingConstants.CENTER);
		lblPublicarEvento.setFont(new Font("Buxton Sketch", Font.PLAIN, 25));
		getContentPane().add(lblPublicarEvento);
		
		textEventType = new JTextField();
		textEventType.setBounds(212, 58, 212, 20);
		getContentPane().add(textEventType);
		textEventType.setColumns(10);
		
		JLabel lblIngreseElTipo = new JLabel("Ingrese el tipo de evento");
		lblIngreseElTipo.setFont(new Font("Buxton Sketch", Font.PLAIN, 14));
		lblIngreseElTipo.setBounds(10, 61, 190, 17);
		getContentPane().add(lblIngreseElTipo);
		
		JLabel lblIngreseLaUbicacin = new JLabel("Ingrese la ubicaci\u00F3n del evento");
		lblIngreseLaUbicacin.setFont(new Font("Buxton Sketch", Font.PLAIN, 14));
		lblIngreseLaUbicacin.setBounds(10, 122, 190, 17);
		getContentPane().add(lblIngreseLaUbicacin);
		
		textEventLoc = new JTextField();
		textEventLoc.setColumns(10);
		textEventLoc.setBounds(212, 119, 212, 20);
		getContentPane().add(textEventLoc);
		
		JLabel lblIngreseLasCaractersticas = new JLabel("Ingrese las caracter\u00EDsticas del evento");
		lblIngreseLasCaractersticas.setFont(new Font("Buxton Sketch", Font.PLAIN, 14));
		lblIngreseLasCaractersticas.setBounds(10, 94, 190, 17);
		getContentPane().add(lblIngreseLasCaractersticas);
		
		textEventChars = new JTextField();
		textEventChars.setColumns(10);
		textEventChars.setBounds(212, 88, 212, 20);
		getContentPane().add(textEventChars);
		
		JButton btnSaveEvent = new JButton("Guardar evento");
		btnSaveEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
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
			}
		});
		btnSaveEvent.setFont(new Font("Buxton Sketch", Font.PLAIN, 17));
		btnSaveEvent.setBounds(154, 172, 143, 57);
		getContentPane().add(btnSaveEvent);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
                /*try {
                    /*PublishEvent frame = new PublishEvent();
                    frame.setTitle("Publicar Evento");
                    frame.pack();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }
        });
	}
	
}

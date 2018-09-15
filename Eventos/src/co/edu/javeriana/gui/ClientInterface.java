/**
 * 
 */
package co.edu.javeriana.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;

import co.edu.javeriana.code.Evento;

/**
 * @author Miguel
 *
 */
public class ClientInterface extends JFrame {
	
	ArrayList<String> types = new ArrayList<String>();
	ArrayList<String> matches = new ArrayList<String>();
	ArrayList<Evento> eventos = new ArrayList<Evento>();
	
	public ClientInterface() {
		getContentPane().setLayout(null);
		
		JLabel lblSeleccionarTiposDe = new JLabel("Seleccionar tipos de int\u00E9res");
		lblSeleccionarTiposDe.setBounds(0, 11, 434, 32);
		lblSeleccionarTiposDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionarTiposDe.setFont(new Font("Buxton Sketch", Font.PLAIN, 25));
		getContentPane().add(lblSeleccionarTiposDe);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {
                try {
                    ClientInterface frame = new ClientInterface();
                    frame.setTitle("Client Dashboard");
                    frame.pack();
                    frame.setBounds(0, 0, 450, 300);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });*/
	}

}

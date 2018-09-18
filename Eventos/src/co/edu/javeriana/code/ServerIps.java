package co.edu.javeriana.code;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import co.edu.javeriana.gui.ServerInterface;

public class ServerIps {
	private Evento evento;
	private String ipDestino;
	private ServerInterface s;
	
	public ServerIps (Evento e, ServerInterface s, String ip) {
		this.evento = e;
		this.ipDestino = ip;
		this.s = s;
	}
	
	public void conn() throws ClassNotFoundException {
		Socket sock = null;
		
		try {
			int serverPort = 3000;
			sock = new Socket(ipDestino, serverPort);
			ObjectOutputStream out = new ObjectOutputStream(sock.getOutputStream());
			
			out.writeObject(this.evento);
			
			this.s.getEventos().add(evento);
			
			this.s.agregarDatos();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
			
	
	
}

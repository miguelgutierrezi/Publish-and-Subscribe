/**
 * 
 */
package co.edu.javeriana.code;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

/**
 * @author Miguel
 *
 */
public class Utils {
	public static ArrayList<Evento> Read_File (String File_Name) throws IOException, FileNotFoundException, InterruptedException{

		ArrayList<Evento> eventos = new ArrayList<Evento>();
		ArrayList<String> matches = new ArrayList<String>();
		Evento evento;
		String cadena;
        FileReader f = new FileReader(File_Name);
        BufferedReader b = new BufferedReader(f);
        
        while((cadena = b.readLine())!=null) {

        	StringTokenizer tokens = new StringTokenizer(cadena, "*");
        	int a = 0;
        	String token;
        	LocalTime time = LocalTime.parse("00:00");
        	String tipo = null;
        	matches = new ArrayList<String>();
        	
        	while(tokens.hasMoreTokens()){
        		a++;
        		token = tokens.nextToken();
        		if (a == 1) {time = LocalTime.parse(token);};
        		if (a == 2) {tipo = token;};
        		if (a == 3) {
        			StringTokenizer colon = new StringTokenizer(token, ",");
        			
        			while (colon.hasMoreTokens()) {
        				token = colon.nextToken();
        				matches.add(token);
        			}
        			
        		}
        	}
        	
        	evento = new Evento(time, tipo, matches);
        	eventos.add(evento);
        }

        b.close();
        return eventos;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
        
		/*String hora = "19:39:00";
        LocalTime horaS = LocalTime.parse(hora);
        
        while (horaS.equals(LocalTime.now()) == false) {
        	System.out.println("Aun no");
        }
        
        System.out.println(horaS.toString());
		System.out.println(LocalTime.now().toString());*/
		
		Read_File("eventos.txt");
    }
}

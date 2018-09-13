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
import java.util.HashSet;
import java.util.Set;
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
        	String ubicacion = null;
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
        		if (a == 4) {ubicacion = token;};
        	}
        	
        	evento = new Evento(time, tipo, matches, ubicacion);
        	eventos.add(evento);
        }

        b.close();
        return eventos;
	}
	
	public static ArrayList<String> Obtain_Types(ArrayList<Evento> eventos) {
		ArrayList<String> types = new ArrayList<String>();
		
		for (Evento e: eventos) {
			types.add(e.getTipos());
		}
		
		Set<String> hs = new HashSet<String>();
		hs.addAll(types);
		types.clear();
		types.addAll(hs);
		
		for (String s: types) {
			System.out.println(s);
		}
		
		return types;
	}
	
	public static ArrayList<String> Obtain_Matches(ArrayList<Evento> eventos) {
		ArrayList<String> matches = new ArrayList<String>();
		
		for (Evento e: eventos) {
			for (String s: e.getMatch()) {
				matches.add(s);
			}
		}
		
		Set<String> hs = new HashSet<String>();
		hs.addAll(matches);
		matches.clear();
		matches.addAll(hs);
		
		for (String s: matches) {
			System.out.println(s);
		}
		
		return matches;
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
        
		ArrayList<String> matches = new ArrayList<String>();
		ArrayList<String> types = new ArrayList<String>();
		
		/*String hora = "19:39:00";
        LocalTime horaS = LocalTime.parse(hora);
        
        while (horaS.equals(LocalTime.now()) == false) {
        	System.out.println("Aun no");
        }
        
        System.out.println(horaS.toString());
		System.out.println(LocalTime.now().toString());*/
		
		System.out.println("Tipos\n");
		types.addAll(Obtain_Types(Read_File("eventos.txt")));
		types.addAll(Obtain_Types(Read_File("eventos1.txt")));
		
		System.out.println("\nMatches\n");
		matches.addAll(Obtain_Matches(Read_File("eventos1.txt")));
		matches.addAll(Obtain_Matches(Read_File("eventos.txt")));
    }
}

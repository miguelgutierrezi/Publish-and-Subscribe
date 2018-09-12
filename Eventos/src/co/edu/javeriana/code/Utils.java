/**
 * 
 */
package co.edu.javeriana.code;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

/**
 * @author Miguel
 *
 */
public class Utils {
	public static void Read_File (String File_Name) throws IOException, FileNotFoundException, InterruptedException{
		String cadena;
        FileReader f = new FileReader(File_Name);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            StringTokenizer tokens = new StringTokenizer(cadena, "*");
        	int a = 0;
        	String token;
        	LocalTime time = LocalTime.parse("00:00");
        	String tipo = null;
        	String matches = null;
        	while(tokens.hasMoreTokens()){
        		a++;
        		token = tokens.nextToken();
        		if (a == 1) {time = LocalTime.parse(token);};
        		if (a == 2) {tipo = token;};
        		if (a == 3) {matches = token;};
        	}
        	while (true) {
        		if (time.equals(LocalTime.now()) == true) {
        			System.out.println("********************");
        			System.out.println(time);
        			System.out.println(tipo);
        			System.out.println(matches);
        			System.out.println("Ya pare, voy a enviar el mensaje");
        			break;
        		}
        	}
        }
        b.close();
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
        
		/*String hora = "19:39:00";
        LocalTime horaS = LocalTime.parse(hora);
        
        while (horaS.equals(LocalTime.now()) == false) {
        	System.out.println("Aun no");
        }
        
        System.out.println(horaS.toString());
		System.out.println(LocalTime.now().toString());*/
		
		Read_File("C:\\Users\\Miguel\\eclipse-workspace\\Eventos\\src\\co\\edu\\javeriana\\code\\eventos.txt");
    }
}

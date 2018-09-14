/**
 * 
 */
package co.edu.javeriana.code;

import java.util.Comparator;

/**
 * @author Miguel
 *
 */
public class EventComparator implements Comparator<Evento> {
	public int compare(Evento evento1, Evento evento2) {
        return evento1.getHora_publicacion().compareTo(evento2.getHora_publicacion());
    }
}

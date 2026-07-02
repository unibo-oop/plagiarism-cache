package view.ending;

import java.awt.Color;
import javax.swing.JPanel;

/**
 * Interfaccia che contiene i metodoti da implementare poi per comunicare all'utente come è terminata la sua partita.
 * 
 * @author Beatrice Ricci
 */
public interface IFinal {
	
	/**
	 * Metodo per stabilire la dimensione del frame.
	 */
	void setDimension();
	
	/**
	 * Metodo per la gestione dei JButtons presenti nella schermata e dello sfondo:
	 * il tasto "play" permetterà di giocare nuovamente mentre il pulsante "exit" chiude l'applicazione.
	 * 
	 * @param c colore di sfondo
	 * @return JPanel completo
	 */
	JPanel setJButtonsInPanel(final Color c);
	
}

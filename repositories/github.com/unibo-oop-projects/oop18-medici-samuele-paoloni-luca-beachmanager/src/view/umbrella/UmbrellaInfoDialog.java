package view.umbrella;

import javax.swing.JFrame;
import javax.swing.JLabel;

import utils.Position;
import view.InfoDialog;

/**
 * Dialog che mostra le informazioni legate all'ombrello
 * 
 * @author Samuele Medici, samuele.medici2@studio.unibo.it ( Mat. 0000718877 )
 *
 */

public class UmbrellaInfoDialog extends InfoDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7657943773272051506L;

	// Flag se l'ombrellone è occupato
	private JLabel isOccupied;

	// nome cliente
	private JLabel clientLabel;

	// Posizione dell'ombrello
	private JLabel positionLabel;

	public UmbrellaInfoDialog(JFrame frame, Position position) {
		super(frame);

		this.positionLabel = new JLabel("Fila: " + (position.getRow() + 1) + " Numero: " + position.getNumber());
		this.add(this.positionLabel);

		// AGGIUNGERE CONTROLLER PER OTTENERE INFORMAZIONI OMBRELLO
		
		// AGGIUNGERE CONTROLLER PER INFORMAZIONI RIGUARDO AL CLIENTE SE L'OMBRELLO E' OCCUPATO
	}
}

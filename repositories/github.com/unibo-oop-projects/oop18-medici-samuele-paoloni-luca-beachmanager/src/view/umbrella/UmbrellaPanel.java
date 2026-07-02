package view.umbrella;

import java.awt.Color;

import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * Pannello per la visualizzazione della griglia degli ombrelloni
 * 
 * @author Samuele Medici, samuele.medici2@studio.unibo.it ( Mat. 0000718877 )
 *
 */
public class UmbrellaPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 218957526674846088L;/**
																		 * Rappresentano i numeri di file e di colonne
																		 * del pannello
																		 */
	private static final int ROWS = 6;
	private static final int COLUMNS = 8;

	/**
	 * Dimensione fissa dei bottoni
	 */
	private final int buttonSize = 120;

	/**
	 * Costruttore per il pannello degli ombrelli
	 * @param screenWidth larghezza dello del pannello principale
	 * @param screenHeight altezza del pannello principale
	 */
	public UmbrellaPanel(int screenWidth, int screenHeight) {
		super();
		
		this.setLayout(new GridLayout(UmbrellaPanel.ROWS, UmbrellaPanel.COLUMNS));
		
		// Dimensioni per i bottoni calcolati in base alla grandezza dello schermo e del numero di bottoni
		for (int i = 0; i < UmbrellaPanel.ROWS; i++) {
			for (int j = 0; j < UmbrellaPanel.COLUMNS; j++) {

				UmbrellaButton btn = new UmbrellaButton(i, j, this.buttonSize);
				
				this.add(btn);

			}
		}
	}

}

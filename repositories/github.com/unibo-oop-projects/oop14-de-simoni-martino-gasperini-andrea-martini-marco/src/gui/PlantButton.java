package gui;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class PlantButton extends JButton{

	/**
	 * 
	 * Pulsante con l'icona di una pianta.
	 * 
	 * L'unico vero pulsante utilizzato nell'interfaccia di gioco, per chiarimenti si può guardare una schermata del gioco originale.
	 * 
	 * @author Martino De Simoni
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private String ID;
	
	public PlantButton( Icon _img, Integer _price, String _ID){
		
		
		this.setIcon( (Icon) _img);
		this.setVerticalAlignment(SwingConstants.BOTTOM);
		this.setBackground(Color.orange);
		this.setText( _price.toString() );

		this.ID = _ID;

		}
	
	public String getID(){
		return ID;
	}
	
}

/**
 * 
 */
package esseri;

import javax.swing.JPanel;

import controller.InsertionPanelController;

/**
 * @author Andrea //DOBBIAMO VEDERE ASSIEME PERCHé LE CONSUMABILI NON HANNO VITA
 *
 */ //classe astratta che implementa i metodi delle piante consumabili del programma 
public abstract class Consumabili extends Pianta implements IConsumabili {

	
	public boolean attaccato = false;
	protected TipoTerreno terra;
	
	public Consumabili(Posizione2D posizione, InsertionPanelController<Azione, ? extends JPanel> controller, final String imgName){		
		super(posizione,controller, imgName);
		life = 1;
	}	

	
}

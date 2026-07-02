package view.play;

import java.awt.Color;
import javax.swing.JButton;
import controller.Utility;

/**
 * Classe che implementa l'interfaccia IButt e estende la classe JButton facendo in modo che ogni pulsante sia 
 * caratterizzato da due coordinate.
 * 
 * @author Beatrice Ricci
 *
 */
public class Butt extends JButton implements IButt {	

	private static final long serialVersionUID = 2769913399342872901L;
	
	private final int posX;
	private final int posY;
	
	/**
	 * Costruttore dell'oggetto Butt.
	 * 
	 * @param x prima coordinata
	 * @param y seconda coordinata
	 */
	public Butt(final int x, final int y) {
		this.posX = x;
		this.posY = y;
		this.setLookButt();
	}
	
	@Override
	public int getPosX() {
		return this.posX;
	}
	
	@Override
	public int getPosY() {
		return this.posY;
	}
	
	/**
	 * Medoto per gestire l'aspetto dei Butt, vengono modificate dimensioni e colore
	 */
	private void setLookButt() {
		this.setBackground(Color.gray);
		this.setSize(Utility.HBUTT, Utility.LBUTT);
	}
	
}

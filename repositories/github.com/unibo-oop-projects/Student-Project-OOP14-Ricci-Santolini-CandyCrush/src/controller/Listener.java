package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.play.IButt;

/**
 * Classe che osserva il comportamento della view.
 * 
 * @author Beatrice Ricci, Nicola Santolini
 *
 */
public class Listener implements ActionListener {

	private Controller c;
	
	private int lastX = -1;
	private int lastY = -1;
	/**
	 * Metodo che aggancia l'observer della GUI al controller.
	 * 
	 * @param con cotroller
	 */
	public void addObserver(final Controller con) {
		this.c = con;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		
		final IButt b = (IButt) e.getSource();
		
		if (lastX == -1 && lastY == -1) {
			lastX = b.getPosX();
			lastY = b.getPosY();
			@SuppressWarnings("unused")
			final IButt app = b;
		} else {
			c.makeMove(lastX,  lastY, b.getPosX(), b.getPosY());
			lastX = -1;
			lastY = -1;
		}	
	}
}
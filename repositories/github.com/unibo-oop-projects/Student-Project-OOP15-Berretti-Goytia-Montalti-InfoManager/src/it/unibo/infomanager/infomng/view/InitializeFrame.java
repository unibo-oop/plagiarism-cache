package it.unibo.infomanager.infomng.view;

import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.infomanager.infomng.controller.Navigator;

/**
 * 
 * Classe che definisce gli aspetti comuni del JFrame per questo programma.
 * 
 * @author Alessandro
 */
public class InitializeFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6847630922237609866L;
	private final Boolean resizable = false;
	private final Integer close = JFrame.EXIT_ON_CLOSE;
	private Navigator<?> navigatore;

	/**
	 * Costruttore.
	 * 
	 * @param title
	 *            Titolo del frame
	 * @param layout
	 *            Layout main panel
	 * @param dimension
	 *            Dimensione del frame
	 */
	protected InitializeFrame(final String title, final LayoutManager layout, final Dimension dimension) {
		this.setTitle(title);
		this.setResizable(this.resizable);
		this.setDefaultCloseOperation(this.close);
		this.setSize(dimension);
		JPanel main = new JPanel();
		main.setLayout(layout);
		this.getContentPane().add(main);
	}

	/**
	 * Metodo che ritorna MainPanel.
	 * 
	 * @return MainPanel
	 */
	protected JPanel getMainPanel() {
		return (JPanel) this.getContentPane().getComponent(0);
	}

	/**
	 * Metodo che setta visible il frame.
	 * 
	 * @param switchOn
	 *            true = visible
	 */
	public void display(final Boolean switchOn) {
		this.setVisible(switchOn);
	}

	/**
	 * Metodo per settare il navigatore che permette di scorrere tra gli
	 * elementi cerati.
	 * 
	 * @param navigatore
	 *            oggetto Navigator
	 */
	//CHECKSTYLE:OFF:
	public void setNavigator(final Navigator<?> navigatore) {
	//CHECKSTYLE:ON:
		this.navigatore = navigatore;
	}

	/**
	 * Metodo per ottenere il navigatore per scorrere tra gli elementi.
	 * @return Navigator(?)
	 * 			oggetto Navigator
	 */
	public Navigator<?> getNavigator() {
		return this.navigatore;
	}
}
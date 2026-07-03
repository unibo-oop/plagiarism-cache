package controller;

import gui.MyFrame;

import javax.swing.JPanel;

/**
 * 
 *
 * Classe astratta che raccoglie a fattor comune alcune proprietà fondamentali dei PanelController. 
 * Si noti che ogni PanelController è sempre slave di un master dei PanelController, che controlla un frame.
 *
 * Ogni PanelController segue la filosofia dell'1 a 1: un controller per ogni pannello e viceversa.
 *
 * @param <X> Tipo di pannello utilizzato
 * 
 * @author Martino De Simoni
 */

public abstract class PanelController<X extends JPanel> implements ISlaveController {

    protected X controlledPanel;
	protected MasterPanelController master;
	protected MyFrame frame;
	
	/**
	 * 
	 * @return Il {@link JPanel} controllato
	 */
	
	public JPanel getControlledPanel(){
		return controlledPanel;
	}
	
	
	
}

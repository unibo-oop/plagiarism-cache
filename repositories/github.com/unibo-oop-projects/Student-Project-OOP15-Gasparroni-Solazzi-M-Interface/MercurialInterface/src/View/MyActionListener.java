package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Control.Control;

/**
 * Classe utilizzata per l'intercetto dell'evento della pressione dei pulsanti
 * nella View principale (Merc_view_Impl)
 *
 * @author Filippo Solazzi
 *
 */
public class MyActionListener implements ActionListener {

	private Control ctr;
	
	/**
	* Costruttore che inizializza il control
	* per la gestione degli eventi
	*
	* @param ctr
	*          Control utilizzato per l'intercetto degli eventi
	*/
	public MyActionListener(Control ctr){
		this.ctr = ctr;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
        ctr.operazioni(e.getActionCommand());
	}
}

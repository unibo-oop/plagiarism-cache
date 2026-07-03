/**
 * 
 */
package esseri;


import javax.swing.JPanel;

import controller.InsertionPanelController;

/**
 * @author Andrea
 *
 */
public class SparapallinoTriplo extends Sparapallino {
	
	
	private TipoTerreno terra=TipoTerreno.CORTILE; //andrebbe nel costruttore
	
	public SparapallinoTriplo(){
		//TODO
	}
	
	public void faiRobe(int tempo_trascorso,final InsertionPanelController<Azione, ? extends JPanel> controller){
		//TODO crea tre pallini nello stesso punto, dovrebbe con una certa distanza
		super.faiRobe(tempo_trascorso, controller);
		super.faiRobe(tempo_trascorso, controller);
		super.faiRobe(tempo_trascorso, controller);

		
	}

}

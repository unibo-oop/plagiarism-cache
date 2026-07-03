/**
 * 
 */
package esseri;

import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import controller.InsertionPanelController;

/**
 * @author Andrea
 *
 */
public class Sparapallino extends Offensivo {
	
	
	private TipoTerreno terra=TipoTerreno.CORTILE;
	
	public Sparapallino(){
		super(posizione);
	
	}
	

	
	public void faiRobe(int tempo_trascorso,final InsertionPanelController<Azione,? extends JPanel> controller){
		
		if(canAct(tempo_trascorso))
			controller.insert(new Creazione ( new Pallino(this.posizione) ));
		
	}
	

}

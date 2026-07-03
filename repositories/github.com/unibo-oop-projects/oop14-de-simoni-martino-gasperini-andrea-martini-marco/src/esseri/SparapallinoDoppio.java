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
public class SparapallinoDoppio extends Sparapallino {
	
	private final String imgName = "spara_pallino_doppio.jpg";
	
	BufferedImage img = initImg( cartellaImmagini + imgName );
	
	private TipoTerreno terra=TipoTerreno.CORTILE;
	
	public SparapallinoDoppio(){
	}
	
	
	public void faiRobe(int tempo_trascorso,final InsertionPanelController<Azione, ? extends JPanel> controller){
		//TODO crea due pallini nello stesso punto, dovrebbe crearli con una certa distanza
		super.faiRobe(tempo_trascorso, controller);
		super.faiRobe(tempo_trascorso, controller);
	}
	
}

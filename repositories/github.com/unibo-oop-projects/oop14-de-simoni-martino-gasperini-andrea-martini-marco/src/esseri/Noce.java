/**
 * 
 */
package esseri;

import java.awt.image.BufferedImage;

/**
 * @author Andrea
 *
 */
public class Noce extends Difensivo {
	
	BufferedImage img = initImg();
	
	private TipoTerreno terra=TipoTerreno.CORTILE;
	
	public Noce(){
		super(posizione);
	}
	
	public void faiRobe(int tempo_trascorso,InsertionPanelController<Azione> controller){
		
	}

}

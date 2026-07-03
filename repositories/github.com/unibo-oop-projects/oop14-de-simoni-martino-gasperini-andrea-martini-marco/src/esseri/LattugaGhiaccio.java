/**
 * 
 */
package esseri;

import java.awt.image.BufferedImage;

/**
 * @author Andrea
 *
 */
public class LattugaGhiaccio extends Consumabili {
	
	BufferedImage img = initImg();
	
	private TipoTerreno terra=TipoTerreno.CORTILE;
	
	public LattugaGhiaccio(){
		super(posizione);
		
	}
	
	public void faiRobe(int tempo_trascorso,InsertionPanelController<Azione> controller){
		
		
	}

}

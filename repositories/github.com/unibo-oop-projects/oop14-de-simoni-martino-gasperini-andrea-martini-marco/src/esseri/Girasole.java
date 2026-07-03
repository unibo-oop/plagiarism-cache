/**
 * 
 */
package esseri;

import java.awt.image.BufferedImage;

/**
 * @author Andrea
 *
 */
public class Girasole extends Support {
	
	BufferedImage img = initImg();
	
	private TipoTerreno terra=TipoTerreno.CORTILE;
	
	public Girasole(){	
		super(posizione);
	}
	
	public void faiRobe(int tempo_trascorso,InsertionPanelController<Azione> controller){
		
		
	}

}

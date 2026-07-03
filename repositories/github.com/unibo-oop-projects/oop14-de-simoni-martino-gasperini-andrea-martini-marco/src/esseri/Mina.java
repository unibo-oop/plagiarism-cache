/**
 * 
 */
package esseri;

import java.awt.image.BufferedImage;

/**
 * @author Andrea
 *
 */
public class Mina extends Consumabili {
	
	private TipoTerreno terra=TipoTerreno.CORTILE;
	
	BufferedImage img = initImg();
	
	public Mina(Posizione2D posizione) {
		super(posizione);
	
	}

	
public void faiRobe(int tempo_trascorso,InsertionPanelController<Azione> controller){		
   }
}
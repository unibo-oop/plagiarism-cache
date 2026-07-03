/**
 * 
 */
package esseri;

import java.awt.image.BufferedImage;

/**
 * @author Andrea
 *
 */  //Come pianta e zombie, pallino è un essere
public class Pallino extends Essere {
	
	private double danno = Utility.PIANTA_DANNO_BASSO; 
		
	public Pallino(Posizione2D posizione){
		
		super(posizione);
	}
	
	public void faiRobe(int tempo_trascorso,InsertionPanelController<Azione> controller){
	
		//this.tempo_in_ms+=tempo_trascorso;
		if(this.canAct(tempo_trascorso)){ //if( canAct(tempo_trascorso) )
			
			Azione miaAzione = new Attacco(this.danno,Zombie);
			controller.insert(new Attacco(this.danno,Zombie,new Movimento(this.posizione, new Posizione2D(1,0))));
			
			controller.insert( new Movimento(this.posizione,new Posizione2D(1,0) ));
			
		}	
	}
}

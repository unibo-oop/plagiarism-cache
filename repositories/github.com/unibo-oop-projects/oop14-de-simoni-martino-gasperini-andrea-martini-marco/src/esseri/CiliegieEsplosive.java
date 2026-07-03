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
public class CiliegieEsplosive extends Consumabili {

		
	public CiliegieEsplosive(Posizione2D posizione, InsertionPanelController<Azione, ? extends JPanel> controller){
		 
		 super(posizione,controller, "ciliegie_esplosive.jpg");
		 danno = Utility.PIANTA_DANNO_ALTO;
		
		 terra = TipoTerreno.CORTILE;
	
	}
	
	
	public void faiRobe(int tempo_trascorso,InsertionPanelController<Azione, ? extends JPanel > controller){
		
		if(canAct(tempo_trascorso)){
			if(!attaccato){
				controller.insert(new Attacco(TipoEssere.ZOMBIE, this.posizione.sumPositions( new Posizione2D(1,1)),danno));
				controller.insert(new Attacco(TipoEssere.ZOMBIE, this.posizione.sumPositions( new Posizione2D(0,1)),danno));
				controller.insert(new Attacco(TipoEssere.ZOMBIE, this.posizione.sumPositions( new Posizione2D(1,0)),danno));
				controller.insert(new Attacco(TipoEssere.ZOMBIE, this.posizione.sumPositions( new Posizione2D(-1,1)),danno));
				controller.insert(new Attacco(TipoEssere.ZOMBIE, this.posizione.sumPositions( new Posizione2D(1,-1)),danno));
				controller.insert(new Attacco(TipoEssere.ZOMBIE, this.posizione.sumPositions( new Posizione2D(-1,0)),danno));
				controller.insert(new Attacco(TipoEssere.ZOMBIE, this.posizione.sumPositions( new Posizione2D(0,-1)),danno));
				controller.insert(new Attacco(TipoEssere.ZOMBIE, this.posizione.sumPositions( new Posizione2D(-1,-1)),danno));
			
				attaccato = true;
			}
			else controller.insert(new Attacco(TipoEssere.ZOMBIE, this.posizione.sumPositions( new Posizione2D(0,0)),this.life));
		}
	}

}

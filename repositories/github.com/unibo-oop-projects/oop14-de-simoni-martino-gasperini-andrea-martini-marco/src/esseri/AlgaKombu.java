/**
 * 
 */
/**
 * 
 */
package esseri;

import javax.swing.JPanel;

import controller.InsertionPanelController;

/**
 * @author Andrea
 *Questa pianta , è un'alga acquatica, che può essere giocata solo in acqua , e una volta messa in gioco
 *non fa nulla se non è attaccata, ma quando viene attaccata, infligge un danno elevato allo zombie. 
 *
 */
public class AlgaKombu extends Support {
	
	
	public AlgaKombu(Posizione2D posizione, InsertionPanelController<Azione, ? extends JPanel> controller){
		 
		 super(posizione,controller, "Alga_Kombu.jpg");
		 danno = Utility.PIANTA_DANNO_ALTO;
		
		 terra = TipoTerreno.ACQUA;
	
	}
	
public void faiRobe(int tempo_trascorso,InsertionPanelController<Azione, ? extends JPanel > controller){
		
		if(canAct(tempo_trascorso)){
			if(!attaccato){
				controller.insert(new NessunAzione());
				
				attaccato = true;
			}
			else{ 
				controller.insert(new Attacco(TipoEssere.ZOMBIE, this.posizione.sumPositions( new Posizione2D(0,0)),this.life));
			
			}
		}
	}

}

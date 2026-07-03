package esseri;

import javax.swing.JPanel;
import esseri.ZombiBallerino;

import controller.InsertionPanelController;

public class DiscoZombi extends Zombi {

	
	
	public DiscoZombi(Posizione2D posizione, final InsertionPanelController<Azione,? extends JPanel> controller) {
		super(posizione,controller,"disco_zombi");
		// TODO Auto-generated constructor stub
	}


	public void faiRobe(int tempo_trascorso) {
		
		if(canAct(tempo_trascorso)){
			controller.insert(new Creazione( new ZombiBallerino( this.posizione.sumPositions( new Posizione2D(0,1) ) ,this.controller)));
			controller.insert(new Creazione( new ZombiBallerino( this.posizione.sumPositions( new Posizione2D(0,-1) ) ,this.controller)));
			controller.insert(new Creazione( new ZombiBallerino( this.posizione.sumPositions( new Posizione2D(1,0) ) ,this.controller)));
			controller.insert(new Creazione( new ZombiBallerino( this.posizione.sumPositions( new Posizione2D(-1,0) ) ,this.controller)));
		}
		
		
		
	}


	
	
}

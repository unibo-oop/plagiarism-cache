package esseri;

import javax.swing.JPanel;

import controller.InsertionPanelController;

/*
 * Lo zombi regbista è lo zombi piu forte e resistente della nostra applicazione
 * 
 */

public class ZombiRegbista extends Zombi {

	
	int tempo_in_ms=0;
	
	
	public ZombiRegbista(Posizione2D posizione,final InsertionPanelController<Azione,? extends JPanel> controller){
		
		super(posizione,controller,"zombiInformato.jpg");
		this.life = Utility.PIANTA_DANNO_ALTO;
		super.tempo_richiesto = 1000;
	}
		
	
    public void faiRobe(int tempo_trascorso,InsertionPanelController<Azione, ? extends JPanel> controller) {
		// TODO 
		
		if(this.canAct(tempo_trascorso)){
			
			Attacco azioneDaImmettere = new Attacco( TipoEssere.PIANTA, new Posizione2D(-1,0),this.danno);
			
			azioneDaImmettere.inCasoDiFallimento = new Movimento( new Posizione2D(-3,0), this );
			
			this.controller.insert(azioneDaImmettere);
			
			//controller.insert(new Attacco(this.danno,Pianta,new Movimento(this.posizione,new Posizione2D(1,0))));
			//controller.inser(new Movimento(this.posizione,new Posizione2D(1,0)));
		}
				
	}
	
}

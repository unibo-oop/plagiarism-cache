package esseri;

import javax.swing.JPanel;

import controller.InsertionPanelController;

/*
 * Lo zombiPorta porta con se una enorme porta che lo difende dai colpi iniziali
 * fino a quando la vita della porta non scende a 0.
 * 
 */

public class ZombiPorta extends Zombi {

	
	int tempo_in_ms=0;
	private double lifeOggetto = 40.0;
	
	public ZombiPorta(Posizione2D posizione,final InsertionPanelController<Azione,? extends JPanel> controller) {
		super(posizione,controller,"zombiInformato.jpg");
		super.tempo_richiesto = 1400;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void prendiDanno(int danno) {
		// TODO Auto-generated method stub
		
		while(this.lifeOggetto !=0){
			
			this.lifeOggetto -= danno;
		}
			
		super.prendiDanno(danno);
	}
	
	public double getLifeOggetto(){
		
		return this.lifeOggetto;
		
	}

	
	public void faiRobe(int tempo_trascorso,InsertionPanelController<Azione, ? extends JPanel> controller){
		
		if(this.canAct(tempo_trascorso)){
			
		while(this.lifeOggetto !=0){
			
	        Attacco azioneDaImmettere = new Attacco( TipoEssere.PIANTA, new Posizione2D(-1,0),this.danno);
			
			azioneDaImmettere.inCasoDiFallimento = new Movimento( new Posizione2D(-1,0), this );
			
			this.controller.insert(azioneDaImmettere);
			}
			
			Attacco azioneDaImmettere = new Attacco( TipoEssere.PIANTA, new Posizione2D(-1,0),this.danno);
			
			azioneDaImmettere.inCasoDiFallimento = new Movimento( new Posizione2D(-3,0),this);
			
			this.controller.insert(azioneDaImmettere);
			
	    }
	}
	
}


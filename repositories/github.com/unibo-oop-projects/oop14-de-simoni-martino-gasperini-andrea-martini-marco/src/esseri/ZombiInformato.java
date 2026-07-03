package esseri;

import javax.swing.JPanel;

import controller.InsertionPanelController;


/*
 * Lo zombiInformato ha con se un oggetto che lo difende dai primi colpi
 * che gli infliggono le piante, poi però quando la vita dell'oggetto scende a 0
 * lo zombiInformate prende i danni su se stesso
 * 
 * 
 */

public class ZombiInformato extends Zombi {
	
	
	int tempo_in_ms=0;
	
	private double lifeOggetto = 30.0;
	
	public ZombiInformato(Posizione2D posizione,final InsertionPanelController<Azione,? extends JPanel> controller) {
		super(posizione,controller,"zombiInformato.jpg");
		super.tempo_richiesto = 1200;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void prendiDanno(int danno) {
		
		while(this.lifeOggetto !=0){
			
		this.lifeOggetto -= danno;
		
		}
		
		super.prendiDanno(danno);
				
	}

	public double getLifeOggetto(){
		
		return this.lifeOggetto;
	}


	public void faiRobe(int tempo_trascorso,InsertionPanelController<Azione, ? extends JPanel> controller) {


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
package esseri;

import java.awt.image.BufferedImage;

public class ZombiComune extends Zombi {
	
	
	public ZombiComune(Posizione2D posizione) {
		super(posizione);
		// TODO Auto-generated constructor stub
	}

	public void faiRobe(int tempo_trascorso) {

		Attacco azioneDaImmettere = new Attacco( TipoEssere.PIANTA, new Posizione2D(-1,0),this.danno);
		
		azioneDaImmettere.inCasoDiFallimento = new Movimento( new Posizione2D(-1,0), this );
		
		this.controller.insert( azioneDaImmettere );
		
	}
		
}

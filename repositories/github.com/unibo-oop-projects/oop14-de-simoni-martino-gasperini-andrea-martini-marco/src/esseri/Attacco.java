package esseri;

/**
 * 
 * @author Martino De Simoni
 *
 * Azione di attacco comunicata dagli enti al controller.
 *
 */

public class Attacco extends Azione {
	
	TipoEssere chiAttaccare; //Piante? Zombie? Pallini? Uso le enum perchÄ� sono piÅ¯ facili da usare. 
	Posizione2D distanzaDaAttaccante;
	
	double danno; 
	
	public Attacco(TipoEssere nome,Posizione2D distanzaDaAttaccante,double danno){
		
	    this.chiAttaccare = nome;
		this.distanzaDaAttaccante = distanzaDaAttaccante;
		this.danno = danno;
		
	}
	
}

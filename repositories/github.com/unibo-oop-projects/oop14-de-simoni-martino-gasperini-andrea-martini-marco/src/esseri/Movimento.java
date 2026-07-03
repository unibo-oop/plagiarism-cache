package esseri;

/**
 * 
 * @author Martino De Simoni
 *
 * Classe per comunicare al controller un movimento dell'oggetto che comunica.
 *
 */

public class Movimento extends Azione{
	
	Essere chiSiMuove;
	Posizione2D vettoreMovimento;
    
    public Movimento(Posizione2D vettoreMovimento, Essere chiSiMuove){
		
		this.chiSiMuove = chiSiMuove;
		this.vettoreMovimento = vettoreMovimento;
		
	}
	
}

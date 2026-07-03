package esseri;

/**
 * 
 * @author Martino De Simoni
 *
 * Comunica la creazione di un pallino, di un altro zombie..
 *
 */

/*
 * Non ci sono meccanismi di sicurezza per impedire al programmatore di far creare a una pianta uno zombie. 
 */

public class Creazione extends Azione {

	Essere cosaCreare;	
    
    public Creazione(Essere essere){
		
		this.cosaCreare = essere;
	}
    
}

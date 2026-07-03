package esseri;

/**
 * 
 * @author Martino De Simoni
 *
 */

public class NessunAzione extends Azione {
	
	public static final Azione nessunAzione = new NessunAzione(); //pattern Singleton
	public static final Azione niente = nessunAzione; //alias
	
	private NessunAzione(){
		
		this.animazione= 0; //Qui animazione č ancora un int.
		this.inCasoDiFallimento = nessunAzione;
		this.altraAzione = nessunAzione;
	}
	
}


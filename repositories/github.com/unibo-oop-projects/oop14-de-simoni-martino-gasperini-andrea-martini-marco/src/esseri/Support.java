/**
 * 
 */
package esseri;

/**
 * @author Andrea
 *
 */ // astratta supporto che implementa i metodi delle piante di supporto del programma
public abstract class Support extends Pianta implements ISupport {

	protected double life = 0;
	protected TipoTerreno terra;
	static Posizione2D posizione;
	
	public Support(Posizione2D posizione){		
		super(posizione);
	}	

	public double getLife(){
		return this.life;
	}
	
	public TipoTerreno getTerrenoAccettabile(){		
		return this.terra;			
	}
	
}

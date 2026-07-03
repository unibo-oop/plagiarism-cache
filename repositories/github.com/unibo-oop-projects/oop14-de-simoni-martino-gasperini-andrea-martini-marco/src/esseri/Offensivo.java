/**
 * 
 */
package esseri;

/**
 * @author Andrea
 *
 *///classe astratta offensivo che implementa i metodi per tutte le piante offensive del programma
public abstract class Offensivo extends Pianta implements IOffensivo {
	
	protected double life = Utility.PIANTA_VITA_MEDIA;
	static Posizione2D posizione;
	
	public Offensivo(Posizione2D posizione){		
		super(posizione);
	}
	
	public double getLife(){
		return this.life;
	}
	
	public void prendiDanno(int danno){		//NEW
		this.life -= danno;	
	}
	
	@Override
	public void prendi_danno(int danno) {
		// TODO Auto-generated method stub
		
	}
	
}
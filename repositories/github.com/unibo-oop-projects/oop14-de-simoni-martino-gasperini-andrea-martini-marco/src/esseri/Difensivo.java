package esseri;

/**
 * @author Andrea
 *
 *///classe astratta difensivo che implementa i metodi delle piante difensive del programma
public abstract class Difensivo extends Pianta implements IDifensivo{
	
	protected double life = Utility.PIANTA_VITA_ALTA;
	protected TipoTerreno terra;
	static Posizione2D posizione;
	
	public Difensivo(Posizione2D posizione){		
		super(posizione);
	}	

	public double getLife(){
		return this.life;
	}
	
	public TipoTerreno getTerrenoAccettabile(){		
		return this.terra;			
	}
	
	public void prendiDanno(int danno){		//NEW
		this.life -= danno;
	}
	@Override
	public void prendi_danno(int danno) {
		// TODO Auto-generated method stub
	}	
	
}
	


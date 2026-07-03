package esseri;


public class ZombiSecchione extends Zombi implements iZombi {

	
	private  double life;
	private  double danno;
	
	
	public ZombiSecchione(Posizione2D posizione){
		
		super(posizione);
		this.danno = Utility.PIANTA_DANNO_ALTO;
		this.life = Utility.PIANTA_VITA_ALTA;
	}

	
    public double getlife(){
    	
    	return this.life;
    }
    	
	@Override
	public void azione() {
		// TODO Auto-generated method stub
		
	}

}

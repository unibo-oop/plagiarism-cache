package esseri;

public class ZombiSommozzatore extends Zombi {

	private TipoTerreno tipoTerreno = TipoTerreno.ACQUA ;
	
		
	public ZombiSommozzatore(Posizione2D posizione){
		
		super(posizione);
		super.tipoTerreno = tipoTerreno;
		
	}

	@Override
	public void azione() {
		
	}
		
}

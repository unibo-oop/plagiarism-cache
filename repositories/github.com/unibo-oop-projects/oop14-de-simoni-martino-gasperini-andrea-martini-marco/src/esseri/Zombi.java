package esseri;

import java.io.File;
/*classe astratta che mi definisce tutte le caratteristiche comuni a tutti gl zombi
 *  
 * 
 */


import javax.swing.JPanel;

import controller.InsertionPanelController;

public abstract class Zombi extends Essere implements IZombi {

  // Setto il tipoTerreno a cortile tanto la maggior parte delgi zombi è li per gli zombi che non sono
	//del cortile utilizzo lo implemento nelle classi concrete degli zombi
	
	protected TipoTerreno tipoTerreno = TipoTerreno.CORTILE;
	
	protected static final String cartellaImmagini = System.getProperty("user.dir")+File.separatorChar+"zombi"+File.separatorChar
			+"img"+File.separatorChar; //infallibile
	
	public Zombi(final Posizione2D posizione, final InsertionPanelController<Azione, ? extends JPanel> _controller, 
			final String imgName ){
		
		super(posizione, _controller, cartellaImmagini+imgName, TipoEssere.ZOMBIE);
	}
		
	
	public void prendiDanno(int danno){
		
		 super.life-= danno;
		
	}
				
	

	// algoritmo di fai robe da finire
			
	public TipoTerreno getTerreno(){
		
		return this.tipoTerreno;
		
	}
	
	public double getLife(){
		
		return this.life;
	}
	
	public double getDanno(){
		
		return super.danno;
	}
		
	public void muori(){}
		
}

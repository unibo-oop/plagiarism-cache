/**
 * 
 */
package esseri;

import java.io.File;

import javax.swing.JPanel;

import controller.InsertionPanelController;


/**
 * @author Andrea     
 *                      //pianta come zombie e pallino estende esseri .
 */                   
public abstract class Pianta extends Essere implements IPianta {

	public double life;
	public int tempo_in_ms;
	public int costo; // costo della piante
	private TipoTerreno terra;
	
	public TipoEssere tipo = TipoEssere.PIANTA;
	
	protected static final String cartellaImmagini = System.getProperty("user.dir")+File.separatorChar+"pianta"+File.separatorChar
			+"img"+File.separatorChar; //infallibile
	
	public Pianta(final Posizione2D posizione, final InsertionPanelController<Azione, ? extends JPanel> _controller, final String
			imgName){
		
		super(posizione, _controller, cartellaImmagini+imgName, TipoEssere.PIANTA);
	
	}
	
	public void prendiDanno(int danno){
		
		life-= danno;
		
	}

	public double getLife(){
		return this.life;		
	}
	
	public TipoTerreno getTerrenoAccettabile(){		
		return this.terra;			
	}
	
	public void cambia_immagine(){	
	}
	
	 public void muore(){
	}
	
	public int getCosto() {
		return costo;
	}

}

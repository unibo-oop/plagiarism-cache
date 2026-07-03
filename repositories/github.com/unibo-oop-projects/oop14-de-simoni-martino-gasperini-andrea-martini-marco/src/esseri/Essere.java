/**
 * 
 */
package esseri;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controller.InsertionPanelController;
import esseri.Posizione2D;

/**
 * @author Andrea
 * @author Marco
 * @author Martino
 */
public abstract class Essere {

	InsertionPanelController<Azione, ? extends JPanel> controller;
	protected int tempo_trascorso;
	protected int tempo_richiesto;
    protected double life;
	Posizione2D posizione;
	final public TipoEssere nome;
	protected double danno;
	protected final BufferedImage img;
	
	protected final static String cartellaImmaginiEsseri = System.getProperty("user.dir")+File.separatorChar+"img"+File.separatorChar; //infallibile 
	
	//Costruttori
	
	//TODO avvicinare i costruttori delle altre classi a questo tipo 
	public Essere(final Posizione2D _posizione,final InsertionPanelController<Azione,? extends JPanel> _controller, final String path,
			final TipoEssere _nome){
		
		nome = _nome;
		controller = _controller;
		posizione = _posizione;
		img = initImg(path);
	
	}
	
	protected boolean canAct(int tempo){ // side effect su tempo_in_ms
		
		this.tempo_trascorso+=tempo_trascorso;
		if(tempo_trascorso>tempo_richiesto){
			this.tempo_trascorso-=tempo_richiesto;
			return true;
		}
		return false;
	}
	
	public double getLife(){
	return this.life;	
	}
	
	public String getPosizione(){	
	return this.posizione.getPosition();
	}

	public int getTempo_trascorso() {
		return tempo_trascorso;
	}

	public TipoEssere getNomeEsseri(){
		return this.nome;
	}
	
	/**
	 * 
	 * @param filePath Percorso del file da cui prendere l'immagine.
	 * @return L'immagine.
	 */
	protected static BufferedImage initImg(String filePath){
		
		BufferedImage img = null;
		try{
			img = ImageIO.read(Files.newInputStream(Paths.get(filePath)));
		} catch(IOException e){
			System.out.println(e);
		}
		
		return img;
		}
	
}

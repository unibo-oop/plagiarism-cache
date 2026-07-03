package view;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class V_Background {
	
	protected static BackgroundPanel Create_Sfondo(String percorso){
		
		BackgroundPanel main;
		BufferedImage sfondo = null;
		
		try {
		    sfondo = ImageIO.read(V_Background.class.getResource(percorso));
		} catch (IOException e) {
			
		}
		main = new BackgroundPanel(sfondo, 0);
		
		return main;
	}
	
}

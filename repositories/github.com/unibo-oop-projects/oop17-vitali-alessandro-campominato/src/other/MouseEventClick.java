package other;

import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import enumeration.Action;

/**
 * gestisce il click sulle celle, differenziandolo tra click destro o sinistro
 * 
 * @author Alessandro
 *
 */
public class MouseEventClick implements MouseListener{
	
	private JButton button;
	
	/**
	 * costruttore
	 * 
	 * @param button
	 *     bottone sul quale � avvenuto il click, il listener � inserito in PanelEasyGame
	 */
	public MouseEventClick(JButton button) {
		this.button = button;
	}

	/**
	 * funzione che gestisce il click, in base a destro o sinistro indica l'Action da compiere, inoltre crea un effetto sonoro
	 */
	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		if(this.button.isEnabled()){
			if(SwingUtilities.isRightMouseButton(e)) {
				try {
				  InputStream is = getClass().getResourceAsStream("/sounds/Flag.wav");
				  AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
				  Clip clip = AudioSystem.getClip();
				  clip.open(ais);
				  clip.start();
				  
          ais.close();
          is.close();
				} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e1) {
					e1.printStackTrace();
				}
				Main.boardGameView.clickView(e.getSource(), Action.SET_FLAG);
			} else if(SwingUtilities.isLeftMouseButton(e)){
				try {
				  InputStream is = getClass().getResourceAsStream("/sounds/Click.wav");
          AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
          Clip clip = AudioSystem.getClip();
          clip.open(ais);
          clip.start();
          
          ais.close();
          is.close();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
					e1.printStackTrace();
				}
				Main.boardGameView.clickView(e.getSource(), Action.PLAY);
			}
		}
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {}

}

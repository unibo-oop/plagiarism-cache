package gui;

import java.awt.*;

import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import javax.swing.JPanel;

import gui.Utility;

/**
 *
 * Astratta della classe frame da utilizzare.
 *
 * @author Martino De Simoni
 *
 */

public abstract class MyFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected JPanel inUso = new JPanel(); //non serve, ma non è null..


	/**
	 * @param title
	 *            the window title
	 * @param lm
	 *            the layoutmanager for the main panel
	 */
	public MyFrame(final String title, BufferedImage icon) {

		super(title);

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		
		
		setLocationByPlatform(true);
		
		final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		final int sw = (int) screen.getWidth();
		final int sh = (int) screen.getHeight();
		setSize(sw / Utility.razioWhenNotFullScreen , sh / Utility.razioWhenNotFullScreen);
			
		if(Utility.FULLSCREEN) this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			
		setIconImage( icon );
		


	}
	
	public void setMainPanel(JPanel newPanel, boolean visible ){
		
		this.remove(inUso);
		this.inUso = newPanel;
		this.add(newPanel);
		
		setContentPane(newPanel);
		getContentPane().setVisible(visible);
		
		
	}
	
	
	/**
	 * @return the main {@link JPanel}
	 */
	
	
	
	public JPanel getMainPanel() {
		return (JPanel) this.getContentPane().getComponent(0);
	}

	
	
}


	
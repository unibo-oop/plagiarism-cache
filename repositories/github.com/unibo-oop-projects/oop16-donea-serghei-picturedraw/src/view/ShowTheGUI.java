package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * Il  principale JFrame su cui risiedono tutti i componenti 
 * @author redim
 */

@SuppressWarnings("serial")
public class ShowTheGUI extends JFrame implements ShowTheGUIView {
	
  
    public MainPanel mainPanel;
	
	public ShowTheGUI() {
	    mainPanel = new MainPanel();
		this.setTitle("PictureDraw");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(mainPanel);
		this.setJMenuBar(new ChoseMenuBar());
		this.setResizable(true);
		this.pack();
		final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	        final int sw = (int) screen.getWidth()*2/3;
	        final int sh = (int) screen.getHeight()*2/3;
	        this.setLocationByPlatform(true);
	        this.setMinimumSize(new Dimension(sw, sh));
	        this.setSize(sw, sh);	        
	}

    
    @Override
    public void start() {
        this.setVisible(true);  
        
    }

}

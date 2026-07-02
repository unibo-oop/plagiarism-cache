package view;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.GameController;
import resources.ResourcesManagement;
import utilities.Utility;
import view.template.RoundedCornerButton;
public class StartView extends JPanel{
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private JButton playButton;
	private JButton statisticsButton;
	private JButton creditsButton;
	public  StartView() {
		Optional<Font> appFont = Optional.ofNullable(Utility.fontLoader(ResourcesManagement.getCustomFontPath()));
		this.setLayout(new GridLayout(4,1,0,50));
	    playButton =  new RoundedCornerButton("Start the match");
		statisticsButton = new RoundedCornerButton("Statistics");
		creditsButton = new RoundedCornerButton("Credits");
		System.out.println(ResourcesManagement.getCustomFontPath());
		appFont.ifPresent(x ->{
			playButton.setFont(x);
			statisticsButton.setFont(x);
			creditsButton.setFont(x);
		});	
		this.add(playButton);
		this.add(statisticsButton);
		this.add(creditsButton);
	}
	
	public void addPlayButtonListener(ActionListener l) {
	    playButton.addActionListener(l);
	}
	public void addStatisticsButtonListener(ActionListener l) {
           statisticsButton.addActionListener(l);
        }
	public void addCreditsButtonListener(ActionListener l) {
            creditsButton.addActionListener(l);
        }
/*	private void Dodrawing(Graphics g, int w, int h, int x, int y){
        g.fillRect(x, y, w, h);
    }
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);	
		final Dimension screenSize = Utility.getFrameDimension(this);
	   // Dodrawing(g,(int)(screenSize.getWidth()*0.5),(int)(screenSize.getHeight()*0.5), 0, 0);
	}*/
	
	
  
	


}

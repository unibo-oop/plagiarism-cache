package view;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import hud.SetUpHud;
import lands.Lands.MyJButton;
import view.gamemap.DrawMap;
import view.gamemap.WorldGUI;
/**
 * 
 * Creation of the graphic interface with the various components
 *
 */
public class GUI extends JFrame implements GUIFactory{
	private List<String> carte=Arrays.asList();
	private JPanel w=new WorldGUI("risiko.png");
	
	public GUI(LinkedList<MyJButton> terr) {
	    this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	    this.setTitle("Risiko");
	    int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	    int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	    this.addMap(w);
	    this.addCardsGUI(ViewCards.getViewCards());
	    ViewCards.updatePanel(carte);
	    SetUpHud hudGioc = new SetUpHud();
	    this.addPlayerGUI(hudGioc.getHUD());
	    SetUpHud.updateHUD(controller.Controller.getActualPlayer());
	    SetUpHud.updateDice(new int[] {0, 0, 0}, new int[] {0, 0, 0});
	    this.setSize(width, height);
	    DrawMap.setMap(w, terr); 
	    //this.pack();
	    this.setVisible(true);
	}

	@Override
	public JFrame getFrame() {
		return this;
	}

	@Override
	public void addPlayerGUI(JPanel panel) {
		this.getContentPane().add(panel,BorderLayout.SOUTH);
		
	}

	@Override
	public void addCardsGUI(JPanel panel) {
		this.getContentPane().add(panel, BorderLayout.EAST);
		
	}

	@Override
	public void addMap(JPanel panel) {
		this.getContentPane().add(panel, BorderLayout.CENTER);
		
	}
}

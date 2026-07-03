package Gioco;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Scene extends JPanel {
	
	private ImageIcon icoFond ;
	private Image imgFond1 ;
	
	private ImageIcon icopilar ;
	private Image imgpilar ;
	
	private int xFond1;
	
	public Scene (){
		super();
		this.xFond1 = -50;
		icoFond = new ImageIcon(getClass().getResource("/imagine/background.png"));
		this.imgFond1 = this.icoFond.getImage();
		icopilar = new ImageIcon(getClass().getResource("/imagine/pilarD.png"));
		this.imgpilar = this.icopilar.getImage();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics g2 = (Graphics2D) g ;
		
		g2.drawImage(this.imgFond1, this.xFond1, 0, null);
		g2.drawImage(imgpilar, 300, 245, null);
	}
}

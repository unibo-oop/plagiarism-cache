package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Shape;
import java.awt.image.IndexColorModel;

import javax.swing.JLabel;
import javax.swing.JWindow;


public class Loader extends JWindow{
		
			
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public Loader() {
				this.setLayout(new BorderLayout());
		        JLabel loader = new JLabel(new javax.swing.ImageIcon("img/loading_text.gif"));
				this.add(loader,BorderLayout.CENTER);
				this.setLocationRelativeTo(null);
				this.setAlwaysOnTop(true);
				loader.setOpaque(true);
				this.setBackground(new Color(0,0,0,0));
				this.pack();
			}
		 
		 public void start() {
			this.setVisible(true);
		 }
		 
		 public void end() {
			 this.setVisible(false);
		 }
		
		 /**
		  * TEST
		  * @param args
		  */
		 public static void main(String[] args) {
			 new Loader().start();
		 }
	
}

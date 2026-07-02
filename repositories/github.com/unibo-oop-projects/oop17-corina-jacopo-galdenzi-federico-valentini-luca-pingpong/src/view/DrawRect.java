package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.*;

public class DrawRect {
	public static Rectangle drawRacket(int RECT_X,int RECT_Y,int RECT_WIDTH,int RECT_HEIGHT) {
		Rectangle r = new Rectangle(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT);
		return r;
	}
   /* private int RECT_X;
    private int RECT_Y;
   private int RECT_WIDTH;
   private int RECT_HEIGHT;

   public DrawRect(int width,int height) {
       this.RECT_X = (width*2)/100;
       this.RECT_Y = (height*30)/100;
       this.RECT_WIDTH = (width*1)/100;
       this.RECT_HEIGHT = (height*30)/100;
   }

   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      // draw the rectangle here
      //g.drawRect(RECT_X, RECT_Y, RECT_WIDTH, RECT_HEIGHT);
      //g.drawRect(15, 1, this.RECT_WIDTH, this.RECT_HEIGHT);
      g.setColor(Color.black);
      g.fillRect(this.RECT_X, this.RECT_Y, this.RECT_WIDTH, this.RECT_HEIGHT);
   }

   public Dimension getPreferredSize() {
      // so that our GUI is big enough
      return new Dimension(RECT_WIDTH, RECT_HEIGHT);
   }*/

   // create the GUI explicitly on the Swing event thread
 /*  private static void createAndShowGui() {
      Toolkit t = Toolkit.getDefaultToolkit();
      Dimension screenSize = t.getScreenSize();
      int width = (int) screenSize.getWidth();
      int height = (int) screenSize.getHeight();
      DrawRect mainPanel = new DrawRect(width/2,height/2);

      JFrame frame = new JFrame("DrawRect");
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setSize(width/2, height/2);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            createAndShowGui();
         }
      });
   }*/
}
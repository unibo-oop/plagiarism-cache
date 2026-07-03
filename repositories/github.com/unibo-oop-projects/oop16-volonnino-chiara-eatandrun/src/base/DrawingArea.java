package base;

/**
 * Draw a simple area in the panel
 */

import java.awt.*;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Chiara
 *
 */

public class DrawingArea extends JPanel {
	
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static final int SET_BACKGROUND = 25;
    
    private ArrayList<Drawing> obj;
	
    /**
     * 
     * @param name
     *        panel name
     * @param width
     *        length width
     * @param high
     *        length height
     */
    public DrawingArea(final String name, final int width, final int high) {
        
        obj = new ArrayList<Drawing>();
		
	this.setDoubleBuffered(true);
	this.setPreferredSize(new Dimension (width, high));
	this.setBackground(new Color(SET_BACKGROUND,SET_BACKGROUND,SET_BACKGROUND));
		
	JFrame marco = new JFrame(name);
	marco.setResizable(false);
	marco.setVisible(true);
	marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	marco.add(this);
	marco.pack();
		
	this.requestFocusInWindow();
	
    }
    
    /**
     * 
     * @param objs
     *        objects to add into a panel
     */
    public void add(Drawing objs) {
        obj.add(objs);
    }
	
    public void clears() {
	obj.clear();
    }
	
    @Override
    public void paint(Graphics graphic) {
	super.paint(graphic);
	for (Drawing objs : obj) {
	    objs.draw(graphic);
	}
    }
	
    public void remove(Drawing objs) {
	obj.remove(objs);
    }

}

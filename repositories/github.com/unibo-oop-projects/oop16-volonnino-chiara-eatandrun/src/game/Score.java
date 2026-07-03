package game;

/**
 * Implementation for counting accumulated points
 */

import java.awt.Font;
import java.awt.Graphics;

import base.Animation;
import base.Drawing;

/**
 * 
 * @author Chiara
 *
 */

public class Score implements Drawing, Animation {
    private int x;
    private int y; 
    private boolean visible; 
    private String str = "";
    private int a;
    
    /**
     * 
     * @param x 
     *        point x
     * @param y
     *        point y
     */
    public Score(final int x, final int y) {
        this.x = x; 
        this.y = y;
        this.setVisible(true);
    }
    
    @Override 
    public void draw(Graphics graphic) {
        if(isVisible()) {
            Font source = new Font("Monospaced", Font.BOLD, 20);
            graphic.setFont(source);
            graphic.drawString(str, x, y);
        }
    }
    
    @Override 
    public void animation() {
        final String b;
        b = Integer.toString(getA());
        final String str2; 
        str2 = "Score: "; 
        str = str2 + b;
    }
    
    public boolean isVisible() {
        return visible; 
    }
    
    public void setVisible (final boolean visible) {
        this.visible = visible; 
    }
    
    public void setA(final int a) {
        this.a = a;
    }
    
    public int getA() {
        return a;
    }   
    
}

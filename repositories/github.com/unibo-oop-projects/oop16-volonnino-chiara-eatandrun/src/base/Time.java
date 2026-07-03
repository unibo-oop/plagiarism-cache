package base;

/**
 * Implementation timer
 */

import java.awt.event.*;

import java.util.ArrayList;
import javax.swing.Timer;

/**
 * 
 * @author Chiara
 *
 */

public class Time extends Timer {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    private ArrayList<Animation> obj;
    
    /**
     * 
     * @param delay
     *        delay to load a elements
     * @param game
     *        object game
     */
    public Time(int delay, Game game){
	super(delay, game);
	obj = new ArrayList<Animation>();
    }
	
    /**
     * 
     * @param objs
     *       objects to add
     */
	
    public void add(Animation objs){
        obj.add(objs);
    }
    
    public void clears(){
        obj.clear();
    }
    
    @Override
    protected void fireActionPerformed(ActionEvent e){
        
        for (Animation objs : obj){
            objs.animation();
        }
        super.fireActionPerformed(e);
    }
}
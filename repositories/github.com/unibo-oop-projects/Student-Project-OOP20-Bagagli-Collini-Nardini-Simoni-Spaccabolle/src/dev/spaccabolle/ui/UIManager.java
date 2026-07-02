package dev.spaccabolle.ui;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import dev.spaccabolle.Handler;

/**
 * The Class UIManager.
 */
public class UIManager {

	/** The handler. */
	private Handler handler;
	
	/** The objects. */
	private ArrayList<UIObject> objects;
	
	/** The Constant yBtnLimits. */
	private static final int yBtnLimits = 600;
	
	/**
	 * Instantiates a new UI manager.
	 *
	 * @param handler the handler
	 */
	public UIManager(Handler handler){
		this.handler = handler;
		objects = new ArrayList<UIObject>();
	}
	
	/**
	 * Tick.
	 */
	public void tick(){
		for(UIObject o : objects) {
			o.tick();
			if(o.y>yBtnLimits) {
			    o.y-=2;
			    o.bounds.y=(int) o.y;
			}
		}
	}
	
	/**
	 * Render.
	 *
	 * @param g the g
	 */
	public void render(Graphics g){
		for(UIObject o : objects)
			o.render(g);
	}
	
	/**
	 * On mouse move.
	 *
	 * @param e the e
	 */
	public void onMouseMove(MouseEvent e){
		for(UIObject o : objects)
			o.onMouseMove(e);
	}
	
	/**
	 * On mouse release.
	 *
	 * @param e the e
	 */
	public void onMouseRelease(MouseEvent e){
		for(UIObject o : objects)
			o.onMouseRelease(e);
	}
	
	/**
	 * Adds the object.
	 *
	 * @param o the o
	 */
	public void addObject(UIObject o){
	    System.out.println(o.toString());
		objects.add(o);
	}
	
	/**
	 * Removes the object.
	 *
	 * @param o the o
	 */
	public void removeObject(UIObject o){
		objects.remove(o);
	}

	/**
	 * Gets the handler.
	 *
	 * @return the handler
	 */
	public Handler getHandler() {
		return handler;
	}

	/**
	 * Sets the handler.
	 *
	 * @param handler the new handler
	 */
	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	/**
	 * Gets the objects.
	 *
	 * @return the objects
	 */
	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	/**
	 * Sets the objects.
	 *
	 * @param objects the new objects
	 */
	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}
	
}
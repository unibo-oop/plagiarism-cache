package tq2.implementations.entity;

import java.awt.Color;
import java.awt.Font;

import tq2.implementations.HandlerImpl;
import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;

/**
 * The Class TextEntityResponsive is a TextEntity object that will keep its position regardless the
 * size of the window, and will adapt automatically on resize.
 * 
 * @author Francesco Gori
 */

public class TextEntityResponsive extends TextEntity {

	/** This number multiplied for the width of the window will result in the X coordinate of the object. */
	Double xPercent;
	
	/** This number multiplied for the height of the window will result in the Y coordinate of the object. */
	Double yPercent;
	
	/**
	 * Instantiates a new TextEntityResponsive element.
	 *
	 * @param text the text to be displayed
	 * @param xPercent the relative X position of the object in the window (percentage)
	 * @param yPercent the relative Y position of the object in the window (percentage)
	 * @param color the color of the text
	 * @param font the font of the text
	 * @param alignment the alignment of the text
	 * @param scaleX the X scale of the object
	 * @param scaleY the Y scale of the object
	 * @param alpha the alpha value of the object
	 * @param handler the Handler
	 * @param layer the layer this object resides in
	 */
	public TextEntityResponsive(String text, Double xPercent, Double yPercent, Color color, Font font, Integer alignment, Double scaleX, Double scaleY,
			Float alpha, Handler handler, LevelLayer layer) {
		super(text, 0, 0, color, font, alignment, scaleX, scaleY, alpha, handler, layer);
		this.yPercent = yPercent;
		this.xPercent = xPercent;
	}
		
	/**
	 * Instantiates a new TextEntityResponsive element.
	 *
	 * @param text the text to be displayed
	 * @param xPercent the relative X position of the object in the window (percentage)
	 * @param yPercent the relative Y position of the object in the window (percentage)
	 * @param color the color of the text
	 * @param font the font
	 * @param alignment the alignment
	 * @param alpha the alpha value of the object
	 * @param handler the Handler
	 * @param layer the layer this object resides in
	 */
	public TextEntityResponsive(String text, Double xPercent, Double yPercent, Color color, Font font, Integer alignment, Float alpha, Handler handler, LevelLayer layer) {
		this(text, xPercent, yPercent, color, font, alignment, 1.0, 1.0, alpha, handler, layer);
	}
	
	/**
	 * Instantiates a new TextEntityResponsive element.
	 *
	 * @param text the text to be displayed
	 * @param xPercent the relative X position of the object in the window (percentage)
	 * @param yPercent the relative Y position of the object in the window (percentage)
	 * @param color the color of the text
	 * @param font the font
	 * @param alignment the alignment
	 * @param handler the Handler
	 * @param layer the layer this object resides in
	 */
	public TextEntityResponsive(String text, Double xPercent, Double yPercent, Color color, Font font, Integer alignment, Handler handler, LevelLayer layer) {
		this(text, xPercent, yPercent, color, font, alignment, 1.0f, handler, layer);
	}
	
	/**
	 * Instantiates a new TextEntityResponsive element.
	 *
	 * @param text the text to be displayed
	 * @param xPercent the relative X position of the object in the window (percentage)
	 * @param yPercent the relative Y position of the object in the window (percentage)
	 * @param color the color of the text
	 * @param font the font
	 * @param handler the Handler
	 * @param layer the layer this object resides in
	 */
	public TextEntityResponsive(String text, Double xPercent, Double yPercent, Color color, Font font, HandlerImpl handler, LevelLayer layer) {
		this(text, xPercent, yPercent, color, font, LEFT, 1.0f, handler, layer);
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.TextEntity#tick()
	 */
	@Override
	public void tick() {
		//fit the size to the size of the window
		this.setX(this.getHandler().getGame().getGameWidth()*xPercent);
		this.setY(this.getHandler().getGame().getGameHeight()*yPercent);
	}
}

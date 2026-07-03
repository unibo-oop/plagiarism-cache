package tq2.implementations.entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import tq2.implementations.Id;
import tq2.interfaces.Handler;
import tq2.interfaces.LevelLayer;

/**
 * The Class TextEntity is an object that extends EntityImpl and can
 * be used to display texts via Graphics2D object.
 * 
 * @author Francesco Gori
 */
public class TextEntity extends EntityImpl {
	
	/** The Constant LEFT, used if the alignment of the text is left. */
	public static final int LEFT = 0;
	
	/** The Constant CENTER, used if the alignment of the text is center. */
	public static final int CENTER = 1;
	
	/** The Constant RIGHT, used if the alignment of the text is right. */
	public static final int RIGHT = 2;
	
	/** The text to be displayed. */
	protected String text;
	
	/** The color of the text. */
	protected Color color;
	
	/** The font of the text. */
	protected Font font;
	
	/** The alignment of the text. Either LEFT, CENTER or RIGHT. */
	protected Integer alignment;
	
	/**
	 * Instantiates a new TextEntity.
	 *
	 * @param text the text to be displayed
	 * @param x the x coordinate of the object
	 * @param y the y coordinate of the object
	 * @param color the color of the text
	 * @param font the font of the text
	 * @param alignment the alignment of the text. Either LEFT, CENTER or RIGHT
	 * @param scaleX the X scale of the object
	 * @param scaleY the Y scale of the object
	 * @param alpha the alpha value of the object
	 * @param handler the Handler
	 * @param layer the layer
	 */
	public TextEntity (String text, Integer x, Integer y, Color color, Font font, Integer alignment, Double scaleX, Double scaleY, Float alpha, Handler handler, LevelLayer layer) {
		super(x, y, 0, 0, 1, false, true, scaleX, scaleY, 0.0, 0.0, alpha, handler, layer);
	
		this.id = Id.textEntity;
		this.text = text;
		this.color = color;
		this.font = font;
		this.alignment = alignment;
	}
	
	/**
	 * Instantiates a new TextEntity.
	 *
	 * @param text the text to be displayed
	 * @param x the x coordinate of the object
	 * @param y the y coordinate of the object
	 * @param color the color of the text
	 * @param font the font of the text
	 * @param alignment the alignment of the text. Either LEFT, CENTER or RIGHT
	 * @param alpha the alpha value of the object
	 * @param handler the Handler
	 * @param layer the layer
	 */
	public TextEntity (String text, Integer x, Integer y, Color color, Font font, Integer alignment, Float alpha, Handler handler, LevelLayer layer)  {
		this(text, x, y, color, font, alignment, 1.0, 1.0, alpha, handler, layer);
	}
	
	/**
	 * Instantiates a new TextEntity.
	 *
	 * @param text the text to be displayed
	 * @param x the x coordinate of the object
	 * @param y the y coordinate of the object
	 * @param color the color of the text
	 * @param font the font of the text
	 * @param alignment the alignment of the text. Either LEFT, CENTER or RIGHT
	 * @param handler the Handler
	 * @param layer the layer
	 */
	public TextEntity (String text, Integer x, Integer y, Color color, Font font, Integer alignment, Handler handler, LevelLayer layer)  {
		this(text, x, y, color, font, alignment, 1.0f, handler, layer);
	}
	
	/**
	 * Instantiates a new TextEntity.
	 *
	 * @param text the text to be displayed
	 * @param x the x coordinate of the object
	 * @param y the y coordinate of the object
	 * @param color the color of the text
	 * @param font the font of the text
	 * @param handler the Handler
	 * @param layer the layer
	 */
	public TextEntity (String text, Integer x, Integer y, Color color, Font font, Handler handler, LevelLayer layer)  {
		this(text, x, y, color, font, LEFT, 1.0f, handler, layer);
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.EntityImpl#render(java.awt.Graphics2D)
	 */
	@Override
	public void render(Graphics2D g) {
		
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        
		g.setColor(this.color);
	    g.setFont(this.font);
		
		FontMetrics metrics = g.getFontMetrics(this.font);
		switch(this.alignment) {
			case LEFT:
				g.drawString(this.text, this.getX(), this.getY());
				break;
			case CENTER:
				g.drawString(this.text, this.getX() - (metrics.stringWidth(this.text) / 2), this.getY());
				break;
			case RIGHT:
				g.drawString(this.text, this.getX() - metrics.stringWidth(this.text), this.getY());
				break;
		}

        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
	}

	/* (non-Javadoc)
	 * @see com.tq2.implementations.entity.EntityImpl#tick()
	 */
	@Override
	public void tick() {
	
	}

}

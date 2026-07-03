package tq2.implementations.level;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import tq2.implementations.SoundImpl;
import tq2.implementations.entity.tile.*;
import tq2.implementations.input.MenuControls;
import tq2.interfaces.Entity;
import tq2.interfaces.Handler;

/**
 * This class is the menu for the game Toa's Quest 2. It's a very simple menu with a background image, a title and two buttons.
 * 
 * @author Francesco Gori
 */
public class TQMenu extends LevelImpl {

	/** The X center of the window. */
	protected Integer WINDOW_CENTER_X = this.getHandler().getGame().getGameWidth()/2;
	
	/** The Y center of the window. */
	protected Integer WINDOW_CENTER_Y = this.getHandler().getGame().getGameHeight()/2;
	
	/** The list of buttons. */
	LinkedList<Entity> buttons;
	
	/** The value that keeps track of the button selected in the menu. */
	Integer choice;
	
	/** The value used for the blinking of the button selected in the menu. */
	Float alphaInc;
	
	/**
	 * Instantiates a the menu, initializing the variables and loading the background music.
	 *
	 * @param handler the Handler
	 */
	public TQMenu(Handler handler) {
		super(handler);

		choice = 0;
		alphaInc = 0.018f;
		buttons = new LinkedList<Entity>();
		
		this.sounds.put("Main theme", new SoundImpl ("/sound/menu main theme.wav"));

	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.level.LevelImpl#getCursor()
	 */
	@Override
	public Cursor getCursor() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
	    Point hotSpot = new Point(0,0);
	    BufferedImage cursorImage = new BufferedImage(1, 1, BufferedImage.TRANSLUCENT); 
	    return toolkit.createCustomCursor(cursorImage, hotSpot, "InvisibleCursor");
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.level.LevelImpl#tick()
	 */
	@Override
	public void tick() {
		
		if (this.buttons.get(choice).getAlpha() < 0.3f) {
			this.alphaInc = -Math.abs(this.alphaInc);
		}
		if (this.buttons.get(choice).getAlpha() > 0.95f) {
			this.alphaInc = Math.abs(this.alphaInc);
		}
		
		this.buttons.get(choice).setAlpha(this.buttons.get(choice).getAlpha()-this.alphaInc);
		
	}
	
	/* (non-Javadoc)
	 * @see com.tq2.implementations.level.LevelImpl#loadLevel()
	 */
	@Override
	public void loadLevel() {
		
		super.loadLevel();
		
		LevelLayerImpl menu = new LevelLayerImpl (0.0, 0.0, true, this.getHandler().getLayers().size());
		
		menu.add (new RectangleBackground(Color.BLACK, 1.0f, this.getHandler(), menu));
		menu.add (new QuickBackground("/menu background.jpg", true, 1.0f, QuickBackground.KEEP_RATIO, this.getHandler(), menu));
		menu.add(new QuickTileResponsive(0.5, 0.2, 0.4,"/menu title.png", handler, menu));
		
		buttons.add(new QuickTileResponsive(0.5, 0.6, 0.26,"/New game btn.png", handler, menu));
		buttons.add(new QuickTileResponsive(0.5, 0.75, 0.1,"/Exit.png", handler, menu));


		
		for (Entity e:buttons) {
			menu.add (e);
		}

		this.getHandler().getLayers().add(menu);
		
		
				
		LevelLayerImpl gui = new LevelLayerImpl(0.0, 0.0, this.getHandler().getLayers().size());
		this.getHandler().getLayers().add(gui);
		
		
		
		this.loopSound("Main theme");
		
		this.controls = new MenuControls(this.getHandler(), this);
	}
	
	/**
	 * Returns the size of the list of buttons.
	 *
	 * @return the size of the list of buttons
	 */
	public Integer getButtonsSize() {
		return this.buttons.size();
	}
	
	/**
	 * This is to be used to move through the list of buttons in both directions.
	 * The index will automatically start over if the end of the list is reached,
	 * and vice versa.
	 *
	 * @param inc the value to add to the index of the list
	 */
	public void addChoice(Integer inc) {
		
		if (this.choice + inc >= this.buttons.size()) {
			this.buttons.get(choice).setAlpha(1.0f);
			this.choice = 0;
		}
		
		else if (this.choice + inc < 0) {
			this.buttons.get(choice).setAlpha(1.0f);
			this.choice = (this.buttons.size()-1);
		}
		else {
			this.buttons.get(choice).setAlpha(1.0f);
			this.choice += inc;
		}
	}
	
	/**
	 * Returns the index of the active button in the list.
	 *
	 * @return the index of the active button
	 */
	public Integer getChoice() {
		return this.choice;
	}

}

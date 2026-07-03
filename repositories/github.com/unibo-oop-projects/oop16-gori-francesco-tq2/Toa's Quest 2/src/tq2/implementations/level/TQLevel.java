package tq2.implementations.level;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import tq2.interfaces.Handler;
import tq2.interfaces.platform.tqgame.TQPlayer;

// TODO: Auto-generated Javadoc
/**
 * The Class TQLevel.
 */
public abstract class TQLevel extends LevelImpl {

	
	/** Whether the controls are enabled. */
	protected Boolean playerControlsEnabed;
	
	/** The player of this level. */
	protected TQPlayer player;
	
	/**
	 * Instantiates a new TQLevel.
	 *
	 * @param handler the Handler
	 */
	public TQLevel(Handler handler) {
		super(handler);
	}

	/**
	 * Returns the player of this level.
	 *
	 * @return the player
	 */
	public TQPlayer getPlayer() {
		return this.player;
	}

	/**
	 * Checks if controls are enabled.
	 *
	 * @return whether the controls are enabled
	 */
	public Boolean isControlsEnabled() {
		return this.playerControlsEnabed;
	}
	
	/**
	 * Sets whether the controls are enabled.
	 *
	 * @param playerControlsEnabed whether the controls enabled
	 */
	public void setControlsEnabled(Boolean playerControlsEnabed) {
		this.playerControlsEnabed = playerControlsEnabed;
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
}

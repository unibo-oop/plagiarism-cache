package hollowmen.view.ale;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import hollowmen.model.facade.DrawableRoomEntity;

/**
 * The GameInterface interface makes some methods available in order to 
 * set a ViewObserver,to draw all the elements on screen and to read the
 * gamer's inputs.
 * 
 * @author Alessia
 *
 */
public interface GameInterface {
	
	/**
	 * The method {@code draw} is used to draw all the components on screen.
	 * 
	 * @param componentList
	 */
	public void draw(List<DrawableRoomEntity> componentList);
	
	/**
	 * The method{@code setStorage} is used to create a list of JLabel with the associated {@link ImageIcon}.
	 * 
	 * @param storage
	 */
	public void setStorage(Map<String,ImageIcon> storage);
	
	/**
	 * The method {@code keyPressed} is used to read all the gamer's inputs.
	 */
	public void keyManager(KeyEvent e);
}

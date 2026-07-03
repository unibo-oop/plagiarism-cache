package view.images;

import javax.swing.ImageIcon;
import model.games.*;

/**
 * 
 * @author Stefano Benelli
 * Base representation of Image Set
 */
public interface ImageSet {
	
	/**
	 * Get the Image icon which represents the Choice
	 * @param choice Choice value
	 * @return ImageIcon of the corresponding Choice
	 */
	public ImageIcon getImage(Choice choice);
	
	/**
	 * Get the Image icon which represents the Hint
	 * @param hint Hint value
	 * @return ImageIcon of the corresponding Hint
	 */
	public ImageIcon getImage(Hint hint);
	
	/**
	 * Get the Question Mark Image icon
	 * @return ImageIcon of the Question Mark
	 */
	public ImageIcon getQuestionMarkImage();
}

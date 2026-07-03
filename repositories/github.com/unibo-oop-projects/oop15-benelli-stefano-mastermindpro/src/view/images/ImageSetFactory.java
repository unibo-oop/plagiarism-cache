package view.images;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Stefano Benelli
 * This class implements an Image Set Factory and Singleton
 */
public class ImageSetFactory {
	
	/**
	 * 
	 * @author Stefano Benelli
	 * This enum represents the list of the Image Set Groups
	 */
	public enum SetGroup {
		BUTTONS,
		STARS
	}
	
	//singleton instances
	private static final Map<SetGroup, ImageSet> sets = new HashMap<SetGroup, ImageSet>();
	private static SetGroup currentGroup = SetGroup.BUTTONS;

	/**
	 * Get the current Set Group
	 * @return Current Set Group
	 */
	public static SetGroup getCurrentGroup() {
		return ImageSetFactory.currentGroup;
	}
	

	/**
	 * Set the current Set Group
	 * @param currentGroup Current Set Group
	 */
	public static void setCurrentGroup(SetGroup currentGroup) {
		ImageSetFactory.currentGroup = currentGroup; 
	}
	
	/**
	 * Get an instance of the current ImageSet
	 * @return instance of current ImageSet
	 */
	public static ImageSet getCurrentInstance() {
		return ImageSetFactory.getInstance(ImageSetFactory.currentGroup);
	}

	private static ImageSet getInstance(SetGroup setGroup) {
		if(!sets.containsKey(setGroup)) {
			
			ImageSet newInstance;
			
			switch(setGroup)
			{
			case STARS:
				newInstance = new ImageSetStars();
				break;
			case BUTTONS:
			default:
				newInstance = new ImageSetButtons();
				break;
			}
			
			sets.put(setGroup, newInstance);
		}
		
		return sets.get(setGroup);
	}
}

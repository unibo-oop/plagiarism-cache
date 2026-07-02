package hollowmen.model;


/**
 * This interface represents an object that will modify something in the game
 * based on the point spend on it
 * @author pigio
 *
 */
public interface SkillNode extends InformationUser, LimitedCounter{
	
	/**
	 * This method gives the String that associates this node to all other nodes
	 * with the same tag
	 * @return
	 */
	public String getTag();
	
	/**
	 * This method give the level of this {@code SkillNode}
	 * @return {@code int}
	 */
	public int getLevel();
}

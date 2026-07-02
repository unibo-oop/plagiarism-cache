package cartasoci;
/**
 * 
 * @author Alberto Mulazzani
 *
 */
public interface IFidCard {
	
	/**
	 * Adds points to a User's FidCard.
	 * 
	 * @param points are the points to add
	 */
	void addPoints(final int points);
	
	/**
	 * Get the FidCard's point.
	 * 
	 * @return points of User
	 */
	Integer getPoints();
	

}

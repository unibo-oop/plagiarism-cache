package hollowmen.model.facade;

/**
 * 
 * @author Giordo
 *
 */
public class Point2DImpl implements Point2D{
	
	private int x;
	private int y;
	
	public Point2DImpl(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
	
}

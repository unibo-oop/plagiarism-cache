package characters;

public class PositionImpl implements Position {

	private double x;
	private double y;

	
	
	public PositionImpl(final double x2, final double y2) {
		this.x = x2;
		this.y = y2;
	}

	public PositionImpl() {
		this(0, 0);
	}

	public double getX() {
		return Double.valueOf(this.x);
	}

	public double getY() {
		return Double.valueOf(this.y);
	}

}

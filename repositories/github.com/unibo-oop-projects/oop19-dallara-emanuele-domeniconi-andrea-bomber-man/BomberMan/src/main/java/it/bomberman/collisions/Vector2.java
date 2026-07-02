package it.bomberman.collisions;

public class Vector2 {
	/**
	 * Vettore di due interi
	 */
	
	private int x;
	private int y;

	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double distance(Vector2 other) {
		int xOffset = this.x - other.getX();
		int yOffset = this.y - other.getY();
		return Math.sqrt(Math.abs(xOffset * xOffset + yOffset * yOffset));
	}
	
	public double distanceFromSegment(Vector2 p1, Vector2 p2) {
		/*
		var A = this.x - p1.getX();
		var B = this.y - p1.getY();
		var C = p2.getX() - p1.getX();
		var D = p2.getX() - p1.getX();
		
		var dot = A*C + B*D;
		var lenSq = C*C +D*D;
		*/
		
		return 0.0;
	}
	
	public static Vector2 unmodifiableVector2(Vector2 v) {
		return new Vecto2Decorator(v.getX(), v.getY());
	}
	private static final class Vecto2Decorator extends Vector2{
		public Vecto2Decorator(int x, int y) {
			super(x, y);
		}
		
		@Override
		public void setX(int x) {
			throw new UnsupportedOperationException();
		}
		
		public void setY(int y) {
			throw new UnsupportedOperationException();
		}
		
	}

}

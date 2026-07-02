package main.java.model;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;




public class TetrominoImpl implements Tetromino, Cloneable {
	private Point2D position;
	private final List<Point2D> tetrom;
	private final SquareColor color;
	private int grid = 0;
	private Shape shape;
	
	private TetrominoImpl(List<Point2D> pieces, Point2D position, SquareColor color, Shape shape){
		tetrom = new ArrayList<Point2D>(pieces);
		this.position = new Point2D(position.getX(),position.getY());
		this.color = color;
		this.shape=shape;
		tetrom.forEach(p -> this.grid = Math.max(p.getX(),Math.max(p.getX(),grid)));
		this.grid++;
	}
	
	public Shape getShape() {
		return this.shape;
	}

	public List<Square> getAllSquares() {
		List<Square> l= new LinkedList<>();
		tetrom.forEach(s -> l.add(new Square(s.getX(),s.getY(),this.color)));
		return l;
	}	
	
	public SquareColor getColor() {
		return this.color;
	}
	
	public void move(Direction dir) {
		switch (dir) {  //TODO
		case DOWN:
			position = new Point2D(position.getX(), position.getY()+1);
			break;
		case LEFT:
			position = new Point2D(position.getX()-1, position.getY());
			break;
		case RIGHT:
			position = new Point2D(position.getX()+1, position.getY());
			break;
		case UP:
			position = new Point2D(position.getX(), position.getY()+1);
			break;
		}
	}
	
	
	public void rotate(RotationSense rotSense) {
		switch (rotSense) {
		case CLOCKWISE:
			tetrom.stream().map(p->new Point2D((1-p.getY()-grid+2), p.getX()));
			break;
		case COUNTERCLOCKWISE:	// TODO
			for(int i=0;i<3;i++) {
				tetrom.stream().map(p->new Point2D((1-p.getY()-grid+2), p.getX()));
			}
		}
	}
	
	public Tetromino clone() {
		return new TetrominoImpl(tetrom, position, color, shape);
	}
	
	public static class Builder{
		private Point2D position = new Point2D(0,0); 
		private List<Point2D> tetrom = new ArrayList<>();
		private SquareColor color /*= black*/;
		private Shape shape = null; 
		
		public Builder() {}
		
		public Builder addPosition(Point2D position) {
			this.position = position;
			return this;
		}
		
		public Builder addPoint(Point2D piece) throws IllegalArgumentException{
			if(tetrom.size() == 4) {
				throw new IllegalArgumentException();
			}
			this.tetrom.add(piece);
			return this;
		}
		
		public Builder addColor(SquareColor color) {
			this.color=color;
			return this;
		}
		
		public Builder addShape(Shape shape) {
			this.shape = shape;
			return this;
		}
		
		public Tetromino build() throws IllegalStateException{
			if(tetrom.size() != 4) {
				throw new IllegalStateException();
			}
			return new TetrominoImpl(tetrom, position, color, shape);
		}
		
			
	}

}

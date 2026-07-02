package main.java.model;

public class TetrominoGenerator {
	public Tetromino newTetromino(Point2D position,Shape shape) throws IllegalStateException{
		switch(shape) {
		case I:
			return new TetrominoImpl.Builder()
						.addPosition(position)
						.addShape(shape)
						.addColor(SquareColor.BLUE)
						.addPoint(new Point2D (0,1))
						.addPoint(new Point2D (1,1))
						.addPoint(new Point2D (2,1))
						.addPoint(new Point2D (3,1))
						.build();
		case L:
			return new TetrominoImpl.Builder()
						.addPosition(position)
						.addShape(shape)
						.addColor(SquareColor.ORANGE)
						.addPoint(new Point2D (1,0))
						.addPoint(new Point2D (1,1))
						.addPoint(new Point2D (1,2))
						.addPoint(new Point2D (2,2))
						.build();
		case J:
			return new TetrominoImpl.Builder()
						.addPosition(position)
						.addShape(shape)
						.addColor(SquareColor.BLUE)
						.addPoint(new Point2D (1,0))
						.addPoint(new Point2D (1,1))
						.addPoint(new Point2D (1,2))
						.addPoint(new Point2D (0,2))
						.build();
		case O:
			return new TetrominoImpl.Builder()
						.addPosition(position)
						.addShape(shape)
						.addColor(SquareColor.YELLOW)
						.addPoint(new Point2D (0,0))
						.addPoint(new Point2D (1,1))
						.addPoint(new Point2D (1,0))
						.addPoint(new Point2D (0,1))
						.build();
		case S:
			return new  TetrominoImpl.Builder()
						.addPosition(position)
						.addShape(shape)
						.addColor(SquareColor.GREEN)
						.addPoint(new Point2D (0,1))
						.addPoint(new Point2D (1,1))
						.addPoint(new Point2D (1,0))
						.addPoint(new Point2D (2,0))
						.build();
		case Z:
			return new TetrominoImpl.Builder()
						.addPosition(position)
						.addShape(shape)
						.addColor(SquareColor.RED)
						.addPoint(new Point2D (0,0))
						.addPoint(new Point2D (1,1))
						.addPoint(new Point2D (1,0))
						.addPoint(new Point2D (2,1))
						.build();
		case T:
			return new TetrominoImpl.Builder()
						.addPosition(position)
						.addShape(shape)
						.addColor(SquareColor.VIOLET)
						.addPoint(new Point2D (0,1))
						.addPoint(new Point2D (1,1))
						.addPoint(new Point2D (1,0))
						.addPoint(new Point2D (2,1))
						.build();
		default:
				return new TetrominoImpl.Builder().build();
		}
	}
}

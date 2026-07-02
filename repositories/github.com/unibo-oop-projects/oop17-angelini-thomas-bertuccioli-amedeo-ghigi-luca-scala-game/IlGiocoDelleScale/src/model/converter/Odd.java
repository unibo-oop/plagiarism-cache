package model.converter;

import model.board.Coordinate;

public class Odd extends AbstractConverter{

	public Odd(int width) {
		
		super(width);
	}
	
	@Override
	public int getNumber(Coordinate coordinate) {
		return ((coordinate.getY()*super.getWidth())+(super.getWidth()-(coordinate.getX()+1)));
	}

	@Override
	public Coordinate getCoordinate(int num) {
		
		return new Coordinate((super.getWidth()-(num -(this.getHeight(num)*super.getWidth()))-1), (this.getHeight(num)));
	}

	
}

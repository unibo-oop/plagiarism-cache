package model.converter;

import model.board.Coordinate;

public abstract class AbstractConverter implements ConverterStrategy{

	private final int width;
	
	public AbstractConverter(int width) {
		this.width=width;
	}
	
	public abstract int getNumber(Coordinate coordinate);

	public abstract Coordinate getCoordinate(int num);
	
	protected int getHeight(int num){
		
		return (num/this.getWidth());
	}

	protected int getWidth() {
		return width;
	}

	
	
}

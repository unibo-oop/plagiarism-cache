package model.converter;

import model.board.Coordinate;

public interface ConverterStrategy {

	public int getNumber(Coordinate coordinate);

	public Coordinate getCoordinate(int num);
	
}

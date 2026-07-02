package model.converter;

import model.board.Coordinate;

public interface Converter {

	public int toInt(Coordinate coordinate);
	
	public Coordinate toCoordinate(int num);
	
}

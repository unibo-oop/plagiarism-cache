package model.board;

public interface UpsideDown {
	
	boolean isInPosition(Coordinate position);
	
	Coordinate getTarget();
	
	UpsideDownType getType();
}

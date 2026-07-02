package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import enumeration.Characters;
import model.board.Coordinate;
import model.dice.Dice;

public interface Controller {

	
	public void start(Map<Optional<Integer>, enumeration.Dice> DiceMap, int lastNumber, List<Characters> Character);
	
	public void FinishGame(int turn) throws IOException;
	
	public void Play();
	
	public int ConverteToInt(Coordinate coordinate);
	
	public Coordinate ConverteToCoordinate(int pos);


}

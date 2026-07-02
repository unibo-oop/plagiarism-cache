package model.movements;

import model.pawns.Pawns;

public interface Movements {

	public void changePosition(Pawns p);
	
	public int dicePosition(int initialPosition, int diceNumber);
	
	public boolean checkWin(int pos);	
	
}

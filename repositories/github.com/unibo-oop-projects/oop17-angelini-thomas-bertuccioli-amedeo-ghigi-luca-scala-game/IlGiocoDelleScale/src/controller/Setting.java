package controller;

import model.data.Data;

public interface Setting {

	public int getNumPlayers();
	
	public Data getData();
	
	public int moveTurn();
	
	public int getTurn();
	
}

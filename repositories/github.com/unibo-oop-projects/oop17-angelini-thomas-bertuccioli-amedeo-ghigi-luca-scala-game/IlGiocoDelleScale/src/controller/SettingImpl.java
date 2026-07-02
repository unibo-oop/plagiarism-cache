package controller;

import model.data.Data;

public class SettingImpl implements Setting {

	private final int NumPlayers;
	private Data data;
	private int turn;
	private final static int Start=0;
	
	public SettingImpl(final int NumPlayers, Data data) {
		this.NumPlayers = NumPlayers;
		this.data = data;
		this.turn=Start;
	}
	
	@Override
	public int getNumPlayers() {
		return this.NumPlayers;
	}
	
	@Override
	public Data getData(){
		return this.data;
	}
	
	@Override
	public int moveTurn() {
		this.turn=this.turn++;
		return (this.turn % this.NumPlayers);
	}


	@Override
	public int getTurn() {
		return this.turn;
	}
	
}

package model.map;

import utilityclasses.Pair;

public class MapTileImpl implements MapTile {


	Pair<Integer,Integer> position;
	Status status;
	
	/**
     * Constructor
     */
	public MapTileImpl(int x,int y) {
		
		position = new Pair<>(x,y);
		status = Status.EMPTY;
	}
	
	public MapTileImpl() {
		
	}
	
	public Pair<Integer, Integer> getPosition() {
		return position;
	}
	
	public void setPosition(Pair<Integer, Integer> position) {
		this.position = position;
	}
	
	public Status getStatus() {
		return status;
	}
	
	public void setStatus(Status status) {
		this.status = status;
	}
	
}

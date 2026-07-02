package model.players;

import java.util.ArrayList;
import java.util.List;

import controller.PlayerColor;
import model.board.Tile;
import model.board.TileType;
import model.utils.Direction;

public class PlayerImpl implements Player {

	private List<Tile> playerTreasures = new ArrayList<Tile>();
	
	private final String playerName;
	private Integer playerScore = 0;
	private Direction direction = Direction.DEEP;
	private final PlayerColor playerColor;
	
	/**
	 * this is the constructor class.
	 * 
	 * @param name    name of the player
	 * 
	 * @param playerColor    color choose for represent player
	 */
	public PlayerImpl(final String name, final PlayerColor playerColor) {
		this.playerName = name;
		this.playerColor = playerColor;
		
	}
	
	@Override
	public void changeDirectionToBoat() {
	    if(this.direction.equals(Direction.DEEP)) {
		    this.direction = Direction.TO_BOAT;
	    }
		
	}

	@Override
	public void setDirectionToDeep() {
		this.direction = Direction.DEEP;
		
	}

	@Override
	public List<Tile> getPlayerTreasures() {
		return this.playerTreasures;
	}

	@Override
	public boolean pickUpTreasure(final Tile treasure) {
		if(!treasure.getType().equals(TileType.EMPTY.tileType())){
			return playerTreasures.add(treasure);
		}else {
			return false;
		}
		
		
	}

	@Override
	public Tile chooseTileToRelease(final int index) {
        return this.getPlayerTreasures().remove(index);
	}

	@Override
	public Direction getDirection() {
		return this.direction;
	}

	@Override
	public String getPlayerName() {
		return this.playerName;
	}

	@Override
	public PlayerColor getPlayerColor() {
		return this.playerColor;
	}

	@Override
	public Integer getPlayerScore() {
		return this.playerScore;
	}

	@Override
	public void resetTreasureCarried() {
		this.playerTreasures = new ArrayList<Tile>();
		
	}

	@Override
	public void setPlayerScore(final Integer value) {
		this.playerScore = value;
		
	}
	
	

}

package javarogue.playablecharacter;

import javarogue.behaviourmodules.Movable;
import javarogue.tileengine.Tile;
import javarogue.utility.Direction;
import javarogue.utility.Position;

/**
 * class that implements PlayerManager
 */
public class PlayerManagerImpl implements PlayerManager, Movable {

	private PlayableCharacter character;
	private javarogue.level.Level currentLevel;
	
	public PlayerManagerImpl(javarogue.level.Level level) {
		this.currentLevel = level;
	}
	
	@Override
	public void makeCharacter(Position originPosition) {
		this.character = new PlayableCharacter(this.currentLevel);
		this.character.setPosition(originPosition);
	}
	
	@Override
	public void setLevel(javarogue.level.Level level) {
		this.currentLevel = level;
	}

	@Override
	public void moveCharacter(Direction direction) {
		this.changePosition(direction);
	}

	@Override
	public Position getPosition() {
		return this.character.getPosition();
	}

	@Override
	public PlayableCharacter getCharacter() {
		return this.character;
	}

	@Override
	public void changePosition(Direction direction) {
		final Position futurePosition = direction.newPos(this.getPosition());
		// System.out.println("Position is " + futurePosition);
		if (checkNonOutOfBounds(futurePosition)) {
			if (this.currentLevel.getTileMap().get(futurePosition.getX(), futurePosition.getY()).equals(Tile.FLOOR)
					|| this.currentLevel.getTileMap().get(futurePosition.getX(), futurePosition.getY())
							.equals(Tile.FLOOR_VAULT)) {
				this.character.setPosition(futurePosition);
			}
		}
	}

	/**
	 * checks if the future position the character will move is in the bounds of the
	 * matrix
	 * 
	 * @param futurePosition the position
	 * @return if the character can move
	 */
	private boolean checkNonOutOfBounds(Position futurePosition) {
		if ((futurePosition.getX() >= 0) && (futurePosition.getX() < this.currentLevel.getTileMap().getRows())
				&& (futurePosition.getY() >= 0) && (futurePosition.getY() < this.currentLevel.getTileMap().getRows())) {
			return true;
		}
		return false;
	}
}

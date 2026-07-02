package javarogue.level;

import javarogue.playablecharacter.PlayerManager;
import javarogue.playablecharacter.PlayerManagerImpl;
import javarogue.tileengine.Tile;
import javarogue.utility.Direction;
import javarogue.utility.Matrix;
import javarogue.utility.Position;

public class BossLevel implements Level {

	private Matrix<Tile> tiles;
	private Matrix<Tile> objects;
	
	private PlayerManager pm;

	@Override
	public Matrix<Tile> getTileMap() {
		return this.tiles;
	}

	@Override
	public Matrix<Tile> getObjectMap() {
		return this.objects;
	}

	@Override
	public void generate() {
		this.tiles = new Matrix<>(15, 15);
		this.objects = new Matrix<>(15, 15);

		this.tiles.fill(Tile.BLOCK);
		this.objects.fill(Tile.ALPHA);

		this.tiles.doubleFor(1, this.tiles.getRows() - 1, 1, 1, this.tiles.getCols() - 1, 1, (i, j) -> {
			this.tiles.set(i, j, Tile.FLOOR);
		});
		
		this.tiles.set(5, 5, Tile.STAIRS_UP);
		this.objects.set(8, 8, Tile.BOSS);
		
		this.pm = new PlayerManagerImpl(this);
		this.pm.makeCharacter(new Position(5, 5));
	}

	@Override
	public Position getPlayerPos() {
		return this.pm.getPosition();
	}

	@Override
	public void moveCharacter(Direction dir) {
		this.pm.moveCharacter(dir);
	}

}

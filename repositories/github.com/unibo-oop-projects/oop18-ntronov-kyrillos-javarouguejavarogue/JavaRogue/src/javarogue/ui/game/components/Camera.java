package javarogue.ui.game.components;

import javafx.scene.canvas.GraphicsContext;
import javarogue.config.ConfigGraphics;
import javarogue.level.Level;
import javarogue.tileengine.Tile;
import javarogue.tileengine.TileSet;
import javarogue.utility.Direction;
import javarogue.utility.Matrix;
import javarogue.utility.Position;

public class Camera {

	private GraphicsContext context;
	private Level level;
	private Position origin;
	private TileSet tileset;
	
	private int rowCount;
	private int colCount;
	private double tileSize;
	
	public Camera(GraphicsContext context) {
		this.context = context;
		// Load tileset
		this.tileset = new TileSet(ConfigGraphics.tilesetPath);
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public void setOrigin(Position origin) {
		this.origin = origin;
	}

	public void move(Direction dir) {
		this.origin = dir.newPos(this.origin);
	}

	public void draw() {
		// Draw tile matrix based on camera origin and scale
		this.rowCount = 11;
		this.colCount = 18;
		this.tileSize = this.context.getCanvas().getWidth() / colCount;
		this.setOrigin(new Position(this.level.getPlayerPos().getX() - rowCount / 2 + 1,
				this.level.getPlayerPos().getY() - colCount / 2 + 1));
		this.drawMatrix(this.level.getTileMap());
		this.drawMatrix(this.level.getObjectMap());
		// Draw player in the middle
		/*
		this.context.drawImage(this.tileset.getTile(Tile.PLAYER), this.origin.getY() + (colCount / 2 - 1) * tileSize,
				this.origin.getX() + (rowCount / 2 - 1) * tileSize, tileSize, tileSize);
				*/
	}

	private void drawMatrix(Matrix<Tile> matrix) {
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				Tile tile;
				if (this.origin.getX() + i < 0 || this.origin.getY() + j < 0
						|| this.origin.getX() + i >= matrix.getRows()
						|| this.origin.getY() + j >= matrix.getCols()) {
					tile = Tile.VOID;
				} else {
					tile = matrix.get(this.origin.getX() + i, this.origin.getY() + j);
				}
				this.context.drawImage(this.tileset.getTile(tile), j * tileSize, i * tileSize, tileSize, tileSize);
				if(new Position(this.origin.getX() + i, this.origin.getY() + j).equals(this.level.getPlayerPos())) {
					this.context.drawImage(this.tileset.getTile(Tile.PLAYER), j * tileSize, i * tileSize, tileSize, tileSize);
				}
			}
		}
	}
	
}

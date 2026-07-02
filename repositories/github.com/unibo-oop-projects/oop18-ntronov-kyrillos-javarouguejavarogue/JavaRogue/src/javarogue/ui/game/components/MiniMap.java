package javarogue.ui.game.components;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javarogue.level.Level;
import javarogue.tileengine.Tile;

public class MiniMap {

	private GraphicsContext context;
	private Level level;

	public MiniMap(GraphicsContext context) {
		this.context = context;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public void draw() {
		double scale = 8;
		double offsetCol = this.context.getCanvas().getWidth() - this.level.getTileMap().getCols() * scale;
		this.level.getTileMap().doubleFor((i, j) -> {
			if (this.level.getTileMap().get(i, j).equals(Tile.FLOOR)) {
				this.context.setFill(Color.rgb(225, 225, 225, 0.5));
				this.context.fillRect(j * scale + offsetCol, i * scale, scale, scale);
			} else if(this.level.getTileMap().get(i, j).equals(Tile.WATER)) {
				this.context.setFill(Color.rgb(100, 100, 225, 0.5));
				this.context.fillRect(j * scale + offsetCol, i * scale, scale, scale);
			} else if(this.level.getTileMap().get(i, j).equals(Tile.FLOOR_VAULT)) {
				this.context.setFill(Color.rgb(225, 225, 100, 0.5));
				this.context.fillRect(j * scale + offsetCol, i * scale, scale, scale);
			}
			if (this.level.getObjectMap().get(i, j).equals(Tile.STAIRS_DOWN)) {
				this.context.setFill(Color.rgb(225, 0, 0, 0.5));
				this.context.fillRect(j * scale + offsetCol, i * scale, scale, scale);
			} else if (this.level.getObjectMap().get(i, j).equals(Tile.STAIRS_UP)) {
				this.context.setFill(Color.rgb(0, 0, 225, 0.5));
				this.context.fillRect(j * scale + offsetCol, i * scale, scale, scale);
			}
		});
	}

}

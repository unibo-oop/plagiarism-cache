package javarogue.tileengine;

import javarogue.config.ConfigGraphics;
import javarogue.utility.Position;

/**
 * 
 * The collection of possible tiles to be "cut" out of the tileset. The
 * implementation is image-independent as long as the new tileset image respects
 * the same grid order of tile types. Use the getOrigin() function to get the
 * upper-right corner in the image. ConfigView provides the tileSize.
 * 
 *
 */
public enum Tile {

	VOID {
		@Override
		public Position getOrigin() {
			int tilesetRow = 0;
			int tilesetColumn = 0;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}
	},
	CORNER_NW {

		@Override
		public Position getOrigin() {
			int tilesetRow = 0;
			int tilesetColumn = 1;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	WALL_N {

		@Override
		public Position getOrigin() {
			int tilesetRow = 0;
			int tilesetColumn = 2;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	CORNER_NE {

		@Override
		public Position getOrigin() {
			int tilesetRow = 0;
			int tilesetColumn = 3;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	BLOCK {

		@Override
		public Position getOrigin() {
			int tilesetRow = 0;
			int tilesetColumn = 4;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	FLOOR_VAULT {

		@Override
		public Position getOrigin() {
			int tilesetRow = 0;
			int tilesetColumn = 5;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	STAIRS_UP {

		@Override
		public Position getOrigin() {
			int tilesetRow = 1;
			int tilesetColumn = 0;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	WALL_W {

		@Override
		public Position getOrigin() {
			int tilesetRow = 1;
			int tilesetColumn = 1;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	FLOOR {

		@Override
		public Position getOrigin() {
			int tilesetRow = 1;
			int tilesetColumn = 2;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	WALL_E {

		@Override
		public Position getOrigin() {
			int tilesetRow = 1;
			int tilesetColumn = 3;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	DOOR_VERT {

		@Override
		public Position getOrigin() {
			int tilesetRow = 1;
			int tilesetColumn = 4;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	DOOR_VERT_LOCKED {
		@Override
		public Position getOrigin() {
			int tilesetRow = 1;
			int tilesetColumn = 5;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}
	},
	STAIRS_DOWN {

		@Override
		public Position getOrigin() {
			int tilesetRow = 2;
			int tilesetColumn = 0;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	CORNER_SW {

		@Override
		public Position getOrigin() {
			int tilesetRow = 2;
			int tilesetColumn = 1;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	WALL_S {

		@Override
		public Position getOrigin() {
			int tilesetRow = 2;
			int tilesetColumn = 2;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	CORNER_SE {

		@Override
		public Position getOrigin() {
			int tilesetRow = 2;
			int tilesetColumn = 3;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	DOOR_HORZ {

		@Override
		public Position getOrigin() {
			int tilesetRow = 2;
			int tilesetColumn = 4;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	DOOR_HORZ_LOCKED {

		@Override
		public Position getOrigin() {
			int tilesetRow = 2;
			int tilesetColumn = 5;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	ALPHA {

		@Override
		public Position getOrigin() {
			int tilesetRow = 3;
			int tilesetColumn = 0;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	WATER {
		@Override
		public Position getOrigin() {
			int tilesetRow = 3;
			int tilesetColumn = 1;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}
	},
	TRAP {

		@Override
		public Position getOrigin() {
			int tilesetRow = 3;
			int tilesetColumn = 2;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	CHEST {

		@Override
		public Position getOrigin() {
			int tilesetRow = 3;
			int tilesetColumn = 3;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	FOUNTAIN {

		@Override
		public Position getOrigin() {
			int tilesetRow = 3;
			int tilesetColumn = 4;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	SHRINE {

		@Override
		public Position getOrigin() {
			int tilesetRow = 3;
			int tilesetColumn = 5;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	WEAPON {
		@Override
		public Position getOrigin() {
			int tilesetRow = 4;
			int tilesetColumn = 0;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}
	},
	POTION {
		@Override
		public Position getOrigin() {
			int tilesetRow = 4;
			int tilesetColumn = 1;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}
	},
	ARMOR {
		@Override
		public Position getOrigin() {
			int tilesetRow = 4;
			int tilesetColumn = 2;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}
	},
	SCROLL {
		@Override
		public Position getOrigin() {
			int tilesetRow = 4;
			int tilesetColumn = 3;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}
	},
	OBJECT {
		@Override
		public Position getOrigin() {
			int tilesetRow = 4;
			int tilesetColumn = 4;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}
	},
	PLAYER {
		@Override
		public Position getOrigin() {
			int tilesetRow = 4;
			int tilesetColumn = 5;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}
	},
	SLIME {

		@Override
		public Position getOrigin() {
			int tilesetRow = 5;
			int tilesetColumn = 0;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	RAT {

		@Override
		public Position getOrigin() {
			int tilesetRow = 5;
			int tilesetColumn = 1;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	THIEF {

		@Override
		public Position getOrigin() {
			int tilesetRow = 5;
			int tilesetColumn = 2;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	GOBLIN {

		@Override
		public Position getOrigin() {
			int tilesetRow = 5;
			int tilesetColumn = 3;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	GOLEM {

		@Override
		public Position getOrigin() {
			int tilesetRow = 5;
			int tilesetColumn = 4;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}

	},
	BOSS {
		@Override
		public Position getOrigin() {
			int tilesetRow = 5;
			int tilesetColumn = 5;
			return new Position(tilesetColumn * ConfigGraphics.tileSize, tilesetRow * ConfigGraphics.tileSize);
		}
	};

	/**
	 * 
	 * @return The origin (upper-right corner) of the tile relative to the tileset.
	 *         It respects the tile size provided in the ViewConfig.
	 */
	public abstract Position getOrigin();

}

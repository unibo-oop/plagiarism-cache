package boxhead.view.world.tile;

/**
 * Enum to model the type of tiles.
 */
public enum TileType {
	GROUND(1),

	WALL(2),

	ZOMBIE_SPAWN(3),

	AMMO_SPAWN(4),
	
	GRASS(5),
	
	CONCRETE(6);

	private final int id;

	TileType(final int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static TileType getFromId(final int id) {
		final TileType res = null;
		for (final TileType t : TileType.values()) {
			if (t.getId() == id) {
				return res;
			}
		}
		return res;
	}
}

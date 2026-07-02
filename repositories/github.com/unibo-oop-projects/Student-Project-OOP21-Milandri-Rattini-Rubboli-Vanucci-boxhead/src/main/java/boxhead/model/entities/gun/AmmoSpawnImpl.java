package boxhead.model.entities.gun;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javafx.geometry.Point2D;

/**
 * Implementation of {@link AmmoSpawn}
 */
public class AmmoSpawnImpl implements AmmoSpawn{
	
	private static final long AMMO_TIME_RESPAWN = 8000;
	private static final double AMMO_WIDTH = 40;
	private static final double AMMO_HEIGTH = 40;
	
	private final Set<Ammo> ammoActive;
	private Map<Point2D, Long> ammoSpawnPoints;
	
	/**
	 * Constructor
	 */
	public AmmoSpawnImpl() {
		this.ammoActive = new HashSet<>();
		this.ammoSpawnPoints = new HashMap<>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Set<Ammo> getAmmoActive() {
		return this.ammoActive;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setAmmoSpawnPoints(final Set<Point2D> ammoSpawnPoints) {
		ammoSpawnPoints.forEach(p -> this.ammoSpawnPoints.put(p, System.currentTimeMillis()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void removeAmmo(final Ammo ammo) {
		this.ammoActive.remove(ammo);
		this.ammoSpawnPoints.forEach((p,t) -> {
			if (p.equals(ammo.getPosition())) {
				this.ammoSpawnPoints.replace(p, System.currentTimeMillis());
			}
		});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void update() {
		long now = System.currentTimeMillis();
		this.ammoSpawnPoints.forEach((p, t) -> {
			if (now - t > AMMO_TIME_RESPAWN && t != 0) {
				this.ammoActive.add(new Ammo(p, AMMO_WIDTH, AMMO_HEIGTH));
				this.ammoSpawnPoints.replace(p, (long) 0);
			}
		});
	}
}

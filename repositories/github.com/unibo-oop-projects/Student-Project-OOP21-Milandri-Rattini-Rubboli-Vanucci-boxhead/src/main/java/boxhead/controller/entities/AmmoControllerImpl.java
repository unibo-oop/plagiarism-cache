package boxhead.controller.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import boxhead.model.entities.gun.Ammo;
import boxhead.model.entities.gun.AmmoSpawn;
import boxhead.model.entities.gun.AmmoSpawnImpl;
import boxhead.view.entities.AmmoView;
import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;

/**
 * Implementation of {@link AmmoController}.
 */
public class AmmoControllerImpl implements AmmoController{

	private final AmmoSpawn spawn;
	private final Map<Ammo, AmmoView> ammoActive;
	
	/**
	 * Constructor that takes all the ammoSpawnPoints.
	 * @param ammoSpawnPoints
	 */
	public AmmoControllerImpl(final Set<Point2D> ammoSpawnPoints) {
		this.spawn = new AmmoSpawnImpl();
		this.spawn.setAmmoSpawnPoints(ammoSpawnPoints);
		this.ammoActive = new HashMap<>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Set<BoundingBox> getAmmos() {
		return this.ammoActive.keySet().stream().map(a -> a.getBoundingBox()).collect(Collectors.toSet());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void removeAmmo(final BoundingBox ammoBB) {
		Ammo ammo = this.ammoActive.keySet().stream().filter(a -> a.getBoundingBox().equals(ammoBB)).findFirst().get();
		this.ammoActive.remove(ammo, this.ammoActive.get(ammo));
		this.spawn.removeAmmo(ammo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update() {
		this.spawn.update();
		this.spawn.getAmmoActive().forEach(a -> {
			if (!(this.ammoActive.containsKey(a))) {
				this.ammoActive.put(a, new AmmoView());
			}
		});
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Map<Ammo, AmmoView> getAmmoView() {
		return this.ammoActive;
	}

}

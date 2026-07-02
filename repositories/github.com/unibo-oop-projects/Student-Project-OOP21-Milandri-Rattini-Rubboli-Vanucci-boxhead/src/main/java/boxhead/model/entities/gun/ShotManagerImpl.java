package boxhead.model.entities.gun;

import java.util.HashSet;
import java.util.Set;

import boxhead.model.entities.utils.Collision;
import boxhead.model.entities.zombies.ZombieModel;
import javafx.geometry.BoundingBox;

/**
 * Implementation of {@link ShotManager}
 */
public class ShotManagerImpl implements ShotManager {

	private final Set<Shot> shotsActive;
	private final Set<Shot> ended;
	private final ZombieModel zombieModel;
	private Set<BoundingBox> walls;
	
	/**
	 * @param zombieModel
	 * 			The ZombieModel that will be associate to the ShotManager.
	 */
	public ShotManagerImpl(final ZombieModel zombieModel) {
		this.shotsActive = new HashSet<>();
		this.zombieModel = zombieModel;
		this.ended = new HashSet<>();
		this.walls = new HashSet<>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void addShot(final Shot s) {
		this.shotsActive.add(s);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void removeShot(final Shot s) {
		if (this.shotsActive.contains(s)) {
			this.shotsActive.remove(s);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Set<Shot> getShotsActive() {
		return this.shotsActive;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void update() {
		final Set<Shot> delete = new HashSet<>();
		this.shotsActive.forEach(s -> {
			s.update();
			this.zombieModel.getZombies().stream()
									 	 .filter(z -> Collision.isColliding(s.getBoundingBox(), z.getBoundingBox()))
										 .forEach(z -> {
											 this.zombieModel.hitZombie(z, s.getDamage());
											 delete.add(s);
											 });
			if(!delete.contains(s) && this.walls.stream().anyMatch(w -> Collision.isColliding(w, s.getBoundingBox()))) {
				delete.add(s);
			}
		});
		this.ended.addAll(delete);
		delete.forEach(s -> this.shotsActive.remove(s));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Set<Shot> getShotsEnded() {
		final Set<Shot> tmp = new HashSet<>(this.ended);
		this.ended.clear();
		return tmp;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setWalls(final Set<BoundingBox> walls) {
		this.walls = walls;
	}
}

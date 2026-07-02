package boxhead.controller.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import boxhead.controller.game.GameLevel;
import boxhead.model.entities.gun.Shot;
import boxhead.model.entities.gun.ShotManager;
import boxhead.model.entities.gun.ShotManagerImpl;
import boxhead.view.entities.BulletView;
import boxhead.view.entities.ShotView;
import javafx.geometry.BoundingBox;

/**
 * Implementation of {@link ShotController}
 */
public class ShotControllerImpl implements ShotController {

	private final ShotManager manager;
	private final Map<Shot, ShotView> shotsActiveView;
	
	/**
	 * @param world
	 * 			The world where the ShotController will be used.
	 */
	public ShotControllerImpl(final GameLevel level) {
		this.manager = new ShotManagerImpl(level.getZombieController().getZombieModel());
		this.shotsActiveView = new HashMap<>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Set<Shot> getShotsActive() {
		return this.manager.getShotsActive();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setWalls(Set<BoundingBox> walls) {
		this.manager.setWalls(walls);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Map<Shot, ShotView> getShots() {
		return this.shotsActiveView;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void addShot(Optional<Shot> shot) {
		if (shot.isPresent()) {
			ShotView view = new BulletView();
			view.setDirection(Math.toDegrees(shot.get().getTrajectory().getAngle() + Math.PI/2));
			this.manager.addShot(shot.get());
			this.shotsActiveView.put(shot.get(), view);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void update() {
		this.manager.update();
		final Set<Shot> delete = this.manager.getShotsEnded();
		delete.stream().filter(s -> shotsActiveView.containsKey(s)).forEach(shotsActiveView::remove);
	}

}

package zombieversity.controller.entities;

import java.util.HashSet;
import java.util.Set;

import javafx.geometry.Point2D;
import javafx.util.Pair;
import zombieversity.controller.core.GameWorld;
import zombieversity.model.entities.zombie.ZombieAIImpl;
import zombieversity.model.entities.zombie.ZombieModel;
import zombieversity.model.entities.zombie.ZombieModelImpl;
import zombieversity.view.entities.ZombieView;
import zombieversity.view.entities.ZombieViewImpl;

/**
 * Implementation of {@link ZombieController}.
 *
 */
public class ZombieControllerImpl implements ZombieController {

    private final ZombieModel zombies;
    private final GameWorld gameWorld;
    private final ZombieView zombieView;

    /**
     * Instantiates a {@link ZombieControllerImpl}.
     * @param gameWorld game main controller.
     */
    public ZombieControllerImpl(final GameWorld gameWorld) {
        this.gameWorld = gameWorld;
        this.zombies = new ZombieModelImpl();
        this.zombieView = new ZombieViewImpl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void update() {
        this.zombies.update();
        this.updateView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ZombieModel getZombieModel() {
        return this.zombies;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final ZombieView getZombieView() {
        return this.zombieView;
    }

    /**
     * Used to update zombies images in zombie view.
     */
    private void updateView() {
        final Set<Pair<Point2D, Double>> positions = new HashSet<>();
        this.zombies.getZombies().forEach(zombie -> {
            positions.add(new Pair<>(zombie.getPosition(), ZombieAIImpl.getAngleToPlayer(zombie, this.gameWorld.getPlayerController().getEntity())));
        });
        this.zombieView.setPositionsAndDirections(positions);
        this.zombieView.update();
    }

}

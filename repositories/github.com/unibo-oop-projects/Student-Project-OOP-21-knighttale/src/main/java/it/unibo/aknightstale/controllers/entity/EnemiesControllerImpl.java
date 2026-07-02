package it.unibo.aknightstale.controllers.entity;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.aknightstale.controllers.interfaces.EnemiesController;
import it.unibo.aknightstale.controllers.interfaces.MapController;
import it.unibo.aknightstale.models.Enemy;
import it.unibo.aknightstale.models.entity.Character;
import it.unibo.aknightstale.models.entity.factories.EntityFactory;
import it.unibo.aknightstale.utils.CollisionManager;
import it.unibo.aknightstale.utils.Point2D;
import it.unibo.aknightstale.views.entity.AnimatedEntityView;
import it.unibo.aknightstale.views.interfaces.MapView;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * The type Enemies controller.
 */
public class EnemiesControllerImpl implements EnemiesController {

    private int numEnemies;
    private final EntityFactory factory;

    private final List<CharacterController<? super Character, ? super  AnimatedEntityView>> enemiesControllers;

    private final MapView mapView;
    private final MapController mapController;

    /**
     * Instantiates a new Enemies controller.
     *
     * @param numEnemies the num enemies
     * @param mapView    the map view
     * @param factory    the factory
     */
    @SuppressFBWarnings("EI_EXPOSE_REP2")
    public EnemiesControllerImpl(final int numEnemies, final MapView mapView, final EntityFactory factory, final MapController mapController) {
        enemiesControllers = new LinkedList<>();
        this.factory = factory;
        this.mapView = mapView;
        this.mapController = mapController;
        createEnemies(numEnemies);
    }

    /**
     * Gets a list that contains the controllers of all enemies.
     *
     * @return the enemies controllers list.
     */
    @SuppressFBWarnings("EI_EXPOSE_REP")
    public List<CharacterController<? super Character, ? super  AnimatedEntityView>> getEnemiesControllers() {
        return enemiesControllers;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void createEnemies(final int numEnemies) {
        this.numEnemies += numEnemies;

        for (int i = 0; i < this.numEnemies; i++) {
            final var enemy = this.factory.getEnemy(new Point2D(0, 0));
            this.mapController.setSpawnPosition(enemy);
            enemiesControllers.add(enemy);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawEnemies(final CollisionManager collision, final CharacterController player) {

        this.enemiesControllers.forEach((c) -> {

            if (collision.checkCollision(c).contains(player)) {
                c.attack();
            }

            switch (c.getModel().getDirection()) {
                case LEFT:
                    c.moveLeft();
                    break;
                case RIGHT:
                    c.moveRight();
                    break;
                case UP:
                    c.moveUp();
                    break;
                case DOWN:
                    c.moveDown();
                    break;
                default:
                    break;
            }


            mapView.draw(c.getView(), c.getModel().getPosition().getX(), c.getModel().getPosition().getY());
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeDeadEnemies() {
        final List<CharacterController<? super Character, ? super  AnimatedEntityView>> killedEnemies = new ArrayList<>();
        this.enemiesControllers.forEach(c -> {
            if (c.getModel().getHealth() == 0) {
                killedEnemies.add(c);
                this.factory.getEntityManager().removeEntity(c);
            }
        });
        enemiesControllers.removeAll(killedEnemies);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDirection(final Point2D playerPosition) {
        this.enemiesControllers.forEach(c -> {
            ((Enemy) c.getModel()).update(playerPosition);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void adaptPositionToScreenSize(final double traslX, final double traslY) {
        this.enemiesControllers.forEach(c -> {
            c.adaptPositionToScreenSize(traslX, traslY);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getNumEnemy() {
        return this.enemiesControllers.size();
    }
}

package model.physics;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import controller.gameloop.GameController;
import model.Model;
import model.entities.Enemy;
import model.entitiesutil.MovementValue;

/**
 * Class of manage the {@link Enemy}s movements in autonomy. It calculates all
 * movements based on the current position of the {@link Hero}.
 *
 */
public class EnemyAIImpl implements EnemyAI {

    private static final int DELAY = (int) (TimeUnit.SECONDS.toMillis(1) / GameController.FPS);
    private static final int TIME_TO_UPDATE = 900;
    private final Enemy enemy;
    private Optional<MovementValue> enemyAction;
    private final Model model;
    private int deltaT;

    /**
     * Creates an independent AI for a specific enemy.
     * 
     * @param enemy The specific {@link Enemy} which have to be controlled.
     * @param model {@link Model} is used for getting the {@link Hero}.
     */
    public EnemyAIImpl(final Enemy enemy, final Model model) {
        this.deltaT = 0;
        this.model = Objects.requireNonNull(model);
        this.enemy = Objects.requireNonNull(enemy);
        this.enemyAction = Optional.empty();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextMove() {
        if (!this.enemyAction.isPresent()) {
            this.upgradeActions();
        }
        if (this.deltaT < TIME_TO_UPDATE - DELAY) {
            this.deltaT += DELAY;
            this.actionPerform();
        } else {
            this.upgradeActions();
            this.actionPerform();
            this.deltaT = 0;
        }
    }

    /*
     * Perform the saved action for the enemy
     */
    private void actionPerform() {
        if (!this.enemyAction.isPresent()) {
            throw new IllegalStateException();
        }
        switch (this.enemyAction.get()) {
        case CHARACTER_MOVE_LEFT:
            enemy.move(MovementValue.CHARACTER_MOVE_LEFT);
            break;
        case CHARACTER_MOVE_RIGHT:
            enemy.move(MovementValue.CHARACTER_MOVE_RIGHT);
            break;
        default:
            throw new IllegalStateException();
        }
    }

    /*
     * After TIME_TO_UPDATE It upgrade enemy direction based on Hero position
     */
    private void upgradeActions() {
        final int heroX = Objects.requireNonNull(this.model.getLevel().getHero()).getPosition().getX();
        final int heroY = Objects.requireNonNull(this.model.getLevel().getHero()).getPosition().getY();

        final int enemyX = this.enemy.getPosition().getX();
        final int enemyY = this.enemy.getPosition().getY();

        if (heroX < enemyX) {
            this.enemyAction = Optional.of(MovementValue.CHARACTER_MOVE_LEFT);
        } else if (enemyX <= heroX) {
            this.enemyAction = Optional.of(MovementValue.CHARACTER_MOVE_RIGHT);
        }

        if (heroY < enemyY) { // y are inverted
            this.enemy.move(MovementValue.CHARACTER_JUMP);
        }
    }

}

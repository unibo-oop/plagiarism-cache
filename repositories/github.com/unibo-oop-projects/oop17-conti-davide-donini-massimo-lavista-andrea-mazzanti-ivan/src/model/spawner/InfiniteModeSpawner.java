package model.spawner;

import java.util.Collection;
import java.util.Optional;
import java.util.Random;

import model.entities.Enemy;
import model.entities.Spaceship;

/**
 * 
 * Spawner for the infinite mode of the game.
 *
 */
public final class InfiniteModeSpawner extends StoryModeSpawner {

    private static final int STATES_NUMBER = 7;
    private static final int FINAL_CASE = 5;

    private double difficulty;
    private final Spaceship spaceship;

    /**
     * 
     * @param spaceship
     *            of the player.
     */
    public InfiniteModeSpawner(final Spaceship spaceship) {
        super(spaceship);
        this.spaceship = spaceship;
        this.difficulty = 1;
    }

    @Override
    public Collection<Enemy> spawn() {
        final Collection<Enemy> enemies = super.spawn();

        if (super.getState().get().isStateEnded()) {
            super.setState(this.createRandomState());
            this.difficulty++;
        }

        return enemies;
    }

    private Optional<State> createRandomState() {
        final int i = new Random().nextInt(STATES_NUMBER);
        State state;

        switch (i) {
        case 0:
            state = new BacksideKamikazeState(this.spaceship, Math.sqrt(this.difficulty));
            break;
        case 1:
            state = new DoubleLineState(this.spaceship, Math.sqrt(this.difficulty));
            break;
        case 2:
            state = new DoubleStarsState(this.spaceship, Math.sqrt(this.difficulty));
            break;
        case 3:
            state = new FiveKamikazeState(this.spaceship, Math.sqrt(this.difficulty));
            break;
        case 4:
            state = new ObliqueAimEnemiesState(this.spaceship, Math.sqrt(this.difficulty));
            break;
        case FINAL_CASE:
            state = new PerimetralState(this.spaceship, Math.sqrt(this.difficulty));
            break;
        default:
            state = new X3x3ShootEnemyState(this.spaceship, Math.sqrt(this.difficulty));
            break;
        }

        return Optional.of(state);
    }
}

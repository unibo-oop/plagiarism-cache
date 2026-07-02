package it.unibo.oop.manpac.view.screens.game;


import static it.unibo.oop.manpac.utils.SoundManager.SoundName;

import it.unibo.oop.manpac.model.Action;
import it.unibo.oop.manpac.model.Directions;
import it.unibo.oop.manpac.model.Entity;
import it.unibo.oop.manpac.utils.SoundManager;

/**
 * Implementation of GameViewHelper interface.
 */
public class ActionsResultProcessorImpl implements ActionsResultProcessorObserver {

    private final ActionsResultProcessorObservable view;

    private final SoundManager soundManager = SoundManager.getSoundManager();

    private boolean alternalPillSound = true;

    /**
     * Constructor of the GameViewHelperImpl class.
     * @param view the GameView
     */
    public ActionsResultProcessorImpl(final ActionsResultProcessorObservable view) {
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pacmanCollisionHelper(final Action action) {
        switch (action) {
        case POWER_PILL_EATEN:
            this.view.enablePower();
        case PILL_EATEN:
            if (alternalPillSound) {
                soundManager.play(SoundName.EAT_PILL_ONE);
            } else {
                soundManager.play(SoundName.EAT_PILL_TWO);
            }
            alternalPillSound = !alternalPillSound;
            this.view.readyToDestroy();
            break;
        case PHANTOM_KILLED_PACMAN:
            soundManager.play(SoundName.DEATH);
            this.view.lifeJustLost(true);
            break;
        case PACMAN_EFFECT:
            soundManager.play(SoundName.PACMAN_EFFECT);
            this.view.pacmanEffect();
            break;
        case GAME_OVER:
            this.view.setGameOver(false);
            break;
        case WIN:
            this.view.setGameOver(true);
            break;
        case PACMAN_ATE_PHANTOM:
            soundManager.play(SoundName.EAT_PHANTOM);
            this.view.phantomJustEaten(true);
            break;
        default:
            break;
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pacmanChangeDirectionHelper(final Action directionAction) {
        switch (directionAction) {
        case DIRECTION_CHANGED_UP:
            this.view.setPacmanDirection(Directions.UP);
            break;
        case DIRECTION_CHANGED_DOWN:
            this.view.setPacmanDirection(Directions.DOWN);
            break;
        case DIRECTION_CHANGED_LEFT:
            this.view.setPacmanDirection(Directions.LEFT);
            break;
        case DIRECTION_CHANGED_RIGHT:
            this.view.setPacmanDirection(Directions.RIGHT);
            break;
        case DIRECTION_CHANGED_STOP:
            this.view.setPacmanDirection(Directions.STOP);
            break;
        default:
            break;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void phantomChangeDirectionHelper(final Entity phantomName, final Action action) {
        switch (action) {
        case DIRECTION_CHANGED_UP:
            this.view.updatePhantomTexture(phantomName, Directions.UP);
            break;
        case DIRECTION_CHANGED_DOWN:
            this.view.updatePhantomTexture(phantomName, Directions.DOWN);
            break;
        case DIRECTION_CHANGED_LEFT:
            this.view.updatePhantomTexture(phantomName, Directions.LEFT);
            break;
        case DIRECTION_CHANGED_RIGHT:
            this.view.updatePhantomTexture(phantomName, Directions.RIGHT);
            break;

        default:
            break;
        }
    }

}

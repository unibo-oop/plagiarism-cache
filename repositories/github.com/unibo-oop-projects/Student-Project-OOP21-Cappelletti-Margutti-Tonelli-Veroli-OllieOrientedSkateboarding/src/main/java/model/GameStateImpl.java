package model;

import java.util.List;

import javafx.geometry.Dimension2D;
import model.entity.DynamicEntity;
import model.entity.manager.EntityManager;
import model.entity.manager.EntityManagerImpl;
import model.player.Player;
import model.player.PlayerImpl;
import sound.SoundFactory;

/**
 * 
 * An implementation of {@link GameState}.
 *
 */
public class GameStateImpl implements GameState {

    private final Player player;
    private final EntityManager entityManager;

    /**
     * Creates a new GameStateImpl initially with a new Player and a new EntityManager.
     * @param gameDimension the dimension of the game screen.
     * @param soundFactory the {@link SoundFactory}.
     * 
     */
    public GameStateImpl(final Dimension2D gameDimension, final SoundFactory soundFactory) {
        this.player = new PlayerImpl(soundFactory);
        this.entityManager = new EntityManagerImpl(gameDimension);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer() {
        return this.player;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DynamicEntity> getEntities() {
        return this.entityManager.getEntities();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        return this.player.getLives() < 1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.player.updateJump();
        this.entityManager.updateList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVelocity(final double difficulty) {
        this.entityManager.setSpeedX(difficulty);
    }

}

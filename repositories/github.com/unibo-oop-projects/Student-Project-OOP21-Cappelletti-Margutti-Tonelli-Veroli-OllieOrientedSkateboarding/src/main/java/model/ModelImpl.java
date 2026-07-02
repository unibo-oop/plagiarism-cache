package model;

import java.util.List;

import javafx.geometry.Dimension2D;
import model.collision.CollisionManager;
import model.collision.CollisionManagerImpl;
import model.entity.DynamicEntity;
import model.marker.MarkerManager;
import model.marker.MarkerManagerImpl;
import model.mission.MissionManager;
import model.mission.MissionManagerImpl;
import model.player.Player;
import model.statistic.Statistics;
import model.statistic.StatisticsImpl;
import model.statistic.StatisticsUpdater;
import sound.SoundFactory;

/**
 * 
 * An implementation of {@link Model}.
 *
 */
public class ModelImpl implements Model {

    private final SoundFactory soundFactory;
    private final GameState gameState;
    private final Statistics statistics;
    private final CollisionManager collisionManager;
    private final MarkerManager markerManager;
    private final MissionManager missionManager;
    private final StatisticsUpdater statisticsUpdater;

    /**
     * Creates a new ModelImpl.
     * @param width the width of the game screen.
     * @param height the height of the game screen.
     * @param soundFactory the {@link SoundFactory}.
     * 
     */
    public ModelImpl(final double width, final double height, final SoundFactory soundFactory) {
        this.soundFactory = soundFactory;
        this.gameState = new GameStateImpl(new Dimension2D(width, height), this.soundFactory);
        this.statistics = new StatisticsImpl();
        this.collisionManager = new CollisionManagerImpl();
        this.markerManager = new MarkerManagerImpl(this.statistics.getLastDeathDistance(), this.statistics.getRecordDistance());
        this.missionManager = new MissionManagerImpl(this);
        this.statisticsUpdater = new StatisticsUpdater(this.statistics, this.markerManager, this.missionManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameState getGameState() {
        return this.gameState;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Statistics getStatistics() {
        return this.statistics;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StatisticsUpdater getStatisticsUpdater() {
        return this.statisticsUpdater;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MarkerManager getMarkerManager() {
        return this.markerManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MissionManager getMissionManager() {
        return this.missionManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SoundFactory getSoundFactory() {
        return this.soundFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.gameState.update();
        this.markerManager.update(this.statistics.getDifficulty());
        this.missionManager.update();

        final Player player = this.gameState.getPlayer();
        final List<DynamicEntity> entities = this.gameState.getEntities();
        this.collisionManager.playerCollidesWidth(player, entities, this);
        this.gameState.setVelocity(this.statistics.getDifficulty());
    }

}

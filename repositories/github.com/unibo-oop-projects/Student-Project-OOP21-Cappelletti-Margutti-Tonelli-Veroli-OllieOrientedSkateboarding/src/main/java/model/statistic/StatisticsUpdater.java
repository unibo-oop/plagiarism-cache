package model.statistic;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import model.marker.MarkerManager;
import model.mission.MissionManager;

/**
 * 
 * Updater of {@link Statistics} every given seconds.
 * Implements {@link Runnable}.
 *
 */
public class StatisticsUpdater implements Runnable {

    private static final int TIME_RATE_IN_SECONDS = 1;
    private final ScheduledExecutorService executor;
    private final Statistics statistics;
    private final MarkerManager markerManager;
    private final MissionManager missionManager;

    /**
     * Creates a new StatisticsUpdater with given {@link Statistics}, {@link MarkerManager} and {@link MissionManager}.
     * @param statistics the {@link Statistics} to update.
     * @param markerManager the {@link MarkerManager}.
     * @param missionManager the {@link MissionManager}.
     */
    public StatisticsUpdater(final Statistics statistics, final MarkerManager markerManager, final MissionManager missionManager) {
        super();
        this.executor = Executors.newScheduledThreadPool(1);
        this.executor.scheduleAtFixedRate(this, 0, TIME_RATE_IN_SECONDS, TimeUnit.SECONDS);
        this.statistics = statistics;
        this.markerManager = markerManager;
        this.missionManager = missionManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        this.statistics.update();
        this.markerManager.checkCreateMarker(this.statistics.getDistance());
    }

    /**
     * Starts execution of run method every given time rate.
     *
     */
    public void start() {
        this.run();
    }

    /**
     * Stops run execution.
     * 
     */
    public void stop() {
        this.executor.shutdown();
        this.statistics.increaseCoin(this.missionManager.isCompleted() ? this.missionManager.getReward() : 0);
        try {
            this.statistics.saveStatisticsOnFile();
        } catch (IOException e) {
            System.out.println("Statistics unsaved");
        }
    }

}

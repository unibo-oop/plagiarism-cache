package model.mission;

import java.util.Optional;

import model.Model;

/**
 * 
 * Implementation of {@link MissionManager}.
 *
 */
public class MissionManagerImpl implements MissionManager {

    private final Optional<Mission> mission;

    /**
     * Creates a new MissionManagerImpl and creates a new random {@link Mission}.
     * @param model the {@link Model}.
     */
    public MissionManagerImpl(final Model model) {
        super();
        final MissionFactory missionFactory = new MissionFactoryImpl(model);
        this.mission = Optional.of(missionFactory.createMission());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Mission> getMission() {
        if (this.mission.isEmpty()) {
            return Optional.empty();
        }
        return this.mission;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        this.mission.get().updateCounter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCompleted() {
        if (this.mission.isEmpty()) {
            return false;
        }
        return this.mission.get().isCompleted();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getReward() {
        return this.mission.get().getReward();
    }

}

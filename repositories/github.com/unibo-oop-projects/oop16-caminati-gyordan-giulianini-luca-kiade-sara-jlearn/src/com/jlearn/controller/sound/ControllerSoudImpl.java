package com.jlearn.controller.sound;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * This class wrap {@link AudioAgentImpl} for apply some check and perform good Mediaplayer.
 */
public class ControllerSoudImpl implements ControllerSound {

    private static final Logger LOG = Logger.getLogger(ControllerSoudImpl.class);
    private final AudioAgent agentAudioExercise;

    @Override
    public String toString() {
        return "ControllerSoudImpl [agentAudioExercise=" + this.agentAudioExercise + "]";
    }

    /**
     * Return an new IstaNCE OF {@link ControllerSoudImpl}.
     */
    public ControllerSoudImpl() {
        LOG.setLevel(Level.WARN);
        this.agentAudioExercise = new AudioAgentImpl();
    }

    @Override
    public void changeTrack(final String filename) {
        this.agentAudioExercise.changeTrack(filename);
    }

    @Override
    public boolean checkAudio() {
        return (this.agentAudioExercise != null) && this.agentAudioExercise.canStart();
    }

    @Override
    public void cleanTrack() {
        this.agentAudioExercise.cleanTrack();
    }

    @Override
    public final Boolean pauseAudioExercise() {
        if (this.checkAudio()) {
            LOG.info("Audio paused");
            this.agentAudioExercise.pausePlayer();
            return true;
        }
        return false;
    }

    @Override
    public final Boolean playAudioExercise() {
        if (this.checkAudio()) {
            LOG.info("Audio played");
            this.agentAudioExercise.startPlayer();
            return true;
        }
        return false;
    }

    @Override
    public final Boolean stopAudioExercise() {
        if (this.checkAudio()) {
            LOG.info("Audio stopped");
            this.agentAudioExercise.stopPlayer();
            return true;
        }
        return false;
    }

}

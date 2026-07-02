package it.dpg.minigames.ballgame.model;

import it.dpg.minigames.ballgame.controller.BallMinigameLevel;

public interface BallEnvironmentFactory {
    BallEnvironment createEnvironment(BallMinigameLevel level);
}

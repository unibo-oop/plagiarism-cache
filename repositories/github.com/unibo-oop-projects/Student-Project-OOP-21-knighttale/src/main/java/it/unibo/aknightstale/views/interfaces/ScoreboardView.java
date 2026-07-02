package it.unibo.aknightstale.views.interfaces;

import it.unibo.aknightstale.controllers.interfaces.ScoreboardController;

import java.util.Map;
import java.util.Set;

public interface ScoreboardView extends View<ScoreboardController> {
    void updateScoreboard(Set<Map.Entry<String, Integer>> scoreboard);
}

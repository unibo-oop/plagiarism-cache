package it.unibo.aknightstale.controllers.interfaces;

import it.unibo.aknightstale.views.interfaces.ScoreboardView;

import java.util.Map.Entry;
import java.util.Set;

public interface ScoreboardController extends Controller<ScoreboardView> {
    Set<Entry<String, Integer>> getScoreboard();
    void returnToMainMenu();
}

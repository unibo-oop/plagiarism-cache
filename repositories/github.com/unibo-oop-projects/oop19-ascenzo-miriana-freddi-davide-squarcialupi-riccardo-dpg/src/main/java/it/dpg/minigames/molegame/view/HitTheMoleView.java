package it.dpg.minigames.molegame.view;

import it.dpg.maingame.view.View;
import it.dpg.minigames.molegame.model.Mole;
import it.dpg.minigames.molegame.model.Score;
import javafx.util.Pair;

import java.util.List;

public interface HitTheMoleView extends View {

    /**
     * update the score of the view
     */
    void updateScore(Score score);

    /**
     * decrease the timer
     */
    void updateTimer(long time);

    /**
     * update the view of out moles
     *
     * @param moleOut list of the out mole
     */
    void updateMole(List<Pair<Integer, Mole>> moleOut);

}

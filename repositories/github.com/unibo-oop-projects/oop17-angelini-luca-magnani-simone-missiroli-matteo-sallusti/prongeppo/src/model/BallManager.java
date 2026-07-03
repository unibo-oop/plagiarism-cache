package model;

import java.util.List;

import javafx.util.Pair;
import model.Ball.Combo;

/**
 * @author Missi
 *
 */
public interface BallManager {
    /**
     * @param list **the hittable bar list**
     * @param combo **the combo to trigger in case of combo**
     */
    void collisionCheck(List<Bar> list, Combo combo);
    /**
     * needed for particular, multi cycle related combos.
     */
    void comboHandler();
    /**
     * reset the internal values of this obj.
     */
    void reset();
    /**
     * 
     * @return pair of combos of the teams for this ball (Pair(team1,team2))
     */
    Pair<Integer, Integer> getComboCount();

    /**
     * @param list **the list of pickup to check**
     * @return the list of collected pickups
     */
    List<PickUp> collectedPickups(List<PickUp> list);
}

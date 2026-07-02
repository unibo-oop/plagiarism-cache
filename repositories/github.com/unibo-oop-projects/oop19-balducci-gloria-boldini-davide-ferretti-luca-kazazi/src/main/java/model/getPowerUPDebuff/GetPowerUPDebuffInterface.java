package model.getPowerUPDebuff;

import model.gameObject.GameObject;
import other.Pair;

public interface GetPowerUPDebuffInterface {

    /**
     * @param pos
     * get random PowerUP for a specific position
     * @return random gameObject PowerUP
     */
    GameObject getRandomPowerUP(Pair<Integer, Integer> pos);

    /**
     * @param pos
     * get random Debuff for a specific position
     * @return random gameObject Debuff
     */
    GameObject getRandomDebuff(Pair<Integer, Integer> pos);

}

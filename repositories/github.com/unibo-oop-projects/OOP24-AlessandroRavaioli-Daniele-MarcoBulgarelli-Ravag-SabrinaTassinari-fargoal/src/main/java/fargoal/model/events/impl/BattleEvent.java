package fargoal.model.events.impl;

import java.util.List;
import java.util.Random;

import fargoal.model.events.api.FloorEvent;

/**
 * BattleEvent is a class called everytime that
 * a battle between the player and a monster is underway.
 */
public class BattleEvent implements FloorEvent {
    private final Random random;
    private final List<String> list = List.of("Chop!", "Clang!",
            "Ouch!", "Thud!", "Clink!", "Shriek!", "Slash!", "Shred!",
            "Ugh!", "Claw!", "Crunch!", "Gnarl!", "Growl!", "Thump!");

    /**
     * Constructor that initializes the random.
     */
    public BattleEvent() {
        this.random = new Random();
    }

    /**
     * Method that returns a random textSound for the battle
     * to be displayed on the screen.
     * 
     * @return a string for the battle
     */
    public String getTextSound() {
        return list.get(random.nextInt(list.size()));
    }
}

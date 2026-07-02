package it.unibo.monopoli.model.mainunits;

import java.util.Random;

import javax.swing.JOptionPane;

import it.unibo.monopoli.view.C;

/**
 * This is an implementation of the classic {@link Dice} with 6 faces.
 *
 */
public class ClassicDice implements Dice {

    private static final int FACES = 6;
    private static final Random RANDOM = new Random();

    @Override
    public int roll() {
        if (C.DEBUG) {
            final int randVal = RANDOM.nextInt(FACES) + 1;
            final String res = JOptionPane.showInputDialog("Dice result", randVal);
            return Integer.parseInt(res);
        } else {
            return RANDOM.nextInt(FACES) + 1;
        }
    }

}

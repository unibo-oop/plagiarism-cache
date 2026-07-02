package it.unibo.monopoli.model.actions;

import java.util.LinkedList;
import java.util.List;

import it.unibo.monopoli.model.mainunits.ClassicDice;
import it.unibo.monopoli.model.mainunits.Dice;
import it.unibo.monopoli.model.mainunits.Player;

/**
 * In this {@link DicesStrategy}, dices are two. If you double and you are not
 * in prison, you can roll dices again, else, if you are in prison, you can go
 * out of there.
 *
 */
public class ClassicDicesStrategy implements DicesStrategy {

    @Override
    public List<Dice> getDices() {
        final List<Dice> dices = new LinkedList<>();
        dices.add(new ClassicDice());
        dices.add(new ClassicDice());
        return dices;
    }

    @Override
    public void nowPlay(final Player player) {
        if (!player.isInPrison()) {
            player.getPawn().setPos(player.getPawn().getActualPos() + player.lastDicesNumber().get(0)
                    + player.lastDicesNumber().get(1));
        }
        if (this.twice(player)) {
            if (player.isInPrison()) {
                player.setPrison(false);
                player.getPawn().setPos(player.getPawn().getActualPos() + player.lastDicesNumber().get(0)
                        + player.lastDicesNumber().get(1));
            } else {
                player.setDicesRoll(false);
                player.setIfIsTheFirstLaunch(false);
            }
        } else {
            if (player.isInPrison() && player.howManyTurnsHasBeenInPrison() < 3) {
                player.incrementsTurnsInPrison();
            }
        }
    }

    private boolean twice(final Player player) {
        return player.lastDicesNumber().get(0) == player.lastDicesNumber().get(1);
    }

}

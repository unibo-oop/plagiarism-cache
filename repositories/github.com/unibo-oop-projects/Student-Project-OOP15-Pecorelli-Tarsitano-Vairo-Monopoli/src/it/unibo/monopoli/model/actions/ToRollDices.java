package it.unibo.monopoli.model.actions;

import java.util.LinkedList;
import java.util.List;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.ChanceCards;
import it.unibo.monopoli.model.cards.CommunityChestCards;
import it.unibo.monopoli.model.mainunits.Dice;
import it.unibo.monopoli.model.mainunits.Player;
import it.unibo.monopoli.model.table.Box;

/**
 * This class represent one of the {@link Action}s of the game. This one is for
 * rolling dices.
 *
 */
public class ToRollDices implements Action {

    private static final int POLICE_POSITION = 30;
    private static final int FIRST_USEFUL_POSITION = 28;
    private static final int LAST_USEFUL_POSITION = 11;

    private final DicesStrategy strategy;
    private final List<Dice> dices;
    private final Box prison;

    /**
     * Constructs a new instance of {@link ToRollDices}'s {@link Action}. The
     * {@link DicesStrategy} in input specifies how to use this dices.
     * 
     * @param strategy
     *            - the {@link DicesStrategy} to use
     * @param prison
     *            - the {@link Box} prison, where players have to go if they
     *            stop to the police's {@link Box}
     */
    public ToRollDices(final DicesStrategy strategy, final Box prison) {
        this.strategy = strategy;
        this.dices = strategy.getDices();
        this.prison = prison;
    }

    @Override
    public void play(final Player player) {
        if (player.isInPrison()) {
            if (player.howManyTurnsHasBeenInPrison() == 3) {
                player.setPrison(false);
            } else if (!player.getCards().isEmpty()) {
                for (final Card c : player.getCards()) {
                    if (c.getID() == ChanceCards.CARD4.getID() || c.getID() == CommunityChestCards.CARD4.getID()) {
                        player.setPrison(false);
                        player.removeCard(c);
                        break;
                    }
                }
            }
        }
        final List<Integer> dicesNumbers = new LinkedList<>();
        this.dices.stream().forEach(d -> {
            dicesNumbers.add(d.roll());
        });
        player.setLastDicesNumber(dicesNumbers);
        player.setDicesRoll(true);
        this.strategy.nowPlay(player);
        if (this.policePos(player)) {
            new GoToPrison(this.prison).play(player);
        }
        if (this.isPassedFromStartBox(player)) {
            new PassFromStar().play(player);
        }
    }

    private boolean policePos(final Player player) {
        return player.getPawn().getActualPos() == POLICE_POSITION;
    }

    private boolean isPassedFromStartBox(final Player player) {
        return player.getPawn().getPreviousPos() >= FIRST_USEFUL_POSITION
                && player.getPawn().getActualPos() <= LAST_USEFUL_POSITION;
    }

}

package it.unibo.monopoli.model.mainunits;

import java.util.Iterator;
import java.util.List;

import it.unibo.monopoli.model.cards.Card;
import it.unibo.monopoli.model.cards.Deck;
import it.unibo.monopoli.model.table.Box;

/**
 * This class implements the contract of {@link GameVersion} to bring back the
 * right game's version.
 *
 */
public class GameVersionImpl implements GameVersion {

    private final GameStrategy strategy;
    private final List<Player> players;
    private Iterator<Player> iter;
    private Player actualPlayer;

    /**
     * Constructs an instance that will be able to give back the right version
     * thanks to the {@link GameStrategy} passed in input.
     * 
     * @param strategy
     *            - the {@link GameStrategy} who will report the strategy to
     *            implement the right {@link GameVersion}
     */
    public GameVersionImpl(final GameStrategy strategy) {
        this.strategy = strategy;
        this.players = strategy.getPlayers();
        this.iter = this.players.iterator();
    }

    @Override
    public Bank getBank() {
        return this.strategy.getBank();
    }

    @Override
    public List<Box> getAllBoxes() {
        return this.strategy.getBoxes();
    }

    @Override
    public List<Deck> getDecks() {
        return this.strategy.getDecks();
    }

    @Override
    public Player getNextPlayer() {
        if (!this.iter.hasNext()) {
            this.iter = this.players.iterator();
        }
        this.actualPlayer = (Player) this.iter.next();
        return this.actualPlayer;
    }

    @Override
    public Player endOfTurnAndNextPlayer() {
        this.actualPlayer.setDicesRoll(false);
        this.actualPlayer.setIfIsTheFirstLaunch(true);
        return this.getNextPlayer();
    }

    @Override
    public List<Integer> toRollDices() {
        return this.strategy.toRollDices(this.actualPlayer);
    }

    @Override
    public boolean getNextCardsAction(final Box box, final Card card, final Player player) {
        return this.strategy.getNextCardsActions(box, card, player);
    }

    @Override
    public boolean haveEnoughMoney(final Player player, final int moneyToPay) {
        return this.strategy.haveEnoughMoney(player, moneyToPay);
    }

    @Override
    public Player removePlayer(final Player player) {
        final int position = this.players.indexOf(player);
        this.iter = null;
        if (position == this.players.size() - 1) {
            this.players.remove(position);
            this.iter = this.players.listIterator(0);
        } else {
            this.players.remove(position);
            this.iter = this.players.listIterator(position);
        }
        this.actualPlayer = this.iter.next();
        return this.actualPlayer;
    }

}

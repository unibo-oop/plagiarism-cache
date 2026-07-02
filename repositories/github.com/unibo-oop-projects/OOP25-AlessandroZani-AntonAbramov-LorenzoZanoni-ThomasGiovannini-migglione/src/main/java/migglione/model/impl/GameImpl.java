package migglione.model.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

import migglione.model.api.Game;
import migglione.model.api.Player;

/**
 * Class designed to overlook a match.
 * Takes care of managing when the turns happen and more
 */
public final class GameImpl extends Match implements Game {

    private final String playerName;
    private String currAttr = "Attk";
    private final Map<Player, Integer> currVals = new LinkedHashMap<>();

    /**
     * Constructor of the class.
     * Due to being a "more functional" version of Match,
     * it inherits its characteristics.
     * 
     * @param name the name of the player
     */
    public GameImpl(final String name) {
        super(
            new User(new ArrayList<>(), name), 
            new Mosquito(new ArrayList<>(), false), 
            new StandardCardDrawImpl(new DeckImpl())
        );
        this.playerName = name;
    }

    @Override
    public void playTurnLead(final String attr, final Card card) {
        play(getTurnLeader(), attr, card);
        currAttr = getTurnLeader().getAttr();
    }

    @Override
    public void playTurnTail(final Card card) {
        final Player p = getPlayers().getFirst().equals(getTurnLeader()) ? getPlayers().getLast() : getPlayers().getFirst();
        play(p, currAttr, card);
    }

    private void play(final Player p, final String attr, final Card card) {
        p.chooseAttr(attr);
        currVals.put(p, p.playCard(attr, card));
    }

    @Override
    public boolean playTurnStored() {
        final Player msq = getPlayers().getLast();
        final Player plr = getPlayers().getFirst();
        return playTurn(currVals.get(plr), currVals.get(msq));
    }

    @Override
    public String getCurrAttr() {
        return this.currAttr;
    }

    @Override
    public Optional<Integer> getPlayerScore() {
        for (final var p : getPlayers()) {
            if (p.getName().equals(playerName)) {
                return Optional.of(getScore(p));
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Integer> getCPUScore() {
        for (final var p : getPlayers()) {
            if (!p.getName().equals(playerName)) {
                return Optional.of(getScore(p));
            }
        }
        return Optional.empty();
    }
}

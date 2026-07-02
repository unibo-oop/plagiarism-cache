package it.unibo.briscoola.model.impl.game;

import java.util.ArrayList;
import java.util.List;

import it.unibo.briscoola.model.api.attributes.Difficulty;
import it.unibo.briscoola.model.api.card.Card;
import it.unibo.briscoola.model.api.deck.Deck;
import it.unibo.briscoola.model.api.game.GameBuilder;
import it.unibo.briscoola.model.api.game.GameModel;
import it.unibo.briscoola.model.api.player.Player;
import it.unibo.briscoola.model.impl.deck.DeckImpl;
import it.unibo.briscoola.model.impl.player.PlayerImpl;
import it.unibo.briscoola.model.impl.player.cpu.CpuPlayer;

/**
 * Builder class that allows a simple and fast creation of a {@link GameModel}
 * through a builder design.
 *
 * @author Adam Paolo Razzino
 */
public class GameBuilderImpl implements GameBuilder {

    private final List<Player> players = new ArrayList<>();
    private int id;
    private Difficulty difficulty;

    /**
     * Basic constructor in which it instantiates the human
     * {@link Player} with the id equals to 0.
     *
     * @param name {@link String} of the player nickname
     */
    public GameBuilderImpl(final String name) {
        this.id = 0;
        players.add(new PlayerImpl(this.id, name));
        this.id++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameBuilderImpl changeDifficulty(final Difficulty diff) {
        this.difficulty = diff;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameBuilderImpl addPlayer() {
        this.players.add(new CpuPlayer(this.id, this.difficulty));
        this.id++;
        return this;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public GameModel build() {
        final Deck<Card> deck = new DeckImpl();
        return new GameModelImpl(this.players, deck, this.difficulty);
    }
}

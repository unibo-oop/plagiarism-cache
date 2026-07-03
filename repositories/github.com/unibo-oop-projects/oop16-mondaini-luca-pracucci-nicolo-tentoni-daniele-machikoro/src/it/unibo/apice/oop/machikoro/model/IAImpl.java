package it.unibo.apice.oop.machikoro.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Stream;

import javafx.util.Pair;

/**
 * Implementazione dell'interfaccia IA.
 *
 */
public final class IAImpl implements IA {
    private static final int FIRST_LIMIT = 6;
    private static final int SECOND_LIMIT = 12;

    private static class IATheardSafe {
        private static final IA SINGLETON = new IAImpl();
    }

    /**
     * Costruttore privato per impedire l'inizializzazione della classe
     * dall'esterno.
     */
    protected IAImpl() {
    };

    /**
     * Metodo statico che restituisce l'istanza della classe IA. In questo modo
     * si concede l'uso di una sola istanza della classe IA per evitare
     * doppioni.
     * 
     * @return Intelligenza Artificiale
     */
    public static IA getIA() {
        return IATheardSafe.SINGLETON;
    }

    @Override
    public Card getHighestPriorityAimCard(final int turn, final PlayerImpl player) {
        Objects.requireNonNull(turn);
        Objects.requireNonNull(player);
        /*
         * Prima guardo alle carte obiettivo se posso comprarle e se mi conviene
         */
        final List<Pair<Card, Integer>> tmp = new LinkedList<Pair<Card, Integer>>();
        player.getAimCards().stream().filter(e -> !((AimCard) e).isEnabled())
                .filter(e -> ((AimCard) e).getCost() < player.getMoney()).forEach(e -> {
                    tmp.add(new Pair<Card, Integer>(e, e.getPriority(turn, player)));
                });
        /*
         * Adesso guardo la carta con la priorità maggiore nella lista e la
         * restituisco
         */
        return tmp.stream().max((x, y) -> x.getValue() - y.getValue()).get().getKey();
    }

    @Override
    public Card getHighestPriorityBoardCard(final List<Card> cards, final int turn, final PlayerImpl player) {
        Objects.requireNonNull(cards);
        Objects.requireNonNull(turn);
        Objects.requireNonNull(player);
        /*
         * Prima guardo alle carte obiettivo se posso comprarle e se mi conviene
         */
        final List<Pair<Card, Integer>> tmp = new LinkedList<Pair<Card, Integer>>();
        cards.stream().filter(e -> ((BoardCard) e).getCost() < player.getMoney())
                .forEach(e -> tmp.add(new Pair<Card, Integer>(e, e.getPriority(turn, player))));
        /*
         * Adesso guardo la carta con la priorità maggiore nella lista e la
         * restituisco
         */
        return tmp.stream().max((x, y) -> x.getValue() - y.getValue()).get().getKey();
    }

    @Override
    public Card getHighestPriorityCard(final List<Card> cards, final int turn, final PlayerImpl player) {
        Objects.requireNonNull(cards);
        Objects.requireNonNull(turn);
        Objects.requireNonNull(player);
        if (turn == 1) {
            final Random r = new Random();
            final Card[] tmp = (Card[]) cards.stream().filter(e -> e.getClass().equals(BoardCard.class)).toArray();
            return tmp[r.nextInt(tmp.length)];
        }
        final List<Pair<Card, Integer>> tmp = new LinkedList<Pair<Card, Integer>>();
        /*
         * Prima guardo la convenienza delle carte obiettivo che posso comprare
         */
        player.getAimCards().stream().filter(e -> !((AimCard) e).isEnabled())
                .filter(e -> ((AimCard) e).getCost() <= player.getMoney()).forEach(e -> {
                    tmp.add(new Pair<Card, Integer>(e, e.getPriority(turn, player)));
                });
        /*
         * Poi guardo la convenienza delle carte sul campo che posso comprare
         */
        cards.stream()
                .filter(e -> ((BoardCard) e).getCost() <= player.getMoney()
                        && player.getBoardCards().stream().filter(f -> f.getName().equals(e.getName())).count() < 4)
                .forEach(e -> tmp.add(new Pair<Card, Integer>(e, e.getPriority(turn, player))));
        /*
         * Adesso guardo la carta con la priorità maggiore nella lista e la
         * restituisco
         */

        return tmp.stream().max((x, y) -> x.getValue() - y.getValue()).get().getKey();
    }

    @Override
    public int rollingDice(final Player player) {
        Objects.requireNonNull(player);
        if (player.getAimCards().stream()
                .filter(e -> ((AimCard) e).getEffect().equals(AimEffect.TRAIN_STATION) && ((AimCard) e).isEnabled())
                .count() == 0) {
            return 1;
        }
        int countSmallNumberEffects;
        int countBigNumberEffects;
        countSmallNumberEffects = Stream.iterate(0, i -> i + 1).limit(FIRST_LIMIT)
                .mapToInt(e -> player.getEffectsByNumber(e).stream().mapToInt(f -> f.getTotalRevenue(player)).sum())
                .sum();
        countBigNumberEffects = Stream.iterate(FIRST_LIMIT + 1, i -> i + 1).limit(SECOND_LIMIT)
                .mapToInt(e -> player.getEffectsByNumber(e).stream().mapToInt(f -> f.getTotalRevenue(player)).sum())
                .sum();
        if (countBigNumberEffects >= countSmallNumberEffects) {
            return 2;
        }
        return 1;
    }
}

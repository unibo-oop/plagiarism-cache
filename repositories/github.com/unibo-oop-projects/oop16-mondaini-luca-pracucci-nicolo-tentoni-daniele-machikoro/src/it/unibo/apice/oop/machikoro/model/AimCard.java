package it.unibo.apice.oop.machikoro.model;

import java.util.Objects;

/**
 * Classe che implementa le carte obiettivo. Il giocatore che le possiederà
 * tutte contemporaneamente verrà dichiarato vincitore.
 */
public class AimCard extends CardImpl {

    private String lazyToString = "";
    private boolean needToString = true;
    private static final int BIG_NUMBER_CARDS = 6;
    private boolean enabled;
    private final AimEffect effect;

    /**
     * Costruttore completo per la classe AimCard.
     * 
     * @param name
     *            Nome della carta.
     * @param cost
     *            Costo della carta.
     * @param effect
     *            Effetto della carta.
     */
    public AimCard(final String name, final int cost, final AimEffect effect) {
        super(name, cost);
        this.enabled = false;
        this.effect = effect;
    }

    /**
     * Metodo per capire se la carta è abilitata oppure no.
     * 
     * @return True se la carta è abilitata, false altrimenti.
     */
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Metodo per impostare se la carta è abilitata oppure no.
     * 
     * @param enabled
     *            True per abilitare la carta, false altrimenti.
     */
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
        this.needToString = true;
    }

    /**
     * Metodo per ottenere l'effetto della carta corrente.
     * 
     * @return Effetto della carta corrente.
     */
    public AimEffect getEffect() {
        return this.effect;
    }

    @Override
    public int getPriority(final int turn, final PlayerImpl player) {
        int value = 0;
        if (this.effect.equals(AimEffect.SHOPPING_MALL)) {
            final long numRestAndMarket = player.getBoardCards(CardType.SHOP).stream().count()
                    + player.getBoardCards(CardType.RESTAURANT).stream().count();
            value = (int) numRestAndMarket * this.getCost();
        } else if (this.effect.equals(AimEffect.TRAIN_STATION)) {
            final long numGreenBigCards = player.getBoardCards(CardType.FACTORY).stream()
                    .filter(e -> ((BoardCard) e).getTriggers().stream().mapToInt(v -> v.intValue()).min()
                            .getAsInt() > BIG_NUMBER_CARDS)
                    .count()
                    + player.getBoardCards(CardType.MARKET).stream()
                            .filter(e -> ((BoardCard) e).getTriggers().stream().mapToInt(v -> v.intValue()).min()
                                    .getAsInt() > BIG_NUMBER_CARDS)
                            .count()
                    + player.getBoardCards(CardType.SHOP).stream().filter(e -> ((BoardCard) e).getTriggers().stream()
                            .mapToInt(v -> v.intValue()).min().getAsInt() > BIG_NUMBER_CARDS).count();
            value = (int) numGreenBigCards * this.getCost();
        } else {
            value = this.getCost();
        }
        return value * turn * turn;
    }

    /**
     * Metodo per ottenere la stringa corrispondente alla carta in questione.
     */
    @Override
    public String toString() {
        if (this.needToString) {
            final StringBuilder builder = new StringBuilder();
            this.lazyToString = builder.append(this.getName()).append(";").append(this.getCost()).append(";")
                    .append(this.isEnabled()).append(";").append(this.effect.toString()).append("#").toString();
            this.needToString = false;
        }
        return lazyToString;
    }

    @Override
    public Card getClone() {
        final AimCard tmp = new AimCard(this.getName(), this.getCost(), this.getEffect());
        if (this.isEnabled()) {
            tmp.setEnabled(true);
        }
        return tmp;
    }

    @Override
    public boolean equals(final Object obj) {
        if (Objects.isNull(obj)) {
            return false;
        } else {
            if (obj instanceof AimCard) {
                return ((AimCard) obj).getEffect().equals(this.getEffect());
            } else {
                return false;
            }
        }

    }

    @Override
    public int hashCode() {
        return 1;
    }

}

package it.unibo.apice.oop.machikoro.model;

/**
 * Implementazione astratta dell'interfaccia Card che rapprensenta tutte le
 * carte del gioco.
 */
public abstract class CardImpl implements Card {
    private final String name;
    private final int cost;

    /**
     * Costruttore completo di carta.
     * 
     * @param name
     *            Nome della carta.
     * @param cost
     *            Costo della carta.
     */
    protected CardImpl(final String name, final int cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getCost() {
        return this.cost;
    }

    @Override
    public abstract int getPriority(int turn, PlayerImpl player);

}

package it.unibo.apice.oop.machikoro.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe che rappresenta le carte in gioco, ognuna delle quali ha degli effetti
 * diversi che possono farci guadagnare delle monete per il conseguimento della
 * vittoria.
 */
public class BoardCard extends CardImpl {

    private String lazyToString = "";
    private boolean needToString = true;
    /**
     * Tipo della carta
     */
    private final CardType type;
    private final Set<Integer> triggers;
    private Effect effect;
    private static final int BIG_CARDS_NUMBER = 6;

    /**
     * Costruttore completo per la classe BoardCard.
     * 
     * @param name
     *            Nome completo della carta.
     * @param effect
     *            Effetto della carta.
     * @param cost
     *            Costo in monete della carta.
     * @param type
     *            Tipo della carta.
     * @param trigger
     *            Risultato del/dei dadi con i quali "Procca" l'effetto.
     */
    @SuppressWarnings("unchecked")
    public BoardCard(final String name, final Effect effect, final int cost, final CardType type,
            final Set<Integer> trigger) {
        super(name, cost);
        try {
            this.effect = (Effect) effect.clone();
        } catch (CloneNotSupportedException e) {
            this.effect = new EffectImpl(effect.getColor(), effect.getRevenue(), effect.getForInstanceOf());
        }
        this.type = type;
        this.triggers = (Set<Integer>) ((HashSet<Integer>) trigger).clone();
    }

    /**
     * Metodo per ottenere il tipo della carta corrente.
     * 
     * @return Tipo della carta.
     */
    public CardType getType() {
        return this.type;
    }

    /**
     * Metodo per ottenere il Set dei triggers della carta corrente.
     * 
     * @return Set di triggers.
     */
    public Set<Integer> getTriggers() {
        return triggers;
    }

    /**
     * Metodo per ottenere l'effetto della carta corrente.
     * 
     * @return Oggetto effetto.
     */
    public Effect getEffect() {
        return this.effect;
    }

    @Override
    public int getPriority(final int turn, final PlayerImpl player) {
        // Per le BoardCard la priorità viene semplicemente calcolata in base al
        // loro costo
        int delta = 4;
        if (turn < 4 && this.triggers.stream().min((x, y) -> x - y).get() <= BIG_CARDS_NUMBER
                || turn > 4 && this.triggers.stream().min((x, y) -> x - y).get() > BIG_CARDS_NUMBER) {
            delta = 8;
        }
        if (turn > 4 && this.triggers.stream().min((x, y) -> x - y).get() <= BIG_CARDS_NUMBER
                || turn < 4 && this.triggers.stream().min((x, y) -> x - y).get() > BIG_CARDS_NUMBER) {
            delta = 1;
        }
        return this.getCost() * delta;
    }

    /**
     * Metodo per ottenere sotto forma di stringa la carta da gioco corrente.
     */
    @Override
    public String toString() {
        if (this.needToString) {
            final StringBuilder builder = new StringBuilder();
            this.lazyToString = builder.append(this.getName()).append(";").append(this.getCost()).append(";")
                    .append(this.type).append(";").append(this.effect.toString()).append("#").toString();
            this.needToString = false;
        }
        return lazyToString;
    }

    @Override
    public Card getClone() {
        try {
            return new BoardCard(this.getName(), (Effect) this.getEffect().clone(), this.getCost(), this.getType(),
                    this.triggers);
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}

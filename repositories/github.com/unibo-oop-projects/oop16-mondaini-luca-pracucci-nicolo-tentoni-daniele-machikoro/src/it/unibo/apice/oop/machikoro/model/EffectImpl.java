package it.unibo.apice.oop.machikoro.model;

/**
 * Implementazione dell'interfaccia Effect.
 */
public class EffectImpl implements Effect, Cloneable {
    private final EffectColor color;
    private final int revenue;
    private final CardType forInstanceOf;

    /**
     * Costruttore completo della classe EffectImpl.
     * 
     * @param color
     *            Colore dell'effetto
     * @param revenue
     *            Rendita in monete.
     * @param forInstanceOf
     *            Tipo di carte moltiplicatore della rendita.
     */
    public EffectImpl(final EffectColor color, final int revenue, final CardType forInstanceOf) {
        this.color = color;
        this.revenue = revenue;
        this.forInstanceOf = forInstanceOf;
    }

    @Override
    public EffectColor getColor() {
        return this.color;
    }

    @Override
    public int getRevenue() {
        return this.revenue;
    }

    @Override
    public int getTotalRevenue(final Player player) {
        if (!this.forInstanceOf.equals(CardType.NOTHING)) {
            return this.revenue * player.getBoardCards(forInstanceOf).size();
        }
        return this.revenue;
    }

    @Override
    public CardType getForInstanceOf() {
        return this.forInstanceOf;
    }

    @Override
    public void resolveEffect(final Player receiver) throws EffectColorNotMatch {
        if (this.color == EffectColor.RED) {
            throw new EffectColorNotMatch(
                    "Hai cercato di invocare il metodo ResolveEffect con receiver su una carta di colore rosso.");
        }
        receiver.receiveMoney(this.getTotalRevenue(receiver));

    }

    @Override
    public void resolveEffect(final Player giver, final Player receiver) throws EffectColorNotMatch {
        if (this.color != EffectColor.RED) {
            throw new EffectColorNotMatch(
                    "Hai cercato di invocare il metodo ResolveEffect con giver e receiver su una carta di colore non rosso.");
        }
        if (receiver.getAimCards().stream()
                .filter(e -> ((AimCard) e).getEffect().equals(AimEffect.SHOPPING_MALL) && ((AimCard) e).isEnabled())
                .count() > 0) {
            giver.giveMoney(receiver, 1);
        }
        giver.giveMoney(receiver, this.getTotalRevenue(receiver));
    }

    @Override
    public String toString() {
        return this.color + ";" + this.revenue + ";" + this.forInstanceOf + ";";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return (EffectImpl) super.clone();
    }
}
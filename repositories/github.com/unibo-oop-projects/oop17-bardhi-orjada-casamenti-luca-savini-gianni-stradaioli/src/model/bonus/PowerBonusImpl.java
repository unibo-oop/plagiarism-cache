package model.bonus;

import java.util.function.Consumer;

import model.player.Player;
/**
 * 
 */
public enum PowerBonusImpl implements PowerBonus {

    GUADAGNI_1_ARMATURA(b->b.setArmor(b.getArmor() + BASE_BONUS)),
    COMPRI_CARTE_CON_UNO_SCONTO_DI_1(b -> b.setCashSale(b.getCashSale() + BASE_BONUS)),
    QUANDO_TU_GUADAGNI_PUNTI_GUADAGNI_1_IN_PIU(b -> b.setBonusPoints(b.getBonusPoints() + BASE_BONUS)),
    QUANDO_GUADAGNI_SOLDI_PRENDI_1_IN_PIU(b -> b.setBonusCash(b.getBonusCash() + BASE_BONUS)),
    AUMENTI_LA_VITA_MASSIMA_DI_1(b -> b.setBonusLife(b.getBonusLife() + BASE_BONUS));

    // private static final int BASE_BONUS = 1;
    private Consumer<Bonus> c;

    private PowerBonusImpl(final Consumer<Bonus> c) {
        this.c = c;
    }

    @Override
    public void power(final Player player) {
        c.accept(player.getBonus());
    }

    @Override
        public String toString() {
                return this.name().substring(0, 1) + this.name().substring(1, this.name().length()).toLowerCase().replaceAll("_", " ");
        }

}

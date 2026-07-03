package model.tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import model.bonus.Bonus;
import model.bonus.BonusImpl;
import model.bonus.PowerBonusImpl;
import model.player.Player;
import model.player.PlayerImpl;
/**
 *
 */
public class TestBonus {
    /**
     * 
     */
    @Test
    public void testBasic() {
        final Bonus b1 = new BonusImpl();
        final Bonus b2 = new BonusImpl();
        final Bonus b3 = new BonusImpl();

        final List<Bonus> bonus = Arrays.asList(b1, b2, b3);

        bonus.forEach(b -> assertEquals(b.getBonusLife(), 0));
        bonus.forEach(b -> assertEquals(b.getArmor(), 0));
        bonus.forEach(b -> assertEquals(b.getBonusCash(), 0));
        bonus.forEach(b -> assertEquals(b.getBonusPoints(), 0));
        bonus.forEach(b -> assertEquals(b.getCashSale(), 0));
        bonus.forEach(b -> assertEquals(b.getBonusDamages(), 0));
    }
    /**
     * 
     */
    @Test
    public void testSetting() {
        final Bonus b1 = new BonusImpl();
        final Bonus b2 = new BonusImpl();
        final Bonus b3 = new BonusImpl();

        b1.setBonusLife(1);
        b2.setArmor(2);
        b3.setBonusCash(1);
        b1.setBonusPoints(1);
        b2.setCashSale(1);
        b3.setBonusDamages(3);

        assertEquals(b1.getBonusLife(), 1);
        assertEquals(b2.getArmor(), 2);
        assertEquals(b3.getBonusCash(), 1);
        assertEquals(b1.getBonusPoints(), 1);
        assertEquals(b2.getCashSale(), 1);
        assertEquals(b3.getBonusDamages(), 3);
    }
    /**
     * 
     */
    @Test
    public void testPowerBonus() {

        final Player p1 = new PlayerImpl("Gianni", 10, 0);
        final Player p2 = new PlayerImpl("Nico", 10, 0);
        final Player p3 = new PlayerImpl("Dada", 10, 0);
        final Player p4 = new PlayerImpl("Malto", 10, 0);

        PowerBonusImpl.GUADAGNI_1_ARMATURA.power(p1);

        // .active(p1, Arrays.asList(p2, p3, p4));
        p1.takeDamages(1);

        assertEquals(p1.getBonus().getArmor(), 1);
        assertEquals(p1.getLife(), 10);

        p1.takeDamages(3);
        assertEquals(p1.getLife(), 8);

        PowerBonusImpl.COMPRI_CARTE_CON_UNO_SCONTO_DI_1.power(p2);

        p2.earnMoney(4);
        p2.loseMoney(5);

        assertEquals(p1.getMoney(), 0);

        PowerBonusImpl.QUANDO_TU_GUADAGNI_PUNTI_GUADAGNI_1_IN_PIU.power(p3);

        p3.increasePoints(4);

        assertEquals(p3.getPoints(), 5);

        PowerBonusImpl.QUANDO_GUADAGNI_SOLDI_PRENDI_1_IN_PIU.power(p3);

        p3.earnMoney(4);

        assertEquals(p3.getMoney(), 5);

        PowerBonusImpl.AUMENTI_LA_VITA_MASSIMA_DI_1.power(p4);

        p4.rechargeLife(4);

        assertEquals(p4.getLife(), 11);
    }
}

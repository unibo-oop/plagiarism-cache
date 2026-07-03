package it.unibo.apice.oop.machikoro.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;

import org.junit.Test;

/**
 * Classe di test per la classe player e tutto il model.
 */
public class PlayerImplTest {
    private static final int MONEY_DROP = 22;
    private static final int MONEY_1 = 4;
    private static final int MONEY_2 = 5;
    private static final int MONEY_3 = 6;
    private static final String CAMPO_DI_GRANO = "Campo di Grano";
    private static final String CAFFETTERIA = "Caffetteria";
    private static final String PANIFICIO = "Panificio";
    private static final String MERCATO_ORTOFRUTTICOLO = "Mercato Ortofrutticolo";
    private static final String STAZIONE_FERROVIARIA = "Stazione Ferroviaria";
    private static final int TRIGGERS_7 = 7;
    private static final int TOT_MONEY = 5;

    /**
     * Metodo per testare gli effetti.
     */
    @Test
    public void testBaseEffect() {
        final Effect blue = new EffectImpl(EffectColor.BLUE, 1, CardType.RESTAURANT);
        final Effect red = new EffectImpl(EffectColor.RED, 2, CardType.FACTORY);
        final Effect green = new EffectImpl(EffectColor.GREEN, 3, CardType.PRIMARY);
        assertEquals(EffectColor.BLUE, blue.getColor());
        assertEquals(EffectColor.RED, red.getColor());
        assertEquals(EffectColor.GREEN, green.getColor());
        assertEquals(1, blue.getRevenue());
        assertEquals(2, red.getRevenue());
        assertEquals(3, green.getRevenue());
    }

    /**
     * Metodo per testare le boardCards.
     */
    @Test
    public void testBaseBoardCard() {
        final Effect blue = new EffectImpl(EffectColor.BLUE, 1, CardType.RESTAURANT);
        final Effect red = new EffectImpl(EffectColor.RED, 2, CardType.FACTORY);
        final Effect green = new EffectImpl(EffectColor.GREEN, 3, CardType.PRIMARY);
        final HashSet<Integer> triggers = new HashSet<Integer>();
        triggers.add(1);
        final Card campoDiGrano = new BoardCard(CAMPO_DI_GRANO, blue, 1, CardType.AGRICOLTURE, triggers);
        triggers.clear();
        triggers.add(3);
        final Card caffetteria = new BoardCard(CAFFETTERIA, red, 2, CardType.RESTAURANT, triggers);
        triggers.clear();
        triggers.add(2);
        triggers.add(3);
        final Card panificio = new BoardCard(PANIFICIO, green, 2, CardType.MARKET, triggers);
        assertEquals(CAMPO_DI_GRANO, campoDiGrano.getName());
        assertEquals("Caffetteria", caffetteria.getName());
        assertEquals(PANIFICIO, panificio.getName());
        assertEquals(1, campoDiGrano.getCost());
        assertEquals(2, caffetteria.getCost());
        assertEquals(2, panificio.getCost());
        assertEquals(CardType.AGRICOLTURE, ((BoardCard) campoDiGrano).getType());
        assertEquals(CardType.RESTAURANT, ((BoardCard) caffetteria).getType());
        assertEquals(CardType.MARKET, ((BoardCard) panificio).getType());
    }

    /**
     * Metodo per testare le AimCards.
     */
    @Test
    public void testBaseAimCard() {
        final Card stazione = new AimCard(STAZIONE_FERROVIARIA, 4, AimEffect.TRAIN_STATION);
        final Card commerciale = new AimCard("Centro Commerciale", 10, AimEffect.SHOPPING_MALL);
        assertEquals(4, stazione.getCost());
        assertEquals(10, commerciale.getCost());
        assertEquals("Stazione Ferroviaria", stazione.getName());
        assertEquals("Centro Commerciale", commerciale.getName());
        assertEquals(AimEffect.TRAIN_STATION, ((AimCard) stazione).getEffect());
        assertEquals(AimEffect.SHOPPING_MALL, ((AimCard) commerciale).getEffect());
        assertFalse(((AimCard) stazione).isEnabled());
        assertFalse(((AimCard) commerciale).isEnabled());
        ((AimCard) stazione).setEnabled(true);
        ((AimCard) commerciale).setEnabled(true);
        assertTrue(((AimCard) stazione).isEnabled());
        assertTrue(((AimCard) commerciale).isEnabled());
    }

    /**
     * Metodo per testare i giocatori.
     */
    @Test
    public void testHumanPlayers() {
        final Effect blue = new EffectImpl(EffectColor.BLUE, 1, CardType.RESTAURANT);
        final Effect red = new EffectImpl(EffectColor.RED, 2, CardType.FACTORY);
        final Effect green = new EffectImpl(EffectColor.GREEN, 3, CardType.NOTHING);
        final HashSet<Integer> triggers = new HashSet<Integer>();
        triggers.add(1);
        final Card campoDiGrano = new BoardCard(CAMPO_DI_GRANO, blue, 1, CardType.AGRICOLTURE, triggers);
        triggers.clear();
        triggers.add(2);
        final Card caffetteria = new BoardCard("Caffetteria", red, 2, CardType.RESTAURANT, triggers);
        triggers.clear();
        triggers.add(2);
        triggers.add(3);
        final Card panificio = new BoardCard(PANIFICIO, green, 2, CardType.MARKET, triggers);
        final Card stazione = new AimCard(STAZIONE_FERROVIARIA, 4, AimEffect.TRAIN_STATION);
        final Card commerciale = new AimCard("Centro Commerciale", 10, AimEffect.SHOPPING_MALL);
        final Card radio = new AimCard("Torre Radio", 22, AimEffect.RADIO_TOWER);
        final Card luna = new AimCard("Luna Park", 16, AimEffect.AMUSEMENT_PARK);
        final Player daniele = new PlayerImpl("Daniele Tentoni", false);
        final Player luca = new PlayerImpl("Luca Mondaini", false);
        daniele.receiveMoney(4);
        try {
            daniele.buyCard(caffetteria);
            daniele.buyCard(campoDiGrano);
            daniele.receiveMoney(10);
            daniele.buyCard(commerciale);
            daniele.buyCard(stazione);
            daniele.receiveMoney(2);
            daniele.buyCard(panificio);
            assertFalse(daniele.checkWin());
            daniele.receiveMoney(MONEY_DROP);
            daniele.buyCard(radio);
            daniele.receiveMoney(16);
            daniele.buyCard(luna);
            assertTrue(daniele.checkWin());
            daniele.getEffectsByNumber(2).stream().forEach(e -> {
                try {
                    e.resolveEffect(luca, daniele);
                } catch (EffectColorNotMatch e1) {
                    System.out.println(e1.getMessage());
                }
            });
            assertEquals(1, daniele.getMoney());
            assertEquals(2, luca.getMoney());
            daniele.getEffectsByNumber(3).stream().forEach(e -> {
                try {
                    e.resolveEffect(daniele);
                } catch (EffectColorNotMatch e1) {
                    System.out.println(e1.getMessage());
                }
            });
            assertEquals(2, luca.getMoney());
            assertEquals(MONEY_1, daniele.getMoney());
            daniele.receiveMoney(1);
            assertEquals(2, luca.getMoney());
            assertEquals(MONEY_2, daniele.getMoney());
            luca.giveMoney(daniele, 1);
            assertEquals(1, luca.getMoney());
            assertEquals(MONEY_3, daniele.getMoney());
        } catch (NotEnoughMoneyException e) {
            System.out.println("Non ho avuto abbastanza soldi per questo edificio: " + e.getCard() + ".");
        } catch (AlreadyBoughtCardException e) {
            System.out.println("Hai già comprato questa carta: " + e.getCard().toString() + ".");
        }
        try {
            daniele.buyCard(campoDiGrano);
            daniele.buyCard(campoDiGrano);
            daniele.buyCard(campoDiGrano);
        } catch (NotEnoughMoneyException e) {
            System.out.println("Non hai abbastanza monete per comprare questa carta: " + e.getCard() + ".");
        } catch (AlreadyBoughtCardException e) {
            System.out.println("Hai già comprato questa carta: " + e.getCard().toString() + ".");
        }
    }

    /**
     * Altri test per provare che gli effetti multipli vengano comunque
     * eseguiti.
     */
    @Test
    public void anotherEffectTest() {
        final Effect blue = new EffectImpl(EffectColor.BLUE, 1, CardType.NOTHING);
        final HashSet<Integer> triggers = new HashSet<Integer>();
        triggers.add(1);
        final Card campoDiGrano = new BoardCard(CAMPO_DI_GRANO, blue, 1, CardType.AGRICOLTURE, triggers);
        final Player daniele = new PlayerImpl("Daniele Tentoni", false);
        try {
            daniele.buyCard(campoDiGrano);
            daniele.buyCard(campoDiGrano);
            daniele.buyCard(campoDiGrano);
        } catch (NotEnoughMoneyException e) {
            System.out.println("Non hai abbastanza monete per comprare questa carta: " + e.getCard());
        } catch (AlreadyBoughtCardException e) {
            System.out.println("Hai già comprato questa carta :" + e.getCard());
        }
        daniele.getEffectsByNumber(1).stream().forEach(e -> {
            try {
                e.resolveEffect(daniele);
            } catch (EffectColorNotMatch e1) {
                System.out.println("Effetto sbagliato!");
            }
        });
        assertEquals(3, daniele.getMoney());
        final Effect green = new EffectImpl(EffectColor.GREEN, 3, CardType.AGRICOLTURE);
        triggers.clear();
        triggers.add(4);
        final Card panificio = new BoardCard(PANIFICIO, green, 2, CardType.MARKET, triggers);
        try {
            daniele.buyCard(panificio);
        } catch (NotEnoughMoneyException e1) {
            System.out.println(e1);
        } catch (AlreadyBoughtCardException e1) {
            System.out.println(e1);
        }
        assertEquals(1, daniele.getMoney());
        daniele.getEffectsByNumber(4).stream().forEach(e -> {
            try {
                e.resolveEffect(daniele);
            } catch (EffectColorNotMatch e1) {
                System.out.println("Effetto sbagliato!");
            }
        });
        assertEquals(10, daniele.getMoney());

    }

    /**
     * Testing della IA.
     */
    @Test
    public void someIATest() {
        final Effect blue = new EffectImpl(EffectColor.BLUE, 1, CardType.NOTHING);
        final Effect green = new EffectImpl(EffectColor.GREEN, 1, CardType.NOTHING);
        final HashSet<Integer> triggers = new HashSet<Integer>();
        triggers.add(1);
        final Card campoDiGrano = new BoardCard(CAMPO_DI_GRANO, blue, 1, CardType.AGRICOLTURE, triggers);
        final Player daniele = new PlayerImpl("Daniele Tentoni", false);
        try {
            daniele.buyCard(campoDiGrano);
        } catch (NotEnoughMoneyException e) {
            System.out.println(e);
        } catch (AlreadyBoughtCardException e) {
            System.out.println(e);
        }
        assertEquals(1, IAImpl.getIA().rollingDice(daniele));
        triggers.clear();
        triggers.add(TRIGGERS_7);
        final Card mercatoOrtofrutticolo = new BoardCard(MERCATO_ORTOFRUTTICOLO, green, 2, CardType.MARKET, triggers);
        try {
            daniele.buyCard(mercatoOrtofrutticolo);
            daniele.receiveMoney(TOT_MONEY);
            daniele.buyCard(mercatoOrtofrutticolo);
        } catch (NotEnoughMoneyException e) {
            System.out.println(e);
        } catch (AlreadyBoughtCardException e) {
            System.out.println(e);
        }
        assertEquals(1, IAImpl.getIA().rollingDice(daniele));
        final Card stazione = new AimCard(STAZIONE_FERROVIARIA, 4, AimEffect.TRAIN_STATION);
        try {
            daniele.receiveMoney(3);
            daniele.buyCard(stazione);
        } catch (NotEnoughMoneyException e) {
            System.out.println(e);
        } catch (AlreadyBoughtCardException e) {
            System.out.println(e);
        }
        System.out.println(daniele.getAimCards());
        assertEquals(2, IAImpl.getIA().rollingDice(daniele));
    }
}

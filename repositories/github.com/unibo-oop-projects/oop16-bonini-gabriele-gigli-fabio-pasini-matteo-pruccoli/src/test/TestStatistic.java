package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import maingame.statistics.Statistics;
import maingame.statistics.StatisticsImpl;

/**
 * Classe per il test della classe Statistic.
 */
public class TestStatistic {
    private final Statistics statistic = StatisticsImpl.getStatistics();

    /**
     *  Test per controllo valori della classe satistics.
     */
    @Test
    public void testValue() {
        final int incrementTime1 = 9;
        final int incrementTime2 = 61;
        final int incrementTime3 = 3600;
        final int incrementKill = 50;
        statistic.resetStats();
        assertEquals(statistic.getKill(), 0);
        assertEquals(statistic.getMaxScore(), 0);
        assertEquals(statistic.getSteps(), 0);
        assertEquals(statistic.getProjectile(), 0);
        assertEquals(statistic.getTime(), "0:00:00");
        statistic.incrementTime(incrementTime1);
        assertEquals(statistic.getTime(), "0:00:09");
        statistic.incrementTime(incrementTime2);
        assertEquals(statistic.getTime(), "0:01:10");
        statistic.incrementTime(incrementTime3);
        assertEquals(statistic.getTime(), "1:01:10");
        statistic.incrementKill(incrementKill);
        assertEquals(statistic.getKill(), incrementKill);
    }

    /**
     * Testa eccezione.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testException() {
        statistic.incrementKill(-1);
    }

}

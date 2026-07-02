package test.it.unibo.oop.manpac.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import it.unibo.oop.manpac.model.score.*;

/**
 * Testing class for scoring.
 */
public class TestPoints {

    /**
     * test semplice con punteggi di tipo Integers.
     */
    @Test
    public void testPoints() {
        final Points<Integer> pt =  new PointsImpl<>(10);
        final Points<Integer> pt2 = pt.getCopy();
        assertNotSame(pt, pt2);

        assertTrue(pt.equals(pt));
        assertTrue(pt.equals(pt2));
        assertTrue(pt2.equals(pt));

        final Points<Integer> ptm =  new PointsImpl.Mutable<>(pt.getValue());
        final Points<Integer> ptm2 = ptm.getCopy();
        assertNotSame(ptm, ptm2);
        assertTrue(ptm.equals(ptm));
        assertTrue(ptm.equals(ptm2));
        assertTrue(ptm2.equals(ptm));

        // equals tra PointsImpl e PointsImpl.Mutable
        assertFalse(ptm.equals(pt));
        assertFalse(pt.equals(ptm));

        final Points<Double> pt4 =  new PointsImpl<>(10.0);
        assertNotEquals(pt, pt4);
    }

    /**
     * test con sole print per ferificare la mutabilità coi generici.
     */
    @Test
    public void testModificaPoints() {
        final Points<Integer> pt =  new PointsImpl<Integer>(Integer.valueOf(10));
        final Points<Integer> ptm = new PointsImpl.Mutable<>(pt.getValue());
        final Points<Integer> ptm2 = ptm.getCopy();

        System.out.println("PRIMO MUTABILE: " + ptm);
        System.out.println("SECONDO MUTABILE: " + ptm2);
        // modifico il secondo, il primo non dovrebbe essersi modificato
        ((PointsImpl.Mutable<Integer>) ptm2).setPoints(20);
        System.out.println("PRIMO MUTABILE DOPO MODIFICA: " + ptm);
        System.out.println("SECONDO MUTABILE DOPO MODIFICA: " + ptm2);
        System.out.println();


        final Points<Points<Integer>> pPi1 = new PointsImpl.Mutable<>(new PointsImpl.Mutable<Integer>(10));
        System.out.println("PRIMO : " + pPi1);
        final Points<Points<Integer>> pPi2 = pPi1.getCopy();
        System.out.println("SECONDO: " + pPi2);
        // modifico il secondo
        Points<Integer> appoggio = ((PointsImpl.Mutable<Points<Integer>>) pPi2).getValue();
        ((PointsImpl.Mutable<Integer>) appoggio).setPoints(20);

        System.out.println("SECONDO DOPO MODIFICA: " + pPi2);
        // ho mutato involontariamente anche il primo? credo proprio di si...
        System.out.println("PRIMO DOPO MODIFICA: " + pPi1);
    }
}
